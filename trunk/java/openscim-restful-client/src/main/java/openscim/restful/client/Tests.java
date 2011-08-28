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
package openscim.restful.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import openscim.entities.Error;
import openscim.entities.Group;
import openscim.entities.Meta;
import openscim.entities.Name;
import openscim.entities.ObjectFactory;
import openscim.entities.PluralAttribute;
import openscim.entities.Response;
import openscim.entities.User;
import openscim.entities.Response.Errors;
import openscim.entities.User.Emails;
import openscim.entities.User.MemberOf;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.common.http.HttpStatus;


public class Tests
{
	public static String createUserTest(Client client, ObjectFactory factory)
	{
		// create a test user
		User user = factory.createUser();		
	    user.setUserName("mcrooke");
	    user.setExternalId("mcrooke");
	    user.setDisplayName("Mr Matt Crooke");
	    
	    // set the user names
	    Name name = factory.createName();
	    name.setGivenName("Matt");
	    name.setFamilyName("Crooke");
	    name.setFormatted("Mr Matt Crooke");	    
	    user.setName(name);
	    
	    // set the user emails
	    Emails emails = factory.createUserEmails();
	    PluralAttribute email = factory.createPluralAttribute();
	    email.setPrimary(true);
	    email.setValue("matthewcrooke@gmail.com");
	    emails.getEmail().add(email);
	    user.setEmails(emails);
	    
	    // send the request to create the user
	    ClientResponse response = client.createUser(user);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CREATED.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    user = response.getEntity(User.class);
	    
	    // retrieve the id of the user
	    String id = user.getId();
	    
	    System.out.println("created user - " + user.getExternalId() + " as " + user.getId());
	    
	    return id;
	}
	
	public static String createUserAsJSONTest(Client client, ObjectFactory factory)
	{
		// create a test user
		User user = factory.createUser();		
	    user.setUserName("mcrooke");
	    user.setExternalId("mcrooke");
	    user.setDisplayName("Mr Matt Crooke");
	    
	    // set the user names
	    Name name = factory.createName();
	    name.setGivenName("Matt");
	    name.setFamilyName("Crooke");
	    name.setFormatted("Mr Matt Crooke");	    
	    user.setName(name);
	    
	    // set the user emails
	    Emails emails = factory.createUserEmails();
	    PluralAttribute email = factory.createPluralAttribute();
	    email.setPrimary(true);
	    email.setValue("matthewcrooke@gmail.com");
	    emails.getEmail().add(email);
	    user.setEmails(emails);
	    
	    // send the request to create the user
	    ClientResponse response = client.createUser(user, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CREATED.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    user = response.getEntity(User.class);
	    
	    // retrieve the id of the user
	    String id = user.getId();
	    
	    System.out.println("created user - " + user.getExternalId() + " as " + user.getId());
	    
	    return id;
	}
	
	public static void createUserErrorTest(Client client, ObjectFactory factory)
	{
		// create a test user
		User user = factory.createUser();		
	    user.setUserName("mcrooke");
	    user.setExternalId("mcrooke");
	    user.setDisplayName("Mr Matt Crooke");
	    
	    // set the user names
	    Name name = factory.createName();
	    name.setGivenName("Matt");
	    name.setFamilyName("Crooke");
	    name.setFormatted("Mr Matt Crooke");	    
	    user.setName(name);
	    
	    // set the user emails
	    Emails emails = factory.createUserEmails();
	    PluralAttribute email = factory.createPluralAttribute();
	    email.setPrimary(true);
	    email.setValue("matthewcrooke@gmail.com");
	    emails.getEmail().add(email);
	    user.setEmails(emails);
	    
	    // send the request to create the user
	    ClientResponse response = client.createUser(user);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CONFLICT.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("create user returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void retrieveUserTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    User user = response.getEntity(User.class);
	    
	    System.out.println("retrieved user - " + user.getId());
	}
	
	public static void retrieveUserAsJSONTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    User user = response.getEntity(User.class);
	    
	    System.out.println("retrieved user - " + user.getId());
	}
	
	public static void retrieveUserErrorTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("retrieve user returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void updateUserTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    User user = response.getEntity(User.class);
	    
	    // update the users name
	    Name name = user.getName();
	    name.setGivenName("Matthew");
	    name.setFormatted("Mr Matthew Crooke");
	    user.setNickName("Matt");
	    user.setDisplayName("Mr Matthew Crooke");
	    
	    // update the users groups
	    MemberOf memberof = factory.createUserMemberOf();
	    PluralAttribute group = factory.createPluralAttribute();
	    group.setValue("gid=nerds,ou=groups,dc=hackerypokery,dc=com");
	    memberof.getGroup().add(group);
	    user.setMemberOf(memberof);
	    
	    // send the request to update the user
		response = client.updateUser(user);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    user = response.getEntity(User.class);
	    
	    System.out.println("updated user - " + user.getId());	    
	}
	
	public static void updateUserAsJSONTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    User user = response.getEntity(User.class);
	    
	    // update the users name
	    Name name = user.getName();
	    name.setGivenName("Matthew");
	    name.setFormatted("Mr Matthew Crooke");
	    user.setNickName("Matt");
	    user.setDisplayName("Mr Matthew Crooke");
	    
	    // update the users groups
	    MemberOf memberof = factory.createUserMemberOf();
	    PluralAttribute group = factory.createPluralAttribute();
	    group.setValue("gid=nerds,ou=groups,dc=hackerypokery,dc=com");
	    memberof.getGroup().add(group);
	    user.setMemberOf(memberof);
	    
	    // send the request to update the user
		response = client.updateUser(user);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    user = response.getEntity(User.class);
	    
	    System.out.println("updated user - " + user.getId());	    
	}
	
