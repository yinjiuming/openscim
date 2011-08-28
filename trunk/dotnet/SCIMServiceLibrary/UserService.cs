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

using System.IO;
using System.Net;
using System.Runtime.Serialization.Json;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Xml.Serialization;
using Microsoft.Practices.EnterpriseLibrary.Caching;
using SCIMCoreClassLibrary;
using System.Xml;

namespace SCIMServiceLibrary
{
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    public class UserService : IUserService
    {
        // cache constants
        protected const string USERS_CACHE = "Users Cache Manager";

        // cache parameters
        protected ICacheManager usersCacheManager = null;

        public UserService()
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

            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // populate the id with the local id
            user.id = "uid=" + user.externalId + ",dc=hackerypokery,dc=com";

            // store the user in the cache
            usersCacheManager.Add(user.id, user);
        }

        private Stream serializeUserToXMLStream(User user, HttpStatusCode code)
        {
            // create a user serializer
            XmlSerializer serializer = new XmlSerializer(typeof(User));

            // create a user serializer stream
            MemoryStream stream = new MemoryStream();

            // encode the xml string using utf-8
            XmlTextWriter xmlTextWriter = new XmlTextWriter(stream, new System.Text.UTF8Encoding(false));

            // serialize the user to the stream
            //serializer.Serialize(stream, user);
            serializer.Serialize(xmlTextWriter, user);

            // retrieve a utf-8 formated user
            byte[] bytes = stream.GetBuffer();
            
            // set the content type to xml
            WebOperationContext.Current.OutgoingResponse.ContentType = "application/xml";

            // set the responses http status
            WebOperationContext.Current.OutgoingResponse.StatusCode = code;

            // return the utf-8 user stream
            return new MemoryStream(bytes);
        }

        private Stream serializeUserToJSONStream(User user, HttpStatusCode code)
        {
            // create a user serializer
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(User));

            // create a user serializer stream
            MemoryStream stream = new MemoryStream();

            // serialize the user to the stream
            serializer.WriteObject(stream, user);

            // retrieve a utf-8 formated user
            byte[] bytes = stream.GetBuffer();

            // set the content type to xml
            WebOperationContext.Current.OutgoingResponse.ContentType = "application/json";

            // set the responses http status
            WebOperationContext.Current.OutgoingResponse.StatusCode = code;

