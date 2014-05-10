/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
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
public class PropertyPrivateMessagesTest {
    
    public PropertyPrivateMessagesTest() {
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
     * Test of calculateStat method, of class PropertyPrivateMessages.
     */
    @Test
    public void testCalculateStat() throws StatException, BadParameterException {        
        System.out.println("calculateStat");
        String accessToken = FacebookUtility.getAccessToken();
        String profileId = "699493740"; 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        PropertyPrivateMessages instance = new PropertyPrivateMessages();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        
         Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("limit", "200"));

        for (List<User> friendPaging : myFriends) {
          for (User friend : friendPaging) {
              params.put("friendId", friend.getId());
          }
        
        }
        params.put("timePeriod", "20");
        StatResponse result = instance.performCalculation(facebookClient, params );
        
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
