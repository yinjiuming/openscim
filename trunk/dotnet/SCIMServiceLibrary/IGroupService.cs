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
    public interface IGroupService
    {
        [WebGet(UriTemplate = "{gid}")]
        Group retrieveGroup(string gid);

        [WebGet(UriTemplate = "{gid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Group retrieveGroupAsXML(string gid);

        [WebGet(UriTemplate = "{gid}.json", ResponseFormat = WebMessageFormat.Json)]
        Group retrieveGroupAsJSON(string gid);

        [WebInvoke(Method = "POST")]
        Group createGroup(Group group);

        [WebInvoke(Method = "POST", UriTemplate = ".xml", ResponseFormat = WebMessageFormat.Xml)]
        Group createGroupAsXML(Group group);

        [WebInvoke(Method = "POST", UriTemplate = ".json", ResponseFormat = WebMessageFormat.Json)]
        Group createGroupAsJSON(Group group);

        [WebInvoke(Method = "PUT", UriTemplate = "{gid}")]
        Group updateGroup(string gid, Group group);

        [WebInvoke(Method = "PUT", UriTemplate = "{gid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Group updateGroupAsXML(string gid, Group group);

        [WebInvoke(Method = "PUT", UriTemplate = "{gid}.json", ResponseFormat = WebMessageFormat.Json)]
        Group updateGroupAsJSON(string gid, Group group);

        [WebInvoke(Method = "DELETE", UriTemplate = "{gid}")]
        Group deleteGroup(string gid);

        [WebInvoke(Method = "DELETE", UriTemplate = "{gid}.xml", ResponseFormat = WebMessageFormat.Xml)]
        Group deleteGroupAsXML(string gid);

        [WebInvoke(Method = "DELETE", UriTemplate = "{gid}.json", ResponseFormat = WebMessageFormat.Json)]
        Group deleteGroupAsJSON(string gid);
    }
}