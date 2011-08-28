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
package openscim.restful.client.test;

import openscim.entities.ObjectFactory;
import openscim.restful.client.Client;
import openscim.restful.client.Tests;
import junit.framework.TestCase;

public class CrudTest extends TestCase
{
	private Client client = null; 
	private ObjectFactory factory = null;
	
	@Override
    public void setUp()
	{
		// create a client to send the user/group crud requests
		client = new Client("http://localhost:8080/scim", "matt", "password");
		
		// create an object factory to create the user/group objects
		factory = new ObjectFactory();
    }
	
	public void testBasicCrud()
	{
		// test the create user functionality
		String gid = Tests.createGroupTest(client, factory);
		
		// test the retrieve group functionality
		Tests.retrieveGroupTest(client, factory, gid);		
		
		// test the update group functionality
		Tests.updateGroupTest(client, factory, gid);		
		
		// test the create user functionality
		String uid = Tests.createUserTest(client, factory);
		
	    // test the retrieve user functionality
		Tests.retrieveUserTest(client, factory, uid);
		
		// test the update user functionality
		Tests.updateUserTest(client, factory, uid);
		
		// test the delete user functionality
		Tests.deleteUserTest(client, factory, uid);
		
		// test the delete group functionality
		Tests.deleteGroupTest(client, factory, gid);
	}
	
	public void testOverrideCrud()
	{
		// test the create user functionality
		String gid = Tests.createGroupTest(client, factory);
		
		// test the retrieve group functionality
		Tests.retrieveGroupTest(client, factory, gid);		
		
		// test the override update group functionality
		Tests.overrideUpdateGroupTest(client, factory, gid);		
		
		// test the create user functionality
		String uid = Tests.createUserTest(client, factory);
		
	    // test the retrieve user functionality
		Tests.retrieveUserTest(client, factory, uid);
		
		// test the override update user functionality
		Tests.overrideUpdateUserTest(client, factory, uid);
		
		// test the override delete user functionality
		Tests.overrideDeleteUserTest(client, factory, uid);
		
		// test the override delete group functionality
		Tests.overrideDeleteGroupTest(client, factory, gid);
	}
	
	public void testCrudErrors()
	{
		// test the create user functionality
		String uid = Tests.createUserTest(client, factory);
		
		// test the create user erorr functionality
		Tests.createUserErrorTest(client, factory);
		
		// test the create user functionality
		String gid = Tests.createGroupTest(client, factory);

		// test the create user error functionality
		Tests.createGroupErrorTest(client, factory);
		
		// test the retrieve user error functionality
		String unknownuid = "uid=unknown,dc=hackerypokery,dc=com";
		Tests.retrieveUserErrorTest(client, factory, unknownuid);
		
		// test the retrieve group error functionality
		String unknowngid = "gid=unknown,ou=groups,dc=hackerypokery,dc=com";
		Tests.retrieveGroupErrorTest(client, factory, unknowngid);
		
		// test the update user error functionality
		Tests.updateUserErrorTest(client, factory, unknownuid);
		
		// test the update user error functionality
		Tests.updateGroupErrorTest(client, factory, unknowngid);
		
		// test the delete user functionality
		Tests.deleteUserTest(client, factory, uid);		
		
		// test the delete user error functionality
		Tests.deleteUserErrorTest(client, factory, uid);
		
		// test the delete group functionality
		Tests.deleteGroupTest(client, factory, gid);
		
		// test the delete group error functionality
		Tests.deleteGroupErrorTest(client, factory, gid);
	}
}
