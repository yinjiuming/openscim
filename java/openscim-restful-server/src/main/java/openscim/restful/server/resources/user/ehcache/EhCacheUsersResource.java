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
package openscim.restful.server.resources.user.ehcache;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import openscim.entities.ObjectFactory;
import openscim.entities.Response.Errors;
import openscim.restful.server.resources.user.UsersResource;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;


public class EhCacheUsersResource extends UsersResource 
{
	// log4j contants
	private static Logger logger = Logger.getLogger(EhCacheUsersResource.class);

	// cache constants
	protected static final String USERS_CACHE = "usersCache";
	
	// cache parameters
	protected CacheManager manager = null;
	protected Ehcache usersCache = null;
	
	public void setCacheManager(CacheManager manager)
	{
		this.manager = manager;
	}
	
	public CacheManager getCacheManager()
	{
		return this.manager;
	}
	
	@Override
	public Response listUsers(UriInfo uriInfo, String attributes, String filter,
			String sortBy, String sortOrder, int startIndex, int count)
	{
		// return an operation unsupported response
		return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider does not support the list users operation");
	}
}
