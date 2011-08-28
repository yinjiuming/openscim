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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Resource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Resource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="meta" type="{urn:scim:schemas:core:1.0}meta" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Resource", propOrder = {
    "meta",
    "id"
})
@XmlSeeAlso({
    User.class,
    Group.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
public class Resource {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected Meta meta;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String id;

    /**
     * Gets the value of the meta property.
     * 
     * @return
     *     possible object is
     *     {@link Meta }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public Meta getMeta() {
        return meta;
    }

    /**
     * Sets the value of the meta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Meta }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setMeta(Meta value) {
        this.meta = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setId(String value) {
        this.id = value;
    }

}
