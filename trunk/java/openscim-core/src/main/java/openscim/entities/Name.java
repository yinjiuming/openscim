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
 * <p>Java class for name complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="name">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="formatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="givenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="honorificPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="honorificSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "name", propOrder = {
    "formatted",
    "familyName",
    "givenName",
    "middleName",
    "honorificPrefix",
    "honorificSuffix"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
public class Name {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String formatted;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String familyName;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String givenName;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String middleName;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String honorificPrefix;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String honorificSuffix;

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
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the honorificPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getHonorificPrefix() {
        return honorificPrefix;
    }

    /**
     * Sets the value of the honorificPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setHonorificPrefix(String value) {
        this.honorificPrefix = value;
    }

    /**
     * Gets the value of the honorificSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getHonorificSuffix() {
        return honorificSuffix;
    }

    /**
     * Sets the value of the honorificSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setHonorificSuffix(String value) {
        this.honorificSuffix = value;
    }

}
