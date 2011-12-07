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
package openscim.restful.server.resources.schema;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/Schema")
public abstract class SchemaResource
{
	// cache constants
	protected static final String CORE_SCHEMA = "urn:scim:schemas:core:1.0";
	
	/**
	 * Retrieve the schema for {resource} of {urn}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}/{urn}")
	@GET		
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public abstract Response retrieveSchema(@Context UriInfo uriInfo, @PathParam("resource") String resource, @PathParam("urn") String urn);
	
	/**
	 * Retrieve the schema for {resource} of {urn}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}/{urn}.xml")
	@GET		
	@Produces(MediaType.APPLICATION_XML)
	public Response retrieveSchemaAsXML(@Context UriInfo uriInfo, @PathParam("resource") String resource, @PathParam("urn") String urn)
	{
		return retrieveSchema(uriInfo, resource, urn);
	}

	/**
	 * Retrieve the schema for {resource} of {urn}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}/{urn}.json")
	@GET		
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveSchemaAsJSON(@Context UriInfo uriInfo, @PathParam("resource") String resource, @PathParam("urn") String urn)
	{
		return retrieveSchema(uriInfo, resource, urn);
	}
	
	/**
	 * Retrieve the core schema for {resource}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}")
	@GET		
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response retrieveSchema(@Context UriInfo uriInfo, @PathParam("resource") String resource)
	{
		return retrieveSchema(uriInfo, resource, CORE_SCHEMA);
	}
	
	/**
	 * Retrieve the core schema for {resource}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}.xml")
	@GET		
	@Produces(MediaType.APPLICATION_XML)
	public Response retrieveSchemaAsXML(@Context UriInfo uriInfo, @PathParam("resource") String resource)
	{
		return retrieveSchema(uriInfo, resource);
	}
	
	/**
	 * Retrieve the core schema for {resource}
	 * 
	 * @param uriInfo
	 * @param resource
	 * @param urn
	 * @return
	 */
	@Path("{resource}.json")
	@GET		
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveSchemaAsJSON(@Context UriInfo uriInfo, @PathParam("resource") String resource)
	{
		return retrieveSchema(uriInfo, resource);
	}
}
