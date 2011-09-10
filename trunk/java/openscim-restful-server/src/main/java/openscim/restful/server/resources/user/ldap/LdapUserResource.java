/*
 * openscim restful scim server
 * http://code.google.com/p/openscim/
 * Copyright (C) 2011 Matthew Crooke <matthewcrooke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package openscim.restful.server.resources.user.ldap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import openscim.entities.PluralAttribute;
import openscim.entities.User;
import openscim.restful.server.resources.user.UserResource;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;
import org.springframework.ldap.core.LdapTemplate;


public class LdapUserResource extends UserResource
{
	// log4j contants
	private static Logger logger = Logger.getLogger(LdapUserResource.class);
	
	// ldap parameters
	private LdapTemplate ldapTemplate = null;
	 
	public void setLdapTemplate(LdapTemplate ldapTemplate)
	{
		this.ldapTemplate = ldapTemplate;
	}
	
	@Override
	public Response retrieveUser(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{	
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			try
			{
				// retrieve the user
				User user = (User)ldapTemplate.lookup(uid, new UserAttributesMapper());				
				
				// check if the user was found
				if(user == null)
				{
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// determine the url of the new resource
				URI location = new URI("/User/" + user.getId());
				
				// user stored successfully, return the user				
				return Response.ok(user).location(location).build();
			}
			catch(URISyntaxException usException)
			{
				// problem generating entity location
				logger.error("problem generating entity location");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + uid + " not found");
				logger.debug(nException);
				
				// user not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
			}
		}
		else
		{
			// ldap not configured
			logger.error("ldap not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user ldap repository not configured");
		}		
	}

	@Override
	public Response createUser(UriInfo uriInfo, User user)
	{
	    // check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			try
			{
				try
				{
					// retrieve the user
					User lookedupUser = (User)ldapTemplate.lookup(user.getId(), new UserAttributesMapper());				
					
					// check if the user was found
					if(lookedupUser != null)
					{
						// user already exists				
						return ResourceUtilities.buildErrorResponse(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getMessage() + ": Resource " + user.getId() + " already exists");
					}
				}
				catch(Exception nException)
				{
					// user not found, do nothing
				}
				
				Attributes userAttributes = new BasicAttributes();
				
				// set the objectclass of the user
				userAttributes.put("objectclass", "inetOrgPerson");
				userAttributes.put("objectclass", "organizationalPerson");
				userAttributes.put("objectclass", "person");
				userAttributes.put("objectclass", "top");
				
				// set the uid
				userAttributes.put("uid", user.getId());
				
				// set the cn
				if(user.getDisplayName() != null) userAttributes.put("cn", user.getDisplayName());
				
				// set the names
				if(user.getName() != null)
				{
					if(user.getName().getFamilyName() != null) userAttributes.put("sn", user.getName().getFamilyName());
					if(user.getName().getGivenName() != null) userAttributes.put("givenName", user.getName().getGivenName());
				}
				
				// set the emails
				if(user.getEmails() != null)
				{
					Attribute attribute = new BasicAttribute("mail");
					List<PluralAttribute> emails = user.getEmails().getEmail();
					for(PluralAttribute email : emails)
					{						
						attribute.add(email.getValue());
					}
					userAttributes.put(attribute);
				}
					    
			    // set the telephones
				if(user.getPhoneNumbers() != null)
				{
					Attribute attribute = new BasicAttribute("telephoneNumber");
					List<PluralAttribute> telephones = user.getPhoneNumbers().getPhoneNumber();
					for(PluralAttribute telephone : telephones)
					{						
						attribute.add(telephone.getValue());
					}
					userAttributes.put(attribute);
				}
				
				// set the set with description
			    userAttributes.put("description", "Created via SCIM Restful Server for Java");
				
			    // create the user
			    ldapTemplate.bind(user.getId(), null, userAttributes);
				
				// determine the url of the new resource
				URI location = new URI("/User/" + user.getId());
				
				// user stored successfully, return the user				
				return Response.created(location).entity(user).build();
			}
			catch(URISyntaxException usException)
			{
				// problem generating entity location
				logger.error("problem generating entity location");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
			}
			catch(Exception nException)
			{
				// problem creating user
				logger.error("problem creating user");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem creating user");
			}
		}
		else
		{
			// ldap not configured
			logger.error("ldap not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user ldap repository not configured");
		}
	}

	
	@Override
	public Response updateUser(UriInfo uriInfo, String uid, User user)
	{
		// return an operation unsupported response
		return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider does not support the update user operation");
	}

	
	@Override
	public Response deleteUser(UriInfo uriInfo, String uid)
	{
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			try
			{
				// retrieve the user
				User user = (User)ldapTemplate.lookup(uid, new UserAttributesMapper());				
				
				// check if the user was found
				if(user == null)
				{
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// remove the retrieved user
				ldapTemplate.unbind(uid, true);
		    	
		    	// user removed successfully
		    	return Response.ok().build();
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + uid + " not found");
				logger.debug(nException);
				
				// user not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
			}
		}
		else
		{
			// ldap not configured
			logger.error("ldap not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user ldap repository not configured");
		}
	}
	
	
	@Override
	public Response changePassword(UriInfo uriInfo, String uid, User user)
	{
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			try
			{								
				// retrieve the user
				User lookedupUser = (User)ldapTemplate.lookup(uid, new UserAttributesMapper());				
				
				// check if the user was found
				if(lookedupUser == null)
				{
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// build a password modification			
				Attribute passwordAttribute = new BasicAttribute("userPassword", user.getPassword());				
				ModificationItem passwordItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, passwordAttribute);
			    
				// update the user password
				ldapTemplate.modifyAttributes(user.getId(), new ModificationItem[]{passwordItem});
			    
				// password changed successfully
			    return Response.status(HttpStatus.NO_CONTENT.getCode()).build();				
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + uid + " not found");
				logger.debug(nException);
				
				// user not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
			}
		}
		else
		{
			// ldap not configured
			logger.error("ldap not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider user ldap repository not configured");
		}
	}
}
