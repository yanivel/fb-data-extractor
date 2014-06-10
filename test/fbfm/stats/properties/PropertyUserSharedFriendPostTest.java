/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import fbfm.BadParameterException;
import fbfm.StatException;
import fbfm.StatResponse;
import fbfm.util.DebugUtility;
import fbfm.util.FacebookUtility;
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
public class PropertyUserSharedFriendPostTest {
    
    public PropertyUserSharedFriendPostTest() {
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
     * Test of calculateStat method, of class PropertyUserSharedFriendPost.
     */
    @Test
    public void testCalculateStat() throws StatException, BadParameterException{
      
         DebugUtility.setDebug(true);
         System.out.println("num of times I shared a post of valerie on wall");
        String accessToken = FacebookUtility.getAccessToken();
        String profileId = "100001558392789"; // valerie mangoni 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        PropertyUserSharedFriendPost instance = new PropertyUserSharedFriendPost();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        
        params.put("profileId", profileId);
        
        StatResponse result = instance.performCalculation(facebookClient, params );
        System.out.println(result);
    }
    
}
