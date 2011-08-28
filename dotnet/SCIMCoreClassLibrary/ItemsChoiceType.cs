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

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SCIMCoreClassLibrary
{
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.1")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace = "urn:scim:schemas:core:1.0", IncludeInSchema = false)]
    public enum ItemsChoiceType
    {

        /// <remarks/>
        [System.Xml.Serialization.XmlEnumAttribute(":Resources")]
        Resources,

        /// <remarks/>
        [System.Xml.Serialization.XmlEnumAttribute(":errors")]
        errors,

        /// <remarks/>
        [System.Xml.Serialization.XmlEnumAttribute(":itemsPerPage")]
        itemsPerPage,

        /// <remarks/>
        [System.Xml.Serialization.XmlEnumAttribute(":startIndex")]
        startIndex,

        /// <remarks/>
        [System.Xml.Serialization.XmlEnumAttribute(":totalResults")]
        totalResults,

        /// <remarks/>
        Resource,
    }
}
