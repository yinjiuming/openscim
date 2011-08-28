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
package openscim.restful.server.resources.schema.ehcache;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import openscim.restful.server.resources.schema.SchemaResource;
import openscim.restful.server.resources.util.ResourceUtilities;
import openscim.xmlschema.Schema;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;


public class EhCacheSchemaResource extends SchemaResource
{
	// log4j contants
	private static Logger logger = Logger.getLogger(EhCacheSchemaResource.class);
	
	// cache constants
	protected static final String USER_SCHEMAS_CACHE = "usersSchemasCache";
	protected static final String GROUP_SCHEMAS_CACHE = "groupsSchemasCache";	
	
	// cache parameters
	protected CacheManager manager = null;
	protected Ehcache usersSchemasCache = null;
	protected Ehcache groupsSchemasCache = null;
	
	public void setCacheManager(CacheManager manager)
	{
		this.manager = manager;
	}
	
	public CacheManager getCacheManager()
	{
		return this.manager;
	}
	
	@Override
	public Response retrieveSchema(UriInfo uriInfo, String resource, String urn)
	{
		// attempt to find the schema within the cache
		if(manager != null)
		{
			// retrieve the schema cache
			if(usersSchemasCache == null) usersSchemasCache = manager.getEhcache(USER_SCHEMAS_CACHE);
			if(groupsSchemasCache == null) groupsSchemasCache = manager.getEhcache(GROUP_SCHEMAS_CACHE);
		    
			if(resource != null && resource.equalsIgnoreCase("user"))
			{
				// check a usersSchemasCache is configured
				if(usersSchemasCache == null)
				{
					// usersSchemasCache not configured
					logger.error("usersSchemasCache not configured");
					
					// return a server error
					return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider schema cache manager usersSchemasCache not configured");
				}
				
			    // check if the result is cached
			    Element cachedSchema = usersSchemasCache.get(resource + "-" + urn);		    
			    if(cachedSchema != null)
			    {
			    	// return the retrieved schema
			    	Schema schema = (Schema)cachedSchema.getObjectValue();
			    	return Response.ok(schema).build();
			    }
			    else
			    {
			    	// schema not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + urn + "#" + resource + " not found");
			    }
			}
			else if(resource != null && resource.equalsIgnoreCase("group"))
			{
				// check a groupsSchemasCache is configured
				if(groupsSchemasCache == null)
				{
					// groupsSchemasCache not configured
					logger.error("groupsSchemasCache not configured");
					
					// return a server error
					return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider schema cache manager groupsSchemasCache not configured");
				}
				
			    // check if the result is cached
			    Element cachedSchema = groupsSchemasCache.get(resource + "-" + urn);		    
			    if(cachedSchema != null)
			    {
			    	// return the retrieved schema
			    	Schema schema = (Schema)cachedSchema.getObjectValue();
			    	return Response.ok(schema).build();
			    }
			    else
			    {
			    	// schema not found, return an error message
			    	return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + urn + "#" + resource + " not found");
			    }
			}
			else
			{
				// resource type not found
				logger.error("resource type not found");
				
				// return a server error
				return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_FOUND, "Resource " + urn + "#" + resource + " not found");
			}		    				
		}
		else
		{
			// cache manager not configured
			logger.error("cache manager not configured");
			
			// return a server error
			return ResourceUtilities.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider schema cache manager not configured");
		}
	}
}
