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

using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Text;
using System.Runtime.Serialization.Json;
using System.Net;
using System.IO;
using SCIMCoreClassLibrary;
using Microsoft.Practices.EnterpriseLibrary.Caching;
using Microsoft.Practices.EnterpriseLibrary.Common;

namespace SCIMServiceLibrary
{
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    public class SchemaService : ISchemaService
    {
        public Response retrieveSchema(string resource, string urn)
        {
            return null;
        }

        public Response retrieveSchemaAsXML(string resource, string urn)
        {
            return retrieveSchema(resource, urn);
        }

        public Response retrieveSchemaAsJSON(string resource, string urn)
        {
            return retrieveSchema(resource, urn);
        }

        public Response retrieveSchema(string resource)
        {
            return null;
        }

        public Response retrieveSchemaAsXML(string resource)
        {
            return retrieveSchema(resource);
        }

        public Response retrieveSchemaAsJSON(string resource)
        {
            return retrieveSchema(resource);
        }
    }
}