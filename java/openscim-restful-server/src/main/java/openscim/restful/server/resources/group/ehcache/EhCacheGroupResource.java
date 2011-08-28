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
package openscim.restful.server.resources.group.ehcache;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import openscim.entities.Group;
import openscim.restful.server.resources.group.GroupResource;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;


public class EhCacheGroupResource extends GroupResource
{
	// log4j contants
	private static Logger logger = Logger.getLogger(EhCacheGroupResource.class);
	
	// cache constants
	protected static final String GROUPS_CACHE = "groupsCache";
	
	// cache parameters
	protected CacheManager manager = null;
	protected Ehcache groupsCache = null;
	
	public void setCacheManager(CacheManager manager)
	{
		this.manager = manager;
	}
	
	public CacheManager getCacheManager()
	{
		return this.manager;
	}
	
	@Override
	public Response retrieveGroup(UriInfo uriInfo, String gid)
	{
		// attempt to find the group within the cache
		if(manager != null)
		{
			// retrieve the groups cache
			if(groupsCache == null) groupsCache = manager.getEhcache(GROUPS_CACHE);
			
			// check a groupsCache is configured
			if(groupsCache == null)
			{
				// groupsCache not configured
				logger.error("groupsCache not configured");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager groupsCache not configured");
			}
			
		    // check if the result is cached
		    Element cachedGroup = groupsCache.get(gid);		    
		    if(cachedGroup != null)
		    {
		    	// return the retrieved group
		    	Group group = (Group)cachedGroup.getObjectValue();
		    	
		    	try
				{
					// determine the url of the new resource
					URI location = new URI("/Group/" + group.getId());
					
					// group stored successfully, return the group				
					return Response.ok(group).location(location).build();
				}
				catch(URISyntaxException usException)
				{
					// problem generating entity location
					logger.error("problem generating entity location");
					
					// return a server error
					return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
				}
		    }
		    else
		    {
		    	// group not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
		    }		    				
		}
		else
		{
			// cache manager not configured
			logger.error("cache manager not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
		}
	}

	@Override
	public Response createGroup(UriInfo uriInfo, Group group)
	{
		// attempt to find the group within the cache
		if(manager != null)
		{
			// retrieve the groups cache
			if(groupsCache == null) groupsCache = manager.getEhcache(GROUPS_CACHE);
			
			// check a groupsCache is configured
			if(groupsCache == null)
			{
				// groupsCache not configured
				logger.error("groupsCache not configured");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager groupsCache not configured");
			}
			
			// check if the group already exists
			if(groupsCache.get(group.getId()) != null)
			{
				// user already exists				
				return ResourceUtilities.buildErrorResponse(HttpStatus.CONFLICT, HttpStatus.CONFLICT.getMessage() + ": Resource " + group.getId() + " already exists");
			}
			
			// store the group in the cache
			groupsCache.put(new Element(group.getId(), group));
			
			try
			{
				// determine the url of the new resource
				URI location = new URI("/Group/" + group.getId());
				
				// group stored successfully, return the group					
				return Response.created(location).entity(group).build();
			}
			catch(URISyntaxException usException)
			{
				// problem generating entity location
				logger.error("problem generating entity location");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
			}
		}
		else
		{
			// cache manager not configured
			logger.error("cache manager not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
		}
	}

	@Override
	public Response updateGroup(UriInfo uriInfo, String gid, Group group)
	{
		// attempt to find the group within the cache
		if(manager != null)
		{
			// retrieve the groups cache
			if(groupsCache == null) groupsCache = manager.getEhcache(GROUPS_CACHE);
			
			// check a groupsCache is configured
			if(groupsCache == null)
			{
				// groupsCache not configured
				logger.error("groupsCache not configured");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager groupsCache not configured");
			}
			
		    // check if the result is cached
		    Element cachedUser = groupsCache.get(gid);		    
		    if(cachedUser != null)
		    {
		    	// remove the retrieved group
		    	groupsCache.remove(gid);
		    	
		    	// update the with the new group
		    	groupsCache.put(new Element(group.getId(), group));
				
		    	try
				{
					// determine the url of the new resource
					URI location = new URI("/Group/" + group.getId());
					
					// group stored successfully, return the group				
					return Response.ok(group).location(location).build();
				}
				catch(URISyntaxException usException)
				{
					// problem generating entity location
					logger.error("problem generating entity location");
					
					// return a server error
					return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider problem generating entity location");
				}
		    }
		    else
		    {
		    	// group not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
		    }		    				
		}
		else
		{
			// cache manager not configured
			logger.error("cache manager not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
		}
	}

	@Override
	public Response deleteGroup(UriInfo uriInfo, String gid)
	{
		// attempt to find the group within the cache
		if(manager != null)
		{
			// retrieve the groups cache
			if(groupsCache == null) groupsCache = manager.getEhcache(GROUPS_CACHE);
			
			// check a groupsCache is configured
			if(groupsCache == null)
			{
				// groupsCache not configured
				logger.error("groupsCache not configured");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager groupsCache not configured");
			}
			
		    // check if the result is cached
		    Element cachedGroup = groupsCache.get(gid);		    
		    if(cachedGroup != null)
		    {
		    	// remove the retrieved group
		    	groupsCache.remove(gid);
		    	
		    	// group removed successfully
		    	return Response.ok().build();
		    }
		    else
		    {
		    	// group not found, return an error message
		    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + gid + " not found");
		    }		    				
		}
		else
		{
			// cache manager not configured
			logger.error("cache manager not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider group cache manager not configured");
		}
	}
}
