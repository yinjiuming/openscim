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
package openscim.restful.server.resources.group;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import openscim.entities.Group;


@Path("/Group")
public abstract class GroupResource
{	
	/**
	 * Retrieve the group {gid}
	 * 
	 * @param uriInfo
	 * @param gid
	 * @return
	 */
	@Path("{gid}")
	@GET		
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response retrieveGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid);
	
	/**
	 * Retrieve the group {gid} as XML
	 * 
	 * @param uriInfo
	 * @param gid
	 * @return
	 */
	@Path("{gid}.xml")
	@GET		
	@Produces(MediaType.APPLICATION_XML)
	public Response retrieveGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid)
	{
		return retrieveGroup(uriInfo, gid);
	}
	
	/**
	 * Retrieve the group {gid} as JSON
	 * 
	 * @param uriInfo
	 * @param gid
	 * @return
	 */
	@Path("{gid}.xml")
	@GET		
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid)
	{
		return retrieveGroup(uriInfo, gid);
	}
	
	/**
	 * Create the group with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param group
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response createGroup(@Context UriInfo uriInfo, Group group);
	
	/**
	 * Create the group with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param group
	 * @return
	 */
	@Path(".xml")
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_XML)
	public Response createGroupAsXML(@Context UriInfo uriInfo, Group group)
	{
		return createGroup(uriInfo, group);
	}
	
	/**
	 * Create the group with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param group
	 * @return
	 */
	@Path(".json")
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGroupAsJSON(@Context UriInfo uriInfo, Group group)
	{
		return createGroup(uriInfo, group);
	}
	
	/**
	 * Update the group {gid} with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param id
	 * @param group
	 * @return
	 */
	@Path("{gid}")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response updateGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group);
	
	/**
	 * Update the group {gid} with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param id
	 * @param group
	 * @return
	 */
	@Path("{gid}.json")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group)
	{
		return updateGroup(uriInfo, gid, group);
	}
	
	/**
	 * Update the group {gid} with the contents of {group}
	 * 
	 * @param uriInfo
	 * @param id
	 * @param group
	 * @return
	 */
	@Path("{gid}.xml")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_XML)
	public Response updateGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid, Group group)
	{
		return updateGroup(uriInfo, gid, group);
	}
	
	/**
	 * Delete the group {gid}
	 * 
	 * @param uriInfo
	 * @param id
	 * @return
	 */
	@Path("{gid}")
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response deleteGroup(@Context UriInfo uriInfo, @PathParam("gid") String gid);
	
	/**
	 * Delete the group {gid}
	 * 
	 * @param uriInfo
	 * @param id
	 * @return
	 */
	@Path("{gid}.json")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGroupAsXML(@Context UriInfo uriInfo, @PathParam("gid") String gid)
	{
		return deleteGroupAsXML(uriInfo, gid);
	}
	
	/**
	 * Delete the group {gid}
	 * 
	 * @param uriInfo
	 * @param id
	 * @return
	 */
	@Path("{gid}.xml")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteGroupAsJSON(@Context UriInfo uriInfo, @PathParam("gid") String gid)
	{
		return deleteGroupAsXML(uriInfo, gid);
	}
}
