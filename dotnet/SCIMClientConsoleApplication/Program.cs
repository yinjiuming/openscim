/*
 * openscim restful scim client console
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
using System.Text;
using System;
using System.IO;
using System.Net;
using System.Xml.Serialization;
using SCIMCoreClassLibrary;
using SCIMClientClassLibrary;
using System.Xml;

namespace SCIMClientConsoleApplication
{
    class Program
    {
        public static String createUserTest(Client client)
        {
            // create a test user
            User user = new User();
            user.userName = "mcrooke";
            user.externalId = "mcrooke";
            user.displayName = "Mr Matt Crooke";

            // set the user names
            user.name = new Name();
            user.name.givenName = "Matt";
            user.name.familyName = "Crooke";
            user.name.formatted = "Mr Matt Crooke";

            // set the user emails
            PluralAttribute email = new PluralAttribute();
            email.primary = true;
            email.value = "matthewcrooke@gmail.com";
            PluralAttribute[] emails = new PluralAttribute[] { email };
            user.emails = emails;

            // send the request to create the user
            HttpWebResponse response = client.createUser(user);

            // verify the response code indicates success
            //int status =  response.getStatusCode();
            //assert(status == 201);

            // retrieve the location of the user
            //String location = response.getHeaders().getFirst("Location");	    

            // build a nide reader from the response
            XmlNodeReader xmlNodeReader = client.buildReaderFromResponse(response);

            try
            {
                // get the user object from the response
                user = client.getUserFromResponse(xmlNodeReader);

                Console.WriteLine("created user - " + user.externalId + " as " + user.id);
                return user.id;
            }
            catch (InvalidOperationException ioException)
            {
                // InvalidOperationException is throw when a user cannot be deserialized

                // get the error object from the response
                Error error = client.getErrorFromResponse(xmlNodeReader);

                Console.WriteLine("user cannot be created, error returned - " + error.code + " " + error.description);
                return null;
            }

            /*
            // get the response stream to read the user
            Stream stream = response.GetResponseStream();

            // create a user deserializer
            XmlSerializer deserializer = new XmlSerializer(typeof(User));

            // retrieve the returned user entry
            user = (User)deserializer.Deserialize(stream);

            // retrieve the id of the user
            String id = user.id;

            Console.WriteLine("created user - " + user.externalId + " as " + user.id);

            return id;
            */
        }

        public static void retrieveUserTest(Client client, String uid)
        {
            // send the request to retrieve the user
            HttpWebResponse response = client.retrieveUser(uid);

            // build a node reader from the response
            XmlNodeReader xmlNodeReader = client.buildReaderFromResponse(response);

            try
            {
                // get the user object from the response
                User user = client.getUserFromResponse(xmlNodeReader);

                Console.WriteLine("retrieved user - " + user.id);
            }
            catch (InvalidOperationException ioException)
            {
                // InvalidOperationException is throw when a user cannot be deserialized

                // get the error object from the response
                Error error = client.getErrorFromResponse(xmlNodeReader);

                Console.WriteLine("user cannot be retrieved, error returned - " + error.code + " " + error.description);
            }
        }

        public static void updateUserTest(Client client, String uid)
        {
            // send the request to retrieve the user
            HttpWebResponse response = client.retrieveUser(uid);

            // build a node reader from the response
            XmlNodeReader xmlNodeReader = client.buildReaderFromResponse(response);

            try
            {
                // verify the response code indicates success
                //int status =  response.getStatusCode();
                //assert(status == 200);

                /*
                // get the response stream to read the user
                Stream stream = response.GetResponseStream();

                // create a user deserializer
                XmlSerializer deserializer = new XmlSerializer(typeof(User));

                // retrieve the returned user entry
                User user = (User)deserializer.Deserialize(stream);
                */

                // get the user object from the response
                User user = client.getUserFromResponse(xmlNodeReader);

                // update the users name
                user.name.givenName = "Matthew";
                user.name.familyName = "Crooke";
                user.nickName = "Matt";
                user.name.formatted = "Mr Matthew Crooke";

                // update the users groups            
                PluralAttribute group = new PluralAttribute();
                group.value = "gid=nerds,ou=groups,dc=hackerypokery,dc=com";
                PluralAttribute[] memberOfs = new PluralAttribute[] { group };
                user.memberOf = memberOfs;

                // send the request to update the user
                response = client.updateUser(user);

                // verify the response code indicates success
                //status =  response.getStatusCode();
                //assert(status == 200);

                /*
                // get the response stream to update user
                stream = response.GetResponseStream();

                // retrieve the returned user entry
                user = (User)deserializer.Deserialize(stream);
                */

                // build a node reader from the response
                xmlNodeReader = client.buildReaderFromResponse(response);

                // get the user object from the response
                user = client.getUserFromResponse(xmlNodeReader);

                Console.WriteLine("updated user - " + user.id);
            }
            catch (InvalidOperationException ioException)
            {
                // InvalidOperationException is throw when a user cannot be deserialized

                // get the error object from the response
                Error error = client.getErrorFromResponse(xmlNodeReader);

                Console.WriteLine("user cannot be updated, error returned - " + error.code + " " + error.description);
            }           
        }

        public static void deleteUserTest(Client client, String uid)
        {
            // send the request to delete the user
            HttpWebResponse response = client.deleteUser(uid);

            try
            {
                // verify the response code indicates success
                if(HttpStatusCode.OK.Equals(response.StatusCode))
                {
                    Console.WriteLine("deleted user - " + uid);
                }
                else
                {
                    Console.WriteLine("user was not deleted - " + uid);
                }
            }
            catch (InvalidOperationException ioException)
            {
                // InvalidOperationException is throw when a user cannot be deserialized

                // build a node reader from the response
                XmlNodeReader xmlNodeReader = client.buildReaderFromResponse(response);

                // get the error object from the response
                Error error = client.getErrorFromResponse(xmlNodeReader);

                Console.WriteLine("user cannot be deleted, error returned - " + error.code + " " + error.description);
            }
        }

        public static String createGroupTest(Client client)
        {
            // create a test group
            Group group = new Group();
            group.id = "gid=nerds,ou=groups,dc=hackerypokery,dc=com";

            // send the request to create the group
            WebResponse response = client.createGroup(group);

            // verify the response code indicates success
            //int status =  response.getStatusCode();
            //assert(status == 201);

            // retrieve the location of the group
            //String location = response.getHeaders().getFirst("Location");	    

            // get the response stream to read the group
            Stream stream = response.GetResponseStream();

            // create a group deserializer
            XmlSerializer deserializer = new XmlSerializer(typeof(Group));

            // retrieve the returned group entry
            group = (Group)deserializer.Deserialize(stream);

            Console.WriteLine("created group - " + group.id);

            return group.id;
        }

        public static void retrieveGroupTest(Client client, String gid)
        {
            // send the request to retrieve the group
            WebResponse response = client.retrieveGroup(gid);

            // verify the response code indicates success
            //int status =  response.getStatusCode();
            //assert(status == 200);

            // get the response stream to read the group
            Stream stream = response.GetResponseStream();

            // create a group deserializer
            XmlSerializer deserializer = new XmlSerializer(typeof(Group));

            // retrieve the returned group entry
            Group group = (Group)deserializer.Deserialize(stream);

            Console.WriteLine("retrieved group - " + group.id);
        }

        public static void updateGroupTest(Client client, String gid)
        {
            // send the request to retrieve the group
            WebResponse response = client.retrieveGroup(gid);

            // verify the response code indicates success
            //int status =  response.getStatusCode();
            //assert(status == 200);

            // get the response stream to read the group
            Stream stream = response.GetResponseStream();

            // create a group deserializer
            XmlSerializer deserializer = new XmlSerializer(typeof(Group));

            // retrieve the returned group entry
            Group group = (Group)deserializer.Deserialize(stream);

            // update the group
            group.meta = new Meta();
            group.meta.version = "2.00";

            // send the request to update the group
            response = client.updateGroup(group);

            // verify the response code indicates success
            //status =  response.getStatusCode();
            //assert(status == 200);

            // get the response stream to read the group
            stream = response.GetResponseStream();

            // retrieve the returned user entry
            group = (Group)deserializer.Deserialize(stream);

            Console.WriteLine("updated group - " + group.id);
        }

        public static void deleteGroupTest(Client client, String gid)
        {
            // send the request to delete the group
            WebResponse response = client.deleteGroup(gid);

            // verify the response code indicates success
            //int status =  response.getStatusCode();
            //assert(status == 200);

            Console.WriteLine("deleted group - " + gid);
        }

        static void Main(string[] args)
        {
            // create a client to send the user/group crud requests
            Client client = new Client("http://localhost:80/scim", null, null);

            // test the create user functionality
            String gid = createGroupTest(client);
            //String gid = "gid=nerds,ou=groups,dc=hackerypokery,dc=com";

            // test the retrieve group functionality
            retrieveGroupTest(client, gid);

            // test the update group functionality
            updateGroupTest(client, gid);

            // test the create user functionality
            String uid = createUserTest(client);
            //String uid = "uid=mcrooke,dc=hackerypokery,dc=com";

            // test the retrieve user functionality
            retrieveUserTest(client, uid);

            // test the update user functionality
            updateUserTest(client, uid);

            // test the delete user functionality
            deleteUserTest(client, uid);

            // test the delete group functionality
            deleteGroupTest(client, gid);

            Console.ReadKey();
        }
    }
}
