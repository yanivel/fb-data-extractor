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
import fbfm.util.FacebookUtility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class FeatureCommonGroupsTest {
    
    public FeatureCommonGroupsTest() {
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
     * Test of calculateStat method, of class FeatureCommonGroups.
     */
    @Test
    public void testCalculateStat() throws StatException, BadParameterException{
        System.out.println("calculateStat");
        String accessToken = FacebookUtility.getAccessToken();
        String profileId = "1400265161"; 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        FeatureCommonGroups instance = new FeatureCommonGroups();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        params.put("profileId", profileId);
        StatResponse result = instance.performCalculation(facebookClient, params );
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
