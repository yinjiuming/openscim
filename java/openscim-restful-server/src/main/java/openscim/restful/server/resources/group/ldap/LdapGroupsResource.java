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
package openscim.restful.server.resources.group.ldap; 

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import openscim.restful.server.resources.group.GroupsResource;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.apache.log4j.Logger;
import org.apache.wink.common.http.HttpStatus;
import org.springframework.ldap.core.LdapTemplate;

public class LdapGroupsResource extends GroupsResource
{
	// log4j contants
	private static Logger logger = Logger.getLogger(LdapGroupsResource.class);
	
	// ldap parameters
	private LdapTemplate ldapTemplate = null;
	 
	public void setLdapTemplate(LdapTemplate ldapTemplate)
	{
		this.ldapTemplate = ldapTemplate;
	}
	
	@Override
	public Response listGroups(UriInfo uriInfo, String attributes,
			String filter, String sortBy, String sortOrder, int startIndex,
			int count)
	{
		// return an operation unsupported response
		return ResourceUtilities.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, HttpStatus.NOT_IMPLEMENTED.getMessage() + ": Service Provider does not support the list groups operation");
	}
}
