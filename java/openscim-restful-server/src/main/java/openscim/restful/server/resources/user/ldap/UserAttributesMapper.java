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
package openscim.restful.server.resources.user.ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import openscim.entities.Name;
import openscim.entities.PluralAttribute;
import openscim.entities.User;
import openscim.entities.User.Emails;
import openscim.entities.User.PhoneNumbers;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.springframework.ldap.core.AttributesMapper;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;

public class UserAttributesMapper implements AttributesMapper
{
	public Object mapFromAttributes(Attributes attributes) throws NamingException
	{
		// create a user resource
		User user = ResourceUtilities.FACTORY.createUser();
		
		// get the uid
		Attribute uidAttribute = attributes.get("uid");
		if(uidAttribute != null) user.setId((String)uidAttribute.get());
		
		// get the display name
		Attribute displayNameAttribute = attributes.get("cn");
		if(displayNameAttribute != null) user.setDisplayName((String)displayNameAttribute.get());
		
		// create a user name resource
		Name name = ResourceUtilities.FACTORY.createName();

		// get the surname name
		Attribute surnameAttribute = attributes.get("sn"); 
		if(surnameAttribute != null) name.setFamilyName((String)surnameAttribute.get());
		
		// get the given name
		Attribute givenAttribute = attributes.get("givenName");
		if(givenAttribute != null) name.setGivenName((String)givenAttribute.get());
		
		// add the name to the user resource
		user.setName(name);
		
		// get the mails
		NamingEnumeration mailEnumeration = attributes.get("mail").getAll();
		if(mailEnumeration != null)
		{
			// create a emails resource
			Emails emails = ResourceUtilities.FACTORY.createUserEmails();

			while(mailEnumeration.hasMoreElements())
			{
				// get the next email
				String mailAttribute = (String)mailEnumeration.next();
				if(mailAttribute != null)
				{				
					PluralAttribute pluralAttribute = ResourceUtilities.FACTORY.createPluralAttribute();
					pluralAttribute.setValue(mailAttribute);
					
					if(emails.getEmail().isEmpty()) pluralAttribute.setPrimary(true);
					else pluralAttribute.setPrimary(false);
					
					emails.getEmail().add(pluralAttribute);
				}
			}
			
			// add the mails to the user resource
			user.setEmails(emails);
		}
		
		// get the telephones
		NamingEnumeration telephoneEnumeration = attributes.get("telephoneNumber").getAll();
		if(telephoneEnumeration != null)
		{
			// create a telephones resource
			PhoneNumbers telephones = ResourceUtilities.FACTORY.createUserPhoneNumbers();

			while(telephoneEnumeration.hasMoreElements())
			{
				// get the next telephone
				String telephoneAttribute = (String)telephoneEnumeration.next();
				if(telephoneAttribute != null)
				{				
					PluralAttribute pluralAttribute = ResourceUtilities.FACTORY.createPluralAttribute();
					pluralAttribute.setValue(telephoneAttribute);
					
					if(telephones.getPhoneNumber().isEmpty()) pluralAttribute.setPrimary(true);
					else pluralAttribute.setPrimary(false);
					
					telephones.getPhoneNumber().add(pluralAttribute);
				}
			}
			
			// add the telephones to the user resource
			user.setPhoneNumbers(telephones);
		}
		
		// get the password
		Attribute passwordAttribute = attributes.get("userpassword");
		if(passwordAttribute != null) user.setPassword(new String((byte[])passwordAttribute.get()));

		return user;
     }
}
