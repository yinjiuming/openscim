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

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for meta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="meta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meta", propOrder = {
    "lastModified",
    "created",
    "location",
    "version"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
public class Meta {

    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected XMLGregorianCalendar lastModified;
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected XMLGregorianCalendar created;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String location;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String version;

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public XMLGregorianCalendar getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setVersion(String value) {
        this.version = value;
    }

}
