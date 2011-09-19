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
package openscim.restful.server.resources.group.ldap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import openscim.entities.Group;
import openscim.entities.PluralAttribute;
import openscim.restful.server.resources.user.ldap.UserAttributesMapper;
import openscim.restful.server.resources.util.ResourceUtilities;

import org.springframework.ldap.core.AttributesMapper;

public class GroupAttributesMapper implements AttributesMapper
{
	public static final String LDAP_PREFIX = "ldap.";
	public static final String ATTRIBUTE_PREFIX = LDAP_PREFIX + "attributes.groups.";
	public static final String GROUP_OBJECTCLASS_ATTRIBUTE = ATTRIBUTE_PREFIX + "objectclass";
	public static final String GID_ATTRIBUTE = ATTRIBUTE_PREFIX + "cn";
	public static final String MEMBER_ATTRIBUTE = ATTRIBUTE_PREFIX + "member";	
	public static final String DEFAULT_GROUP_OBJECTCLASS_ATTRIBUTE = "groupOfNames";
	public static final String DEFAULT_GID_ATTRIBUTE = "cn";
	public static final String DEFAULT_MEMBER_ATTRIBUTE = "member";	
	public static final String GROUP_PREFIX = "ldap.group.";
	public static final String GROUP_BASEDN = GROUP_PREFIX + "basedn";
	public static final String DEFAULT_GROUP_BASEDN = "ou=groups,dc=openscim";
	public static final String CONCEAL_GROUP_DNS = LDAP_PREFIX + "concealdns";
	public static final String DEFAULT_CONCEAL_GROUP_DNS = "true";
	
	private Properties properties = null;
	
	public GroupAttributesMapper(Properties properties)
	{
		this.properties = properties;
	}
	
	public Object mapFromAttributes(Attributes attributes) throws NamingException
	{
		// create a group resource
		Group group = ResourceUtilities.FACTORY.createGroup();
		
		// get the gid attribute name
		String gidAtttributeName = DEFAULT_GID_ATTRIBUTE;
		if(properties.containsKey(GID_ATTRIBUTE)) gidAtttributeName = properties.getProperty(GID_ATTRIBUTE);
		
		// get the gid		
		Attribute gidAttribute = attributes.get(gidAtttributeName);
		if(gidAttribute != null) group.setId((String)gidAttribute.get());
		
		// get the member attribute name
		String memberAtttributeName = DEFAULT_MEMBER_ATTRIBUTE;
		if(properties.containsKey(MEMBER_ATTRIBUTE)) memberAtttributeName = properties.getProperty(MEMBER_ATTRIBUTE);						
		
		// get the members
		NamingEnumeration memberEnumeration = attributes.get(memberAtttributeName).getAll();
		if(memberEnumeration != null)
		{
			// create a members resource
			List<PluralAttribute> members = new ArrayList<PluralAttribute>();
			
			while(memberEnumeration.hasMoreElements())
			{
				// get the next member
				String memberAttribute = (String)memberEnumeration.next();
				if(memberAttribute != null)
				{				
					PluralAttribute pluralAttribute = ResourceUtilities.FACTORY.createPluralAttribute();
					
					// check if the member dns need to be concealed 
					if(properties.getProperty(GroupAttributesMapper.CONCEAL_GROUP_DNS, GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS).equalsIgnoreCase(GroupAttributesMapper.DEFAULT_CONCEAL_GROUP_DNS))
					{
						String expression = properties.getProperty(UserAttributesMapper.UID_ATTRIBUTE, UserAttributesMapper.DEFAULT_UID_ATTRIBUTE) + 
						   "=([^,]*)," + properties.getProperty(UserAttributesMapper.ACCOUNT_BASEDN, UserAttributesMapper.DEFAULT_ACCOUNT_BASEDN);
						Pattern pattern = Pattern.compile(expression);
						Matcher matcher = pattern.matcher(memberAttribute);			
						if(matcher.matches())
						{
							memberAttribute = matcher.group(1); 
						}
					}
					
					pluralAttribute.setValue(memberAttribute);					
					members.add(pluralAttribute);
				}
			}
			
			// add the members to the group resource
			group.setAny(members);
		}
		
		return group;
     }
}
