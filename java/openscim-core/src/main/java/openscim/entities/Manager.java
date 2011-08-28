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
 * <p>Java class for manager complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="manager">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="managerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "manager", namespace = "urn:scim:schemas:extension:enterprise:1.0", propOrder = {
    "managerId",
    "displayName"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
public class Manager {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String managerId;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    protected String displayName;

    /**
     * Gets the value of the managerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getManagerId() {
        return managerId;
    }

    /**
     * Sets the value of the managerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setManagerId(String value) {
        this.managerId = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2011-08-28T03:01:01+10:00", comments = "JAXB RI vhudson-jaxb-ri-2.2-27")
    public void setDisplayName(String value) {
        this.displayName = value;
    }

}
