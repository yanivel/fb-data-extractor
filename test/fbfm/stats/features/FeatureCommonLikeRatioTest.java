/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import fbfm.BadParameterException;
import fbfm.StatException;
import fbfm.StatResponse;
import fbfm.util.FacebookUtility;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        String accessToken = FacebookUtility.getAccessToken();
        String friendId = "1400265161"; 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        FeatureCommonLikeRatio instance = new FeatureCommonLikeRatio();
        StatResponse expResult = null;
        
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);

        SetMultimap<String, Object> params = HashMultimap.create();
        for (List<User> friendPaging : myFriends) {
          for (User friend : friendPaging) {
              params.put("friendId", friend.getId());
          }
        
        }
        
        StatResponse result = instance.performCalculation(facebookClient, params );
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
