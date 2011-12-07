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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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
import openscim.restful.server.resources.group.ldap.GroupAttributesMapper;
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
	
	// ldap attribute mapping properties
	private Properties properties = null;
	
	// ldap attribute mapper
	private UserAttributesMapper mapper = null;		
	
	public void setLdapTemplate(LdapTemplate ldapTemplate)
	{
		this.ldapTemplate = ldapTemplate;
	}
	
	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}
	
	/*
	 * @see 
	 * openscim.restful.server.resources.user.UserResource#retrieveUser(javax.ws.rs.core.UriInfo,java.lang.String)
	 */
	@Override
	public Response retrieveUser(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{	
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			// create the mapper if it doesn't already exists
			if(mapper == null) mapper = new UserAttributesMapper(properties);
			
			// build the user dn
			String dn = uid;
			if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			{
				// utilise ldap formated dn
				dn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + "=" + 
				     uid + "," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
			}
			
			try
			{
				// retrieve the user
				User user = (User)ldapTemplate.lookup(dn, mapper);				
				
				// check if the user was found
				if(user == null)
				{
					logger.debug("Resource " + dn + " not found");
					
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// determine the url of the new resource
				URI location = new URI("/User/" + dn);
			    if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			    {
			    	location = new URI("/User/" + user.getId());
			    }
			    
			    // set the internal id to the dn
				user.setId(dn);
				if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
				{
					user.setId(user.getId());
				}
				
				// user stored successfully, return the user				
				return Response.ok(user).location(location).build();
			}
			catch(URISyntaxException usException)
			{
				// problem generating entity location
				logger.error("problem generating entity location");
				usException.printStackTrace(System.out);
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + dn + " not found");
				nException.printStackTrace(System.out);
				
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

	/*
	 * @see 
	 * openscim.restful.server.resources.user.UserResource#createUser(javax.ws.rs.core.UriInfo,openscim.entities.User)
	 */
	@Override
	public Response createUser(UriInfo uriInfo, User user)
	{
	    // check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			// create the mapper if it doesn't already exists
			if(mapper == null) mapper = new UserAttributesMapper(properties);
			
			// build the user dn
			String dn = user.getId();
			if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			{
				// utilise ldap formated dn
				dn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + "=" + 
				   user.getId() + "," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
			}
			
			try
			{
				try
				{
					// create the mapper if it doesn't already exists
					if(mapper == null) mapper = new UserAttributesMapper(properties);
					
					// retrieve the user
					User lookedUser = (User)ldapTemplate.lookup(dn, mapper);				
					
					// check if the user was found
					if(lookedUser != null)
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
				
				// get the objectclasses
				String objectclasses = properties.getProperty(UserAttributesMapper.ACCOUNT_OBJECTCLASS_ATTRIBUTE, UserAttributesMapper.DEFAULT_ACCOUNT_OBJECTCLASS_ATTRIBUTE);
				
				// set the objectclass of the user
				/*
				Attribute objectclassAttribute = new BasicAttribute("objectclass");
				Scanner scanner = new Scanner(objectclasses);				
				scanner.useDelimiter(",");
				while(scanner.hasNext())
				{
					objectclassAttribute.add(scanner.next());
				}
				*/
				
				BasicAttribute objectclassAttribute = new BasicAttribute("objectclass");
				objectclassAttribute.add("inetOrgPerson");
				objectclassAttribute.add("organizationalPerson");
				objectclassAttribute.add("person");
				objectclassAttribute.add("top");
				userAttributes.put(objectclassAttribute);
				
				// get the uid attribute name
				String uidAtttributeName = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE);
				
				// set the uid
				userAttributes.put(uidAtttributeName, user.getId());
				
				// get the display name attribute name
				String displayAtttributeName = properties.getProperty(UserAttributesMapper.DISPLAYNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_DISPLAYNAME_ATTRIBUTE);
				
				// set the display name
				if(user.getDisplayName() != null) userAttributes.put(displayAtttributeName, user.getDisplayName());
				
				// get the surname attribute name
				String surnameAtttributeName = properties.getProperty(UserAttributesMapper.FAMILYNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_FAMILYNAME_ATTRIBUTE);
				
				// get the given name attribute name
				String givenAtttributeName = properties.getProperty(UserAttributesMapper.GIVENNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_GIVENNAME_ATTRIBUTE);
				
				// set the names
				if(user.getName() != null)
				{
					if(user.getName().getFamilyName() != null) userAttributes.put(surnameAtttributeName, user.getName().getFamilyName());
					if(user.getName().getGivenName() != null) userAttributes.put(givenAtttributeName, user.getName().getGivenName());
				}
				
				// get the email attribute name
				String mailAtttributeName = properties.getProperty(UserAttributesMapper.MAIL_ATTRIBUTE, UserAttributesMapper.DEFAULT_MAIL_ATTRIBUTE);
				
				// set the emails
				if(user.getEmails() != null)
				{
					Attribute attribute = new BasicAttribute(mailAtttributeName);
					List<PluralAttribute> emails = user.getEmails().getEmail();
					for(PluralAttribute email : emails)
					{						
						attribute.add(email.getValue());
					}
					userAttributes.put(attribute);
				}
				
				// get the telephone attribute name
				String telephoneAtttributeName = properties.getProperty(UserAttributesMapper.TELEPHONE_ATTRIBUTE, UserAttributesMapper.DEFAULT_TELEPHONE_ATTRIBUTE);
				
			    // set the telephones
				if(user.getPhoneNumbers() != null)
				{
					Attribute attribute = new BasicAttribute(telephoneAtttributeName);
					List<PluralAttribute> telephones = user.getPhoneNumbers().getPhoneNumber();
					for(PluralAttribute telephone : telephones)
					{						
						attribute.add(telephone.getValue());
					}
					userAttributes.put(attribute);
				}
				
				// get the password attribute name
				String passwordAtttributeName = properties.getProperty(UserAttributesMapper.PASSWORD_ATTRIBUTE, UserAttributesMapper.DEFAULT_PASSWORD_ATTRIBUTE);
				
				// set the password
				if(user.getPassword() != null) userAttributes.put(passwordAtttributeName, user.getPassword());				
				
			    // create the user
			    ldapTemplate.bind(dn, null, userAttributes);
				
				// determine the url of the new resource
			    URI location = new URI("/User/" + dn);
			    if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			    {
			    	location = new URI("/User/" + user.getId());
			    }
			    
				// set the internal id to the dn
				user.setId(dn);
				if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			    {
					user.setId(user.getId());
			    }
				
				// user stored successfully, return the user				
				return Response.created(location).entity(user).build();
			}
			catch(URISyntaxException usException)
			{
				// problem generating entity location
				logger.error("problem generating entity location");
				usException.printStackTrace(System.out);
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
			}
			catch(Exception nException)
			{
				// problem creating user
				logger.error("problem creating user");
				nException.printStackTrace(System.out);
				
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
	
	/*
	 * @see 
	 * openscim.restful.server.resources.user.UserResource#updateUser(javax.ws.rs.core.UriInfo,openscim.entities.User)
	 */
	@Override
	public Response updateUser(UriInfo uriInfo, String uid, User user)
	{
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			// create the mapper if it doesn't already exists
			if(mapper == null) mapper = new UserAttributesMapper(properties);
			
			// build the user dn
			String dn = user.getId();
			if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			{
				// utilise ldap formated dn
				dn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + "=" + 
				   user.getId() + "," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
			}
			
			try
			{	
				// retrieve the user
				User lookedupUser = (User)ldapTemplate.lookup(dn, mapper);				
				
				// check if the user was found
				if(lookedupUser == null)
				{
					logger.debug("Resource " + dn + " not found");
					
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				List<ModificationItem> items = new ArrayList<ModificationItem>();
				
				// get the uid attribute name
				//String uidAtttributeName = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE);
				
				// build a uid modification
			    //if(user.getId() != null)
			    //{
			    //	Attribute uidAttribute = new BasicAttribute(uidAtttributeName, user.getId());				
				//	ModificationItem uidItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, uidAttribute);
				//	items.add(uidItem);
			    //}
				
			    // get the display name attribute name
				String displayAtttributeName = properties.getProperty(UserAttributesMapper.DISPLAYNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_DISPLAYNAME_ATTRIBUTE);
			    
				// build a cn modification
			    if(user.getDisplayName() != null)
			    {
			    	Attribute cnAttribute = new BasicAttribute(displayAtttributeName, user.getDisplayName());				
					ModificationItem cnItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, cnAttribute);
					items.add(cnItem);
			    }
								
				// build names modification
				if(user.getName() != null)
				{
					// get the surname attribute name
					String surnameAtttributeName = properties.getProperty(UserAttributesMapper.FAMILYNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_FAMILYNAME_ATTRIBUTE);
					
					// get the given name attribute name
					String givenAtttributeName = properties.getProperty(UserAttributesMapper.GIVENNAME_ATTRIBUTE, UserAttributesMapper.DEFAULT_GIVENNAME_ATTRIBUTE);
					
					if(user.getName().getFamilyName() != null)
				    {
				    	Attribute snAttribute = new BasicAttribute(surnameAtttributeName, user.getName().getFamilyName());				
						ModificationItem snItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, snAttribute);
						items.add(snItem);
				    }
					
					if(user.getName().getGivenName() != null)
				    {
				    	Attribute gnAttribute = new BasicAttribute(givenAtttributeName, user.getName().getGivenName());				
						ModificationItem gnItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, gnAttribute);
						items.add(gnItem);
				    }					
				}
				
				// set the emails
				if(user.getEmails() != null)
				{
					// get the email attribute name
					String mailAtttributeName = properties.getProperty(UserAttributesMapper.MAIL_ATTRIBUTE, UserAttributesMapper.DEFAULT_MAIL_ATTRIBUTE);
					
					Attribute emailAttribute = new BasicAttribute(mailAtttributeName);
					List<PluralAttribute> emails = user.getEmails().getEmail();
					for(PluralAttribute email : emails)
					{						
						emailAttribute.add(email.getValue());
					}
					ModificationItem emailItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, emailAttribute);
					items.add(emailItem);
				}
					    
			    // set the telephones
				if(user.getPhoneNumbers() != null)
				{
					// get the telephone attribute name
					String telephoneAtttributeName = properties.getProperty(UserAttributesMapper.TELEPHONE_ATTRIBUTE, UserAttributesMapper.DEFAULT_TELEPHONE_ATTRIBUTE);
					
					Attribute telephoneAttribute = new BasicAttribute(telephoneAtttributeName);
					List<PluralAttribute> telephones = user.getPhoneNumbers().getPhoneNumber();
					for(PluralAttribute telephone : telephones)
					{						
						telephoneAttribute.add(telephone.getValue());
					}
					ModificationItem telephoneItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, telephoneAttribute);
					items.add(telephoneItem);
				}
				
				// build a password modification
			    if(user.getPassword() != null)
			    {
			    	// get the password attribute name
					String passwordAtttributeName = properties.getProperty(UserAttributesMapper.PASSWORD_ATTRIBUTE, UserAttributesMapper.DEFAULT_PASSWORD_ATTRIBUTE);
			    	
			    	Attribute passwordAttribute = new BasicAttribute(passwordAtttributeName, user.getPassword());				
					ModificationItem passwordItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, passwordAttribute);
					items.add(passwordItem);
			    }
				
				// update the user password
				ModificationItem[] itemsArray = items.toArray(new ModificationItem[items.size()]);
				ldapTemplate.modifyAttributes(dn, itemsArray);
			    
				// password changed successfully
			    return Response.status(HttpStatus.NO_CONTENT.getCode()).build();				
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + dn + " not found");
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

	/*
	 * @see 
	 * openscim.restful.server.resources.user.UserResource#deleteUser(javax.ws.rs.core.UriInfo,java.lang.String)
	 */
	@Override
	public Response deleteUser(UriInfo uriInfo, String uid)
	{
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			// create the mapper if it doesn't already exists
			if(mapper == null) mapper = new UserAttributesMapper(properties);
			
			// build the user dn
			String dn = uid;
			if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			{
				// utilise ldap formated dn
				dn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + "=" + 
				   uid + "," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
			}
			
			try
			{
				// retrieve the user
				User user = (User)ldapTemplate.lookup(dn, mapper);				
				
				// check if the user was found
				if(user == null)
				{
					logger.debug("Resource " + dn + " not found");
					
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// remove the retrieved user
				ldapTemplate.unbind(dn, true);
		    	
		    	// user removed successfully
		    	return Response.ok().build();
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + dn + " not found");
				//logger.debug(nException);
				nException.printStackTrace(System.out);
				
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
	
	/*
	 * @see 
	 * openscim.restful.server.resources.user.UserResource#deleteUser(javax.ws.rs.core.UriInfo,java.lang.String,openscim.entities.User)
	 */
	@Override
	public Response changePassword(UriInfo uriInfo, String uid, User user)
	{
		// check the ldap template has been setup correctly
		if(ldapTemplate != null)
		{
			// create the mapper if it doesn't already exists
			if(mapper == null) mapper = new UserAttributesMapper(properties);
			
			// build the user dn
			String dn = uid;
			if(properties.getProperty(UserAttributesMapper.CONCEAL_ACCOUNT_DNS, UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS).equalsIgnoreCase(UserAttributesMapper.DEFAULT_CONCEAL_ACCOUNT_DNS))
			{
				// utilise ldap formated dn
				dn = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + "=" + 
				   uid + "," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
			}
			
			try
			{
				// retrieve the user
				User lookedUser = (User)ldapTemplate.lookup(dn, mapper);				
				
				// check if the user was found
				if(lookedUser == null)
				{
					logger.debug("Resource " + dn + " not found");
					
					// user not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + uid + " not found");
				}
				
				// build a password modification			
				Attribute passwordAttribute = new BasicAttribute("userPassword", user.getPassword());				
				ModificationItem passwordItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, passwordAttribute);
			    
				// update the user password
				ldapTemplate.modifyAttributes(dn, new ModificationItem[]{passwordItem});
			    
				// password changed successfully
			    return Response.status(HttpStatus.NO_CONTENT.getCode()).build();				
			}
			catch(Exception nException)
			{
				logger.debug("Resource " + dn + " not found");
				nException.printStackTrace(System.out);
				//logger.debug(nException);
				
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
