/*
 * ParadoxDataFile.java
 *
 * 03/12/2009
 * Copyright (C) 2009 Leonardo Alves da Costa
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
package com.googlecode.paradox.parser.planner;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.googlecode.paradox.Driver;
import com.googlecode.paradox.ParadoxConnection;
import com.googlecode.paradox.integration.MainTest;
import com.googlecode.paradox.parser.SQLParser;
import com.googlecode.paradox.parser.nodes.SelectNode;
import com.googlecode.paradox.parser.nodes.StatementNode;
import com.googlecode.paradox.planner.Planner;
import com.googlecode.paradox.planner.plan.SelectPlan;

/**
 * Unit test for {@link Planner}.
 * 
 * @author Leonardo Alves da Costa
 * @since 1.1
 * @version 1.1
 */
public class PlannerTest {

    /**
     * The database test connection.
     */
    public static final String CONNECTION_STRING = "jdbc:paradox:target/test-classes/";

    /**
     * The database connection.
     */
    private ParadoxConnection conn;

    /**
     * Register the driver.
     * 
     * @throws ClassNotFoundException
     *             in case of connection errors.
     */
    @BeforeClass
    public static void setUp() throws ClassNotFoundException {
        Class.forName(Driver.class.getName());
    }

    /**
     * Used to close the test connection.
     * 
     * @throws Exception
     *             in case closing of errors.
     */
    @After
    public void closeConnection() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Connect to test database.
     * 
     * @throws Exception
     *             in case of connection errors.
     */
    @Before
    public void connect() throws Exception {
        conn = (ParadoxConnection) DriverManager.getConnection(MainTest.CONNECTION_STRING + "db");
    }

    /**
     * Test for a SELECT plan.
     * 
     * @throws Exception
     *             in case of failures.
     */
    @Test
    public void tableTest() throws Exception {
        final SQLParser parser = new SQLParser("select * from areacodes a");
        final Planner planner = new Planner(conn);
        final SelectPlan plan = (SelectPlan) planner.create(parser.parse().get(0));
        Assert.assertNotNull("No columns", plan.getColumns());
        Assert.assertEquals("Num of columns in table", 3, plan.getColumns().size());
        Assert.assertEquals("First column not 'AC'", "AC", plan.getColumns().get(0).getName());
        Assert.assertEquals("Second column not 'State'", "STATE", plan.getColumns().get(1).getName());
        Assert.assertEquals("Third column not 'Cities'", "CITIES", plan.getColumns().get(2).getName());
    }

    /**
     * Test for a invalid node.
     * 
     * @throws SQLException
     *             in case of errors.
     */
    @Test(expected = SQLFeatureNotSupportedException.class)
    public void testInvalid() throws SQLException {
        final StatementNode node = new StatementNode("node");
        final Planner planner = new Planner(conn);
        planner.create(node);
    }

    /**
     * Test for SELECT plan without columns.
     * 
     * @throws SQLException
     *             em caso de falhas.
     */
    @Test(expected = SQLException.class)
    public void testSelectWithoutColumns() throws SQLException {
        final SelectNode node = new SelectNode();
        final Planner planner = new Planner(conn);
        planner.create(node);
    }
}
