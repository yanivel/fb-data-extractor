/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatUtilityTest {
    
    public StatUtilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAvailableStats method, of class StatUtility.
     */
    @Test
    public void testGetAvailableStats() {
        System.out.println("getAvailableStats");
        Set<Class<?>> expResult = null;
        Set<Class<?>> result = StatUtility.getAvailableStats();
        System.out.println(result.toString());
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of getAvailableProperties method, of class StatUtility.
     */
    @Test
    public void testGetAvailableProperties() {
        System.out.println("getAvailableProperties");
        Set<Class<?>> expResult = null;
        Set<Class<?>> result = StatUtility.getAvailableProperties();
        System.out.println(result.toString());
        
    }

    /**
     * Test of getAvailableFeatures method, of class StatUtility.
     */
    @Test
    public void testGetAvailableFeatures() {
        System.out.println("getAvailableFeatures");
        Set<Class<?>> expResult = null;
        Set<Class<?>> result = StatUtility.getAvailableFeatures();
        
        System.out.println(result.toString());
    }
    
}
