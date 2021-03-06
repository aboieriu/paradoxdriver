/*
 * JoinNode.java 03/12/2009 Copyright (C) 2009 Leonardo Alves da Costa This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.paradox.parser.nodes;

import java.util.Collections;
import java.util.List;

/**
 * Stores a join node.
 *
 * @author Leonardo Alves da Costa
 * @version 1.1
 * @since 1.0
 */
public final class JoinNode extends SQLNode {
    
    /**
     * The condition list.
     */
    private List<SQLNode> conditions;
    
    /**
     * The table name.
     */
    private String tableName;
    
    /**
     * The join type.
     */
    private JoinType type = JoinType.CROSS;
    
    /**
     * Create a new instance.
     */
    public JoinNode() {
        super("JOIN");
    }
    
    /**
     * Gets the table name.
     *
     * @return the table name.
     */
    public String getTableName() {
        return this.tableName;
    }
    
    /**
     * Gets the join type.
     *
     * @return the join type.
     */
    public JoinType getType() {
        return this.type;
    }
    
    /**
     * Sets the condition list.
     *
     * @param conditions
     *            the condition list.
     */
    public void setConditions(final List<SQLNode> conditions) {
        this.conditions = Collections.unmodifiableList(conditions);
    }
    
    /**
     * Sets the table name.
     *
     * @param tableName
     *            the table name.
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Sets the join type.
     *
     * @param type
     *            the join type.
     */
    public void setType(final JoinType type) {
        this.type = type;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.type);
        builder.append(" JOIN ");
        builder.append(this.tableName);
        if ((this.getAlias() != null) && !this.tableName.equals(this.getAlias())) {
            builder.append(" AS ");
            builder.append(this.getAlias());
        }
        if (this.conditions != null) {
            builder.append(" ON ");
            for (final SQLNode condition : this.conditions) {
                builder.append(condition);
                builder.append(' ');
            }
        }
        return builder.toString();
    }
    
    /**
     * Gets the conditions.
     *
     * @return the conditions.
     */
    List<SQLNode> getConditions() {
        return Collections.unmodifiableList(this.conditions);
    }
    
}
