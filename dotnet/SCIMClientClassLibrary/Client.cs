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

using System;
using System.IO;
using System.Net;
using System.Xml.Serialization;
using SCIMCoreClassLibrary;
using System.Xml;

namespace SCIMClientClassLibrary
{
    public class Client
    {
        private static string APPLICATION_XML_TYPE = "application/xml";
        private static string APPLICATION_JSON_TYPE = "application/json";

        private string url = null;
        private string username = null;
        private string password = null;

        public Client(string url, string username, string password)
        {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public Error getErrorFromResponse(XmlNodeReader xmlNodeReader)
        {
            // deserialize the error message
            XmlSerializer deserializer = new XmlSerializer(typeof(Error));
            Error error = (Error)deserializer.Deserialize(xmlNodeReader);
            return error;
        }

        public User getUserFromResponse(XmlNodeReader xmlNodeReader)
        {
            // deserialize the user message
            XmlSerializer deserializer = new XmlSerializer(typeof(User));
            User user = (User)deserializer.Deserialize(xmlNodeReader);
            return user;
        }

        public XmlNodeReader buildReaderFromResponse(HttpWebResponse response)
        {
            // get the response stream
            Stream stream = response.GetResponseStream();

            // create a stream reader
            StreamReader reader = new StreamReader(stream);

            // read the xml from the response
            string xml = reader.ReadToEnd();

            // create a utf-8 xml stream
            byte[] bytes = System.Text.UTF8Encoding.UTF8.GetBytes(xml); // ArgumentNullException, EncoderFallbackException
            MemoryStream xmlStream = new MemoryStream(bytes); // ArgumentNullException

            // create an xml doc
            XmlDocument document = new XmlDocument(); // XmlException, ArgumentNullException
            document.Load(xmlStream); // XmlException

            // attempt to build a ReconcileRequest from the request string
            XmlNodeReader xmlNodeReader = new XmlNodeReader(document.DocumentElement);
            return xmlNodeReader;
        }

        public HttpWebResponse createUser(User user)
	    {
            return createUser(user, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
	    }

        public HttpWebResponse createUser(User user, string requestType, string responseType)
        {
            try
            {
                // create a request to create the user
                Uri uri = new Uri(url + "/User");
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri) as HttpWebRequest;

                // set the request & response media types
                request.ContentType = requestType;
                request.Accept = responseType;

                // set the request method
                request.Method = "POST";

                // get the request stream to write the user
                Stream stream = request.GetRequestStream();

                // create a user serializer
                XmlSerializer serializer = new XmlSerializer(typeof(User));

                // serialize the user object to xml
                serializer.Serialize(stream, user);

                // send the create user request to the rest resource
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (WebException wException)
            {
                // get the response from the exception
                HttpWebResponse response = (HttpWebResponse)wException.Response;

                // return the error response
                return response;
            }
        }

        public HttpWebResponse retrieveUser(String uid)
        {
            return retrieveUser(uid, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public HttpWebResponse retrieveUser(String uid, string requestType, string responseType)
        {
            try
            {
                // create a resource to retrieve the user
                Uri uri = new Uri(url + "/User/" + uid);
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri) as HttpWebRequest;

                // set the request & response media types
                request.ContentType = requestType;
                request.Accept = responseType;

                // request the user from the rest resource
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (WebException wException)
            {
                // get the response from the exception
                HttpWebResponse response = (HttpWebResponse)wException.Response;

                // return the error response
                return response;
            }
        }

        public HttpWebResponse updateUser(User user)
        {
            return updateUser(user, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public HttpWebResponse updateUser(User user, string requestType, string responseType)
        {
            try
            {
                // create a resource to update the user
                Uri uri = new Uri(url + "/User/" + user.id);
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri) as HttpWebRequest;

                // set the request & response media types
                request.ContentType = requestType;
                request.Accept = responseType;

                // set the request method
                request.Method = "PUT";

                // get the request stream to write the user
                Stream stream = request.GetRequestStream();

                // create a user serializer
                XmlSerializer serializer = new XmlSerializer(typeof(User));

                // serialize the user object to xml
                serializer.Serialize(stream, user);

                // send the create user request to the rest resource
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (WebException wException)
            {
                // get the response from the exception
                HttpWebResponse response = (HttpWebResponse)wException.Response;

                // return the error response
                return response;
            }
        }

        public HttpWebResponse deleteUser(String uid)
        {
            return deleteUser(uid, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public HttpWebResponse deleteUser(String uid, string requestType, string responseType)
        {
            try
            {
                // create a resource to delete the user
                Uri uri = new Uri(url + "/User/" + uid);
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri) as HttpWebRequest;

                // set the request & response media types
                request.ContentType = requestType;
                request.Accept = responseType;

                // set the request method
                request.Method = "DELETE";

                // send the delete user request to the rest resource        
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (WebException wException)
            {
                // get the response from the exception
                HttpWebResponse response = (HttpWebResponse)wException.Response;

                // return the error response
                return response;
            }
        }

        public HttpWebResponse listUsers(String attributes, String filter, String sortBy, String sortOrder, int startIndex, int count)
        {
            return listUsers(attributes, filter, sortBy, sortOrder, startIndex, count, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public HttpWebResponse listUsers(String attributes, String filter, String sortBy, String sortOrder, int startIndex, int count, string requestType, string responseType)
        {
            try
            {
                // build the list users query string
                String query = null;

                // create a resource to list the users            
                Uri uri = new Uri(url + "/Users/" + query);
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri) as HttpWebRequest;

                // set the request & response media types
                request.ContentType = requestType;
                request.Accept = responseType;

                // send the create users request to the rest resource
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                return response;
            }
            catch (WebException wException)
            {
                // get the response from the exception
                HttpWebResponse response = (HttpWebResponse)wException.Response;

                // return the error response
                return response;
            }
        }

        public WebResponse createGroup(Group group)
        {
            return createGroup(group, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public WebResponse createGroup(Group group, string requestType, string responseType)
        {
            // create a request to create the group
            Uri uri = new Uri(url + "/Group");
            WebRequest request = WebRequest.Create(uri);

            // set the request & response media types
            request.ContentType = requestType;
            request.Headers.Add("Accept: " + responseType);

            // get the request stream to write the group
            Stream stream = request.GetRequestStream();

            // create a group serializer
            XmlSerializer serializer = new XmlSerializer(typeof(Group));

            // serialize the group object to xml
            serializer.Serialize(stream, group);

            // send the create group request to the rest resource
            WebResponse response = request.GetResponse();

            return response;
        }

        public WebResponse retrieveGroup(String gid)
        {
            return retrieveGroup(gid, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public WebResponse retrieveGroup(String gid, string requestType, string responseType)
        {
            // create a resource to retrieve the group
            Uri uri = new Uri(url + "/Group/" + gid);
            WebRequest request = WebRequest.Create(uri);

            // set the request & response media types
            request.ContentType = requestType;
            request.Headers.Add("Accept: " + responseType);

            // request the group from the resource
            WebResponse response = request.GetResponse();

            return response;
        }

        public WebResponse updateGroup(Group group)
        {
            return updateGroup(group, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public WebResponse updateGroup(Group group, string requestType, string responseType)
        {
            // create a resource to update the group
            Uri uri = new Uri(url + "/Group/" + group.id);
            WebRequest request = WebRequest.Create(uri);

            // set the request & response media types
            request.ContentType = requestType;
            request.Headers.Add("Accept: " + responseType);

            // get the request stream to write the group
            Stream stream = request.GetRequestStream();

            // create a user serializer
            XmlSerializer serializer = new XmlSerializer(typeof(Group));

            // serialize the group object to xml
            serializer.Serialize(stream, group);

            // send the create group request to the rest resource
            request.Method = "POST";
            WebResponse response = request.GetResponse();

            return response;
        }

        public WebResponse deleteGroup(String gid)
        {
            return deleteGroup(gid, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public WebResponse deleteGroup(String gid, string requestType, string responseType)
        {
            // create a resource to delete the group
            Uri uri = new Uri(url + "/Group/" + gid);
            WebRequest request = WebRequest.Create(uri);

            // set the request & response media types
            request.ContentType = requestType;
            request.Headers.Add("Accept: " + responseType);

            // send the delete user request to the rest resource
            request.Method = "DELETE";
            WebResponse response = request.GetResponse();

            return response;
        }

        public WebResponse listGroups(String attributes, String filter, String sortBy, String sortOrder, int startIndex, int count)
        {
            return listGroups(attributes, filter, sortBy, sortOrder, startIndex, count, APPLICATION_XML_TYPE, APPLICATION_XML_TYPE);
        }

        public WebResponse listGroups(String attributes, String filter, String sortBy, String sortOrder, int startIndex, int count, string requestType, string responseType)
        {
            // build the list groups query string
            String query = null;

            // create a resource to list the groups
            Uri uri = new Uri(url + "/Groups/" + query);
            WebRequest request = WebRequest.Create(uri);

            // set the request & response media types
            request.ContentType = requestType;
            request.Headers.Add("Accept: " + responseType);

            // send the create groups request to the rest resource
            WebResponse response = request.GetResponse();

            return response;
        }
    }
}
