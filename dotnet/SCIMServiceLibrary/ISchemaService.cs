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

using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using SCIMCoreClassLibrary;

namespace SCIMServiceLibrary
{
    [ServiceContract]
    public interface ISchemaService
    {
        [WebGet(UriTemplate = "{resource}/{urn}")]
	    Response retrieveSchema(string resource, string urn);
	
        [WebGet(UriTemplate = "{resource}/{urn}.xml", ResponseFormat = WebMessageFormat.Xml)]
	    Response retrieveSchemaAsXML(string resource, string urn);

        [WebGet(UriTemplate = "{resource}/{urn}.json", ResponseFormat = WebMessageFormat.Json)]
	    Response retrieveSchemaAsJSON(string resource, string urn);
	
        [WebGet(UriTemplate = "{resource}/{urn}")]
	    Response retrieveSchema(string resource);
	
	    [WebGet(UriTemplate = "{resource}.xml", ResponseFormat = WebMessageFormat.Xml)]
	    Response retrieveSchemaAsXML(string resource);

        [WebGet(UriTemplate = "{resource}.json", ResponseFormat = WebMessageFormat.Json)]
	    Response retrieveSchemaAsJSON(string resource);
    }
}