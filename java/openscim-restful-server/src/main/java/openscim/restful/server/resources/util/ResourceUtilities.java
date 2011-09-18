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
package openscim.restful.server.resources.util;

import javax.ws.rs.core.Response;

import openscim.entities.ObjectFactory;
import openscim.entities.Response.Errors;

import org.apache.wink.common.http.HttpStatus;


public class ResourceUtilities
{
	public static final ObjectFactory FACTORY = new ObjectFactory();
	
	public static Response buildErrorResponse(HttpStatus code, String message)
	{
		// create a response
		openscim.entities.Response response = ResourceUtilities.FACTORY.createResponse();
		
		// create a response errors
		Errors errors = ResourceUtilities.FACTORY.createResponseErrors();
		response.setErrors(errors);

		// create an unsupported operation error
		openscim.entities.Error error = ResourceUtilities.FACTORY.createError();
		error.setCode("" + code.getCode());
		error.setDescription(message);
		errors.getError().add(error);
		
		// build the not implemented response		
		return Response.status(code.getCode()).entity(response).build();
	}
}
