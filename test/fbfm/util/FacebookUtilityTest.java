/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import com.restfb.FacebookClient;
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
public class FacebookUtilityTest {
    
    public FacebookUtilityTest() {
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
     * Test of getAccessToken method, of class FacebookUtility.
     */
    //@Test
    public void testGetAccessToken() {
        System.out.println("getAccessToken");
        String expResult = "";
        String result = FacebookUtility.getAccessToken();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFacebookClient method, of class FacebookUtility.
     */
    //@Test
    public void testGetFacebookClient() {
        System.out.println("getFacebookClient");
        int result = FacebookUtility.AllFriendsToCSV("yaniv_all_friends");
         System.out.println("num of friends : " + result);
    }

    /**
     * Test of AllFriendsToCSV method, of class FacebookUtility.
     */
    @Test
    public void testAllFriendsToCSV() {
        System.out.println("AllFriendsToCSV");
        String filename = "";
        int expResult = 0;
        int result = FacebookUtility.AllFriendsToCSV("yaniv_all_friends.csv");
        System.out.println("num of friends : " + result);
        
    }
    
}
