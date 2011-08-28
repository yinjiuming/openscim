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
using System.ServiceModel.Activation;
using System.Web;
using System.Web.Routing;

namespace SCIMServiceLibrary
{
    public class Global : HttpApplication
    {
        void Application_Start(object sender, EventArgs e)
        {
            RegisterRoutes();
        }

        private void RegisterRoutes()
        {
            // register the User(s) services
            RouteTable.Routes.Add(new ServiceRoute("User", new WebServiceHostFactory(), typeof(UserService)));
            RouteTable.Routes.Add(new ServiceRoute("Users", new WebServiceHostFactory(), typeof(UsersService)));

            // register the Group(s) services
            RouteTable.Routes.Add(new ServiceRoute("Group", new WebServiceHostFactory(), typeof(GroupService)));
            RouteTable.Routes.Add(new ServiceRoute("Groups", new WebServiceHostFactory(), typeof(GroupsService)));

            // register the Schema(s) services
            RouteTable.Routes.Add(new ServiceRoute("Schema", new WebServiceHostFactory(), typeof(SchemaService)));
            RouteTable.Routes.Add(new ServiceRoute("Schemas", new WebServiceHostFactory(), typeof(SchemasService)));

            // Edit the base address of Service1 by replacing the "Service1" string below
            //RouteTable.Routes.Add(new ServiceRoute("Service1", new WebServiceHostFactory(), typeof(Service1)));
        }
    }
}
