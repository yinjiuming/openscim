/*
 * openscim scim core
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
package openscim.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the openscim.entities package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CostCenter_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "costCenter");
    private final static QName _Response_QNAME = new QName("urn:scim:schemas:core:1.0", "Response");
    private final static QName _Error_QNAME = new QName("urn:scim:schemas:core:1.0", "Error");
    private final static QName _Group_QNAME = new QName("urn:scim:schemas:core:1.0", "Group");
    private final static QName _EmployeeNumber_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "employeeNumber");
    private final static QName _Division_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "division");
    private final static QName _Manager_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "manager");
    private final static QName _Resource_QNAME = new QName("urn:scim:schemas:core:1.0", "Resource");
    private final static QName _Organization_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "organization");
    private final static QName _User_QNAME = new QName("urn:scim:schemas:core:1.0", "User");
    private final static QName _Department_QNAME = new QName("urn:scim:schemas:extension:enterprise:1.0", "department");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: openscim.entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link PluralAttribute }
     * 
     */
    public PluralAttribute createPluralAttribute() {
        return new PluralAttribute();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Name }
     * 
     */
    public Name createName() {
        return new Name();
    }

    /**
     * Create an instance of {@link Meta }
     * 
     */
    public Meta createMeta() {
        return new Meta();
    }

    /**
     * Create an instance of {@link Manager }
     * 
     */
    public Manager createManager() {
        return new Manager();
    }

    /**
     * Create an instance of {@link Response.Resources }
     * 
     */
    public Response.Resources createResponseResources() {
        return new Response.Resources();
    }

    /**
     * Create an instance of {@link Response.Errors }
     * 
     */
    public Response.Errors createResponseErrors() {
        return new Response.Errors();
    }

    /**
     * Create an instance of {@link User.Emails }
     * 
     */
    public User.Emails createUserEmails() {
        return new User.Emails();
    }

    /**
     * Create an instance of {@link User.PhoneNumbers }
     * 
     */
    public User.PhoneNumbers createUserPhoneNumbers() {
        return new User.PhoneNumbers();
    }

    /**
     * Create an instance of {@link User.Ims }
     * 
     */
    public User.Ims createUserIms() {
        return new User.Ims();
    }

    /**
     * Create an instance of {@link User.PhotoUrls }
     * 
     */
    public User.PhotoUrls createUserPhotoUrls() {
        return new User.PhotoUrls();
    }

    /**
     * Create an instance of {@link User.Addresses }
     * 
     */
    public User.Addresses createUserAddresses() {
        return new User.Addresses();
    }

    /**
     * Create an instance of {@link User.MemberOf }
     * 
     */
    public User.MemberOf createUserMemberOf() {
        return new User.MemberOf();
    }

    /**
     * Create an instance of {@link User.Roles }
     * 
     */
    public User.Roles createUserRoles() {
        return new User.Roles();
    }

    /**
     * Create an instance of {@link User.Entitlements }
     * 
     */
    public User.Entitlements createUserEntitlements() {
        return new User.Entitlements();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "costCenter")
    public JAXBElement<String> createCostCenter(String value) {
        return new JAXBElement<String>(_CostCenter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Response")
    public JAXBElement<Response> createResponse(Response value) {
        return new JAXBElement<Response>(_Response_QNAME, Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Group }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Group")
    public JAXBElement<Group> createGroup(Group value) {
        return new JAXBElement<Group>(_Group_QNAME, Group.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "employeeNumber")
    public JAXBElement<String> createEmployeeNumber(String value) {
        return new JAXBElement<String>(_EmployeeNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "division")
    public JAXBElement<String> createDivision(String value) {
        return new JAXBElement<String>(_Division_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Manager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "manager")
    public JAXBElement<Manager> createManager(Manager value) {
        return new JAXBElement<Manager>(_Manager_QNAME, Manager.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "Resource")
    public JAXBElement<Resource> createResource(Resource value) {
        return new JAXBElement<Resource>(_Resource_QNAME, Resource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "organization")
    public JAXBElement<String> createOrganization(String value) {
        return new JAXBElement<String>(_Organization_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:core:1.0", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:scim:schemas:extension:enterprise:1.0", name = "department")
    public JAXBElement<String> createDepartment(String value) {
        return new JAXBElement<String>(_Department_QNAME, String.class, null, value);
    }

}
