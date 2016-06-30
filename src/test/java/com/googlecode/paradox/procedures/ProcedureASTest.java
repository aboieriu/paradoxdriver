/*
 * ProcedureAS.java
 *
 * 06/30/2016
 * Copyright (C) 2016 Leonardo Alves da Costa
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
package com.googlecode.paradox.procedures;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.paradox.procedures.math.Min;

/**
 * Unit test for {@link ProcedureAS}.
 * 
 * @author Leonardo Alves da Costa
 * @since 1.3
 * @version 1.0
 */
public class ProcedureASTest {
    /**
     * Test for get all procedures avaliable.
     */
    @Test
    public void testAllProcedures() {
        Assert.assertEquals(5, ProcedureAS.getInstance().list().size());
    }

    /**
     * Test instance.
     */
    @Test
    public void testInstance() {
        Assert.assertNotNull(ProcedureAS.getInstance());
    }

    /**
     * Test for an invalid procedure.
     */
    @Test
    public void testInvalidProcedure() {
        Assert.assertNull(ProcedureAS.getInstance().get("INVALID"));
    }

    /**
     * Test the get procedure by its name.
     */
    @Test
    public void testProcedureByName() {
        Assert.assertSame(new Min().getName(), ProcedureAS.getInstance().get("min").getName());
    }
}
