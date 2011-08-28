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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="formatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "formatted",
    "streetAddress",
    "locality",
    "region",
    "postalCode",
    "country",
    "primary",
    "type"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
public class Address {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String formatted;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String streetAddress;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String locality;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String region;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String postalCode;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String country;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected Boolean primary;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String type;

    /**
     * Gets the value of the formatted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getFormatted() {
        return formatted;
    }

    /**
     * Sets the value of the formatted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setFormatted(String value) {
        this.formatted = value;
    }

    /**
     * Gets the value of the streetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the value of the streetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setStreetAddress(String value) {
        this.streetAddress = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setLocality(String value) {
        this.locality = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the primary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public Boolean isPrimary() {
        return primary;
    }

    /**
     * Sets the value of the primary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setPrimary(Boolean value) {
        this.primary = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setType(String value) {
        this.type = value;
    }

}
