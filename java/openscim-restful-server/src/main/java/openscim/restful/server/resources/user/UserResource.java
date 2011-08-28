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
package openscim.restful.server.resources.user;

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

import openscim.entities.User;


@Path("/User")
public abstract class UserResource
{	
	@Path("{uid}")
	@GET		
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public abstract Response retrieveUser(@Context UriInfo uriInfo, @PathParam("uid") String uid);
		
	@Path("{uid}.xml")
	@GET		
	@Produces(MediaType.APPLICATION_XML)
	public Response retrieveUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{
		return retrieveUser(uriInfo, uid);
	}
	
	@Path("{uid}.json")
	@GET		
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{
		return retrieveUser(uriInfo, uid);
	}
		
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response createUser(@Context UriInfo uriInfo, User user);
	
	@POST
	@Path(".xml")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_XML)
	public Response createUserAsXML(@Context UriInfo uriInfo, User user)
	{
		return createUser(uriInfo, user);
	}
	
	@POST
	@Path(".json")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUserAsJSON(@Context UriInfo uriInfo, User user)
	{
		return createUser(uriInfo, user);
	}
	
	@Path("{uid}")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response updateUser(@Context UriInfo uriInfo, @PathParam("uid") String id, User user);

	@Path("{uid}.xml")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML})
	public Response updateUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String id, User user)
	{
		return updateUser(uriInfo, id, user);
	}
	
	@Path("{uid}.json")
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String id, User user)
	{
		return updateUser(uriInfo, id, user);
	}
	
	@Path("{uid}")
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response deleteUser(@Context UriInfo uriInfo, @PathParam("uid") String uid);
	
	@Path("{uid}.xml")
	@DELETE
	@Produces({MediaType.APPLICATION_XML})
	public Response deleteUserAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{
		return deleteUser(uriInfo, uid);
	}
	
	@Path("{uid}.json")
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteUserAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid)
	{
		return deleteUser(uriInfo, uid);
	}
	
	@Path("{uid}/password")
	@POST		
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response changePassword(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user);
		
	@Path("{uid}/password.xml")
	@POST	
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response changePasswordAsXML(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user)
	{
		return changePassword(uriInfo, uid, user);
	}
	
	@Path("{uid}/password.json")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePasswordAsJSON(@Context UriInfo uriInfo, @PathParam("uid") String uid, User user)
	{
		return changePassword(uriInfo, uid, user);
	}
}
