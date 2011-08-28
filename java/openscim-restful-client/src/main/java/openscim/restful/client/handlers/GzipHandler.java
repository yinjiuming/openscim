/*
 * openscim restful scim client
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
package openscim.restful.client.handlers;

import org.apache.wink.client.ClientRequest;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.handlers.ClientHandler;
import org.apache.wink.client.handlers.HandlerContext;

/**
 * Based on the example provided at https://cwiki.apache.org/WINK/63-input-and-output-stream-adapters.html
 * 
 * @author Matthew Crooke <matthewcrooke@gmail.com>
 *
 */
public class GzipHandler implements ClientHandler
{
	   public ClientResponse handle(ClientRequest request, HandlerContext context) throws Exception
	   {
	        request.getHeaders().add("Accept-Encoding", "gzip");
	        context.addInputStreamAdapter(new GzipInputAdapter());
	        context.addOutputStreamAdapter(new GzipOutputAdapter());
	        return context.doChain(request);
	   }
}

