/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import fbfm.BadParameterException;
import fbfm.StatException;
import fbfm.StatResponse;
import fbfm.util.TokenUtility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class FeatureMutualFriendsTest {
    
    public FeatureMutualFriendsTest() {
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
     * Test of calculateStat method, of class FeatureMutualFriends.
     */
    @Test
    public void testPerformCalculation() throws StatException, BadParameterException {
        System.out.println("calculateStat");
        String accessToken = TokenUtility.getAccessToken();
        String friendId = "1467285958";
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        FeatureMutualFriends instance = new FeatureMutualFriends();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        params.put("profileId", friendId);
        StatResponse result = instance.performCalculation(facebookClient, params );
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
