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

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.wink.client.ClientRequest;
import org.apache.wink.client.handlers.OutputStreamAdapter;

/**
 * Based on the example provided at https://cwiki.apache.org/WINK/63-input-and-output-stream-adapters.html
 * 
 * @author Matthew Crooke <matthewcrooke@gmail.com>
 *
 */
public class GzipOutputAdapter implements OutputStreamAdapter
{
    public OutputStream adapt(OutputStream output, ClientRequest request)
    {
        try
        {
        	request.getHeaders().add("Content-Encoding", "gzip");
        	return new GZIPOutputStream(output);
        }
        catch(IOException ioException)
        {
        	request.getHeaders().remove("Content-Encoding");
        	return output;
        }
    }
}