	public static void overrideUpdateUserTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to retrieve the user
		ClientResponse response = client.retrieveUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    User user = response.getEntity(User.class);
	    
	    // update the users name
	    Name name = user.getName();
	    name.setGivenName("Matthew");
	    name.setFormatted("Mr Matthew Crooke");
	    user.setNickName("Matt");
	    user.setDisplayName("Mr Matthew Crooke");
	    
	    // update the users groups
	    MemberOf memberof = factory.createUserMemberOf();
	    PluralAttribute group = factory.createPluralAttribute();
	    group.setValue("gid=nerds,ou=groups,dc=hackerypokery,dc=com");
	    memberof.getGroup().add(group);
	    user.setMemberOf(memberof);
	    
	    // send the request to update the user
		response = client.overrideUpdateUser(user);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(User.class) instanceof User);
	    
	    // retrieve the returned user entry
	    user = response.getEntity(User.class);
	    
	    System.out.println("updated user - " + user.getId());	    
	}
	
	public static void updateUserErrorTest(Client client, ObjectFactory factory, String uid)
	{	    
	    // create a test user
		User user = factory.createUser();		
	    user.setUserName("unknown");
	    user.setExternalId("unknown");
	    user.setId(uid);
	    
	    // send the request to update the user
		ClientResponse response = client.updateUser(user);
	    
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("update user returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void deleteUserTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to delete the user
		ClientResponse response = client.deleteUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    
	    System.out.println("deleted user - " + uid);
	}
	
	public static void overrideDeleteUserTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to delete the user
		ClientResponse response = client.overrideDeleteUser(uid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    
	    System.out.println("deleted user - " + uid);
	}
	
	public static void deleteUserErrorTest(Client client, ObjectFactory factory, String uid)
	{
		// send the request to delete the user
		ClientResponse response = client.deleteUser(uid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("delete user returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static String createGroupTest(Client client, ObjectFactory factory)
	{
		// create a test group
		Group group = factory.createGroup();
		group.setId("gid=nerds,ou=groups,dc=hackerypokery,dc=com");			   
	    
	    // send the request to create the group
	    ClientResponse response = client.createGroup(group);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CREATED.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);	    
	    
	    // retrieve the returned group entry
	    group = response.getEntity(Group.class);
	    
	    // retrieve the id of the group
	    String id = group.getId();
	    
	    System.out.println("created group - " + group.getId());
	    
	    return id;
	}
	
	public static String createGroupAsJSONTest(Client client, ObjectFactory factory)
	{
		// create a test group
		Group group = factory.createGroup();
		group.setId("gid=nerds,ou=groups,dc=hackerypokery,dc=com");			   
	    
	    // send the request to create the group
	    ClientResponse response = client.createGroup(group, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CREATED.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);	    
	    
	    // retrieve the returned group entry
	    group = response.getEntity(Group.class);
	    
	    // retrieve the id of the group
	    String id = group.getId();
	    
	    System.out.println("created group - " + group.getId());
	    
	    return id;
	}
	
	public static void createGroupErrorTest(Client client, ObjectFactory factory)
	{
		// create a test group
		Group group = factory.createGroup();
		group.setId("gid=nerds,ou=groups,dc=hackerypokery,dc=com");			   
	    
	    // send the request to create the group
	    ClientResponse response = client.createGroup(group);
	    
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.CONFLICT.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("create group returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void retrieveGroupTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a group
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned group entry
	    Group group = response.getEntity(Group.class);
	    
	    System.out.println("retrieved group - " + group.getId());
	}
	
	public static void retrieveGroupAsJSONTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a group
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned group entry
	    Group group = response.getEntity(Group.class);
	    
	    System.out.println("retrieved group - " + group.getId());
	}
	
	
	public static void retrieveGroupErrorTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid);
		
	    // verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("retrieve group returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void updateGroupTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned group entry
	    Group group = response.getEntity(Group.class);
	    
	    // update the group
	    Meta meta = factory.createMeta();
	    meta.setVersion("2.00");
	    group.setMeta(meta);
	    
	    // send the request to update the group
		response = client.updateGroup(group);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned user entry
	    group = response.getEntity(Group.class);
	    
	    System.out.println("updated group - " + group.getId());	    
	}
	
	public static void updateGroupAsJSONTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid, MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned group entry
	    Group group = response.getEntity(Group.class);
	    
	    // update the group
	    Meta meta = factory.createMeta();
	    meta.setVersion("2.00");
	    group.setMeta(meta);
	    
	    // send the request to update the group
		response = client.updateGroup(group);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned user entry
	    group = response.getEntity(Group.class);
	    
	    System.out.println("updated group - " + group.getId());	    
	}
	
	public static void overrideUpdateGroupTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to retrieve the group
		ClientResponse response = client.retrieveGroup(gid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned group entry
	    Group group = response.getEntity(Group.class);
	    
	    // update the group
	    Meta meta = factory.createMeta();
	    meta.setVersion("2.00");
	    group.setMeta(meta);
	    
	    // send the request to update the group
		response = client.overrideUpdateGroup(group);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    	   	    
	    // verify the response contains a user
	    assert(response.getEntity(Group.class) instanceof Group);
	    
	    // retrieve the returned user entry
	    group = response.getEntity(Group.class);
	    
	    System.out.println("updated group - " + group.getId());	    
	}
	
	public static void updateGroupErrorTest(Client client, ObjectFactory factory, String gid)
	{	    
	    // create a test group
		Group group = factory.createGroup();
	    group.setId(gid);
	    
	    // send the request to update the user
		ClientResponse response = client.updateGroup(group);
	    
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("update group returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void deleteGroupTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to delete the group
		ClientResponse response = client.deleteGroup(gid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    
	    System.out.println("deleted group - " + gid);
	}
	
	public static void overrideDeleteGroupTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to delete the group
		ClientResponse response = client.overrideDeleteGroup(gid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.OK.getCode());
	    
	    System.out.println("deleted group - " + gid);
	}
	
	public static void deleteGroupErrorTest(Client client, ObjectFactory factory, String gid)
	{
		// send the request to delete the group
		ClientResponse response = client.deleteGroup(gid);
		
		// verify the response code indicates success
	    assert(response.getStatusCode() == HttpStatus.NOT_FOUND.getCode());
	    	   	    
	    // verify the response contains an error
	    assert(response.getEntity(Response.class) instanceof Response);	    	    
	    
	    // retrieve the returned response entry
	    Response clientResponse = response.getEntity(Response.class);
	    	    
	    // retrieve the response errors
	    Errors errors = clientResponse.getErrors();
	    
	    // verify errors exist
	    assert(errors != null && !errors.getError().isEmpty());
	    
	    // retrieve the error
	    List<Error> errorList = errors.getError();
	    for(Error error : errorList)
	    {
	    	System.out.println("delete group returned an error - " + error.getDescription() + "(" + error.getCode() + ")");
	    }
	}
	
	public static void main(String[] args)
	{
		// create a client to send the user/group crud requests
		Client client = new Client("http://localhost:8080/scim", "matt", "password");
		
		// create an object factory to create the user/group objects
		ObjectFactory factory = new ObjectFactory();

		
		// basic http method tests
		System.out.println("basic tests:");
		
		// test the create user functionality
		String gid = createGroupTest(client, factory);
		
		// test the retrieve group functionality
		retrieveGroupTest(client, factory, gid);		
		
		// test the update group functionality
		updateGroupTest(client, factory, gid);		
		
		// test the create user functionality
		String uid = createUserTest(client, factory);
		
	    // test the retrieve user functionality
		retrieveUserTest(client, factory, uid);
		
		// test the update user functionality
		updateUserTest(client, factory, uid);
		
		// test the delete user functionality
		deleteUserTest(client, factory, uid);
		
		// test the delete group functionality
		deleteGroupTest(client, factory, gid);
		
		
		/*
		// basic json formatted test
		System.out.println("json tests:");
		
		// test the create user functionality
		gid = createGroupAsJSONTest(client, factory);
		
		// test the retrieve group functionality
		retrieveGroupAsJSONTest(client, factory, gid);		
		
		// test the update group functionality
		updateGroupAsJSONTest(client, factory, gid);		
		
		// test the create user functionality
		uid = createUserAsJSONTest(client, factory);
		
	    // test the retrieve user functionality
		retrieveUserAsJSONTest(client, factory, uid);
		
		// test the update user functionality
		updateUserAsJSONTest(client, factory, uid);
		
		// test the delete user functionality
		deleteUserTest(client, factory, uid);
		
		// test the delete group functionality
		deleteGroupTest(client, factory, gid);
		*/
		
		
		// http method override tests
		System.out.println("http method override tests:");
		
		// test the create user functionality
		gid = createGroupTest(client, factory);
		
		// test the retrieve group functionality
		retrieveGroupTest(client, factory, gid);		
		
		// test the override update group functionality
		overrideUpdateGroupTest(client, factory, gid);		
		
		// test the create user functionality
		uid = createUserTest(client, factory);
		
	    // test the retrieve user functionality
		retrieveUserTest(client, factory, uid);
		
		// test the override update user functionality
		overrideUpdateUserTest(client, factory, uid);
		
		// test the override delete user functionality
		overrideDeleteUserTest(client, factory, uid);
		
		// test the override delete group functionality
		overrideDeleteGroupTest(client, factory, gid);
		
		
		// error response tests
		System.out.println("error response tests:");
		
		// test the create user functionality
		uid = createUserTest(client, factory);
		
		// test the create user erorr functionality
		createUserErrorTest(client, factory);
		
		// test the create user functionality
		gid = createGroupTest(client, factory);

		// test the create user error functionality
		createGroupErrorTest(client, factory);
		
		// test the retrieve user error functionality
		String unknownuid = "uid=unknown,dc=hackerypokery,dc=com";
		retrieveUserErrorTest(client, factory, unknownuid);
		
		// test the retrieve group error functionality
		String unknowngid = "gid=unknown,ou=groups,dc=hackerypokery,dc=com";
		retrieveGroupErrorTest(client, factory, unknowngid);
		
		// test the update user error functionality
		updateUserErrorTest(client, factory, unknownuid);
		
		// test the update user error functionality
		updateGroupErrorTest(client, factory, unknowngid);
		
		// test the delete user functionality
		deleteUserTest(client, factory, uid);		
		
		// test the delete user error functionality
		deleteUserErrorTest(client, factory, uid);
		
		// test the delete group functionality
		deleteGroupTest(client, factory, gid);
		
		// test the delete group error functionality
		deleteGroupErrorTest(client, factory, gid);
	}
}
