/*
 * NumericNode.java 03/12/2009 Copyright (C) 2009 Leonardo Alves da Costa This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.paradox.parser.nodes.values;

import com.googlecode.paradox.parser.nodes.SQLNode;

/**
 * Stores the numeric node.
 *
 * @author Leonardo Alves da Costa
 * @since 1.1
 * @version 1.0
 */
public class NumericNode extends SQLNode {
    
    /**
     * Create a new instance.
     *
     * @param value
     *            the numeric value.
     * @param alias
     *            the numeric alias.
     */
    public NumericNode(final String value, final String alias) {
        super(value, alias);
    }
}
