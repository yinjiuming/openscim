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
using System.IO;
using SCIMCoreClassLibrary;

namespace SCIMServiceLibrary
{
    [ServiceContract]
    public interface IUserService
    {
        [WebGet(UriTemplate = "{uid}")]
        Stream retrieveUser(string uid);

        [WebGet(UriTemplate = "{uid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Stream retrieveUserAsXML(string uid);

        [WebGet(UriTemplate = "{uid}.json", ResponseFormat = WebMessageFormat.Json)]
        Stream retrieveUserAsJSON(string uid);

        [WebInvoke(Method = "POST", RequestFormat = WebMessageFormat.Xml, ResponseFormat = WebMessageFormat.Xml)]
        Stream createUser(User user);

        [WebInvoke(Method = "POST", UriTemplate = ".xml", ResponseFormat = WebMessageFormat.Xml)]
        Stream createUserAsXML(User user);

        [WebInvoke(Method = "POST", UriTemplate = ".json", ResponseFormat = WebMessageFormat.Json)]
        Stream createUserAsJSON(User user);

        [WebInvoke(Method = "PUT", UriTemplate = "{uid}", RequestFormat = WebMessageFormat.Xml, ResponseFormat = WebMessageFormat.Xml)]
        Stream updateUser(string uid, User user);

        [WebInvoke(Method = "PUT", UriTemplate = "{uid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Stream updateUserAsXML(string uid, User user);

        [WebInvoke(Method = "PUT", UriTemplate = "{uid}.json", ResponseFormat = WebMessageFormat.Json)]
        Stream updateUserAsJSON(string uid, User user);

        [WebInvoke(Method = "DELETE", UriTemplate = "{uid}")]
        Stream deleteUser(string uid);

        [WebInvoke(Method = "DELETE", UriTemplate = "{uid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Stream deleteUserAsXML(string uid);

        [WebInvoke(Method = "DELETE", UriTemplate = "{uid}.json", ResponseFormat = WebMessageFormat.Json)]
        Stream deleteUserAsJSON(string uid);
    }
}