            // return the utf-8 user stream
            return new MemoryStream(bytes);
        }

        private Stream serializeErrorToXMLStream(Error error, HttpStatusCode code)
        {
            // create a error serializer
            XmlSerializer serializer = new XmlSerializer(typeof(Error));

            // create a error serializer stream
            MemoryStream stream = new MemoryStream();

            // encode the xml string using utf-8
            XmlTextWriter xmlTextWriter = new XmlTextWriter(stream, new System.Text.UTF8Encoding(false));

            // serialize the error to the stream
            //serializer.Serialize(stream, error);
            serializer.Serialize(xmlTextWriter, error);

            // retrieve a utf-8 formated error
            byte[] bytes = stream.GetBuffer();

            // set the content type to xml
            WebOperationContext.Current.OutgoingResponse.ContentType = "application/xml";

            // set the responses http status
            WebOperationContext.Current.OutgoingResponse.StatusCode = code;

            // return the utf-8 user stream
            return new MemoryStream(bytes);
        }

        private Stream serializeErrorToJSONStream(Error error, HttpStatusCode code)
        {
            // create a error serializer
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(Error));

            // create a error serializer stream
            MemoryStream stream = new MemoryStream();

            // serialize the error to the stream
            serializer.WriteObject(stream, error);

            // retrieve a utf-8 formated error
            byte[] bytes = stream.GetBuffer();

            // set the content type to xml
            WebOperationContext.Current.OutgoingResponse.ContentType = "application/json";

            // set the responses http status
            WebOperationContext.Current.OutgoingResponse.StatusCode = code;

            // return the utf-8 error stream
            return new MemoryStream(bytes);
        }

        public Stream retrieveUser(string uid)
        {
            return retrieveUserAsXML(uid);
        }

        public Stream retrieveUserAsXML(string uid)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the result is cached
            if (usersCacheManager.Contains(uid))
            {
                // return the retrieved user
                User user = (User)usersCacheManager.GetData(uid);
                return serializeUserToXMLStream(user, System.Net.HttpStatusCode.OK);
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }

        public Stream retrieveUserAsJSON(string uid)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the result is cached
            if (usersCacheManager.Contains(uid))
            {
                // return the retrieved user
                User user = (User)usersCacheManager.GetData(uid);
                return serializeUserToJSONStream(user, System.Net.HttpStatusCode.OK);
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }

        public Stream createUser(User user)
        {
            return createUserAsXML(user);
        }

        public Stream createUserAsXML(User user)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // populate the id with the local id
            user.id = "uid=" + user.externalId + ",dc=hackerypokery,dc=com";

            // store the user in the cache
            usersCacheManager.Add(user.id, user);

            // return the user with the created status
            return serializeUserToXMLStream(user, System.Net.HttpStatusCode.Created);
        }

        public Stream createUserAsJSON(User user)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // populate the id with the local id
            user.id = "uid=" + user.externalId + ",dc=hackerypokery,dc=com";

            // store the user in the cache
            usersCacheManager.Add(user.id, user);

            // return the user with the created status
            return serializeUserToJSONStream(user, System.Net.HttpStatusCode.Created);
        }

        public Stream updateUser(string uid, User user)
        {
            return updateUserAsXML(uid, user);
        }

        public Stream updateUserAsXML(string uid, User user)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the user is cached
            if (usersCacheManager.Contains(uid))
            {
                // remove the retrieved user
                usersCacheManager.Remove(uid);

                // update the with the new user
                usersCacheManager.Add(user.id, user);

                // return the user with the ok status
                return serializeUserToXMLStream(user, System.Net.HttpStatusCode.OK);
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }

        public Stream updateUserAsJSON(string uid, User user)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the user is cached
            if (usersCacheManager.Contains(uid))
            {
                // remove the retrieved user
                usersCacheManager.Remove(uid);

                // update the with the new user
                usersCacheManager.Add(user.id, user);

                // return the user with the ok status
                return serializeUserToJSONStream(user, System.Net.HttpStatusCode.OK);
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }

        public Stream deleteUser(string uid)
        {
            return deleteUserAsXML(uid);
        }

        public Stream deleteUserAsXML(string uid)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the user is cached
            if (usersCacheManager.Contains(uid))
            {
                // remove the retrieved user
                usersCacheManager.Remove(uid);

                // removed the user return the ok status
                
                // set the content type to xml
                WebOperationContext.Current.OutgoingResponse.ContentType = "application/xml";

                // set the responses http status
                WebOperationContext.Current.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.OK;

                // return the utf-8 error stream
                return new MemoryStream();
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToXMLStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }

        public Stream deleteUserAsJSON(string uid)
        {
            // retrieve the users cache manager
            if (usersCacheManager == null) usersCacheManager = CacheFactory.GetCacheManager(USERS_CACHE);

            // check a usersCacheManager is configured
            if (usersCacheManager == null)
            {
                // usersCacheManager not configured
                //logger.error("cache manager not configured");

                // build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.InternalServerError.ToString();
                error.description = "cache manager not configured";

                // return a server error
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.InternalServerError);
            }

            // check if the user is cached
            if (usersCacheManager.Contains(uid))
            {
                // remove the retrieved user
                usersCacheManager.Remove(uid);

                // removed the user return the ok status

                // set the content type to xml
                WebOperationContext.Current.OutgoingResponse.ContentType = "application/json";

                // set the responses http status
                WebOperationContext.Current.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.OK;

                // return the utf-8 error stream
                return new MemoryStream();
            }
            else
            {
                // user not found, build an error message
                Error error = new Error();
                error.code = System.Net.HttpStatusCode.NotFound.ToString();
                error.description = "user not found";

                // user not found, return an error message
                return serializeErrorToJSONStream(error, System.Net.HttpStatusCode.NotFound);
            }
        }
    }
}