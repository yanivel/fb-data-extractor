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
public class FeatureCommonLikeRatioTest {
    
    public FeatureCommonLikeRatioTest() {
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
     * Test of calculateStat method, of class FeatureCommonLikeRatio.
     */
    @Test
    public void testPerformCalculation() throws StatException, BadParameterException{
        System.out.println("calculateStat");
        String accessToken = "CAACEdEose0cBAAVpnGu3QR9Wuq73zIcIFZCNsVjRXrap9uH6pnmaZBgPGlOvQF1LhEaPSb2q9lBx4TExuUzXO9F1dtJEDqoMwv2bzgHZAY3iKZC5Fu8DiX7tEgRtASIZB8ZAXPAPTNjMYoZBFcznqrJVy3RoC4wLWJVlIeSlUHPvexOW6jFKXMwCMQvMxZAhYy0ZD";
        String friendId = "1400265161"; 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        FeatureCommonLikeRatio instance = new FeatureCommonLikeRatio();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        params.put("friendId", friendId);
        StatResponse result = instance.performCalculation(facebookClient, params );
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
