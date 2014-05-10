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
public class PropertyFriendTaggedMePhotoTest {
    
    public PropertyFriendTaggedMePhotoTest() {
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
     * Test of calculateStat method, of class PropertyFriendTaggedMePhoto.
     */
    @Test
    public void testCalculateStat() throws StatException, BadParameterException{
        System.out.println("calculateStat");
        String accessToken = TokenUtility.getAccessToken();
        String profileId = "1378447125"; 
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        PropertyFriendTaggedMePhoto instance = new PropertyFriendTaggedMePhoto();
        StatResponse expResult = null;
        SetMultimap<String, Object> params = HashMultimap.create();
        
        /*Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("limit", "200"));

        for (List<User> friendPaging : myFriends) {
          for (User friend : friendPaging) {
              params.put("friendId", friend.getId());
          }
        
        }*/
        params.put("friendId", profileId);
        params.put("tagAmount", "20");
        StatResponse result = instance.performCalculation(facebookClient, params );
        
        System.out.println(result);
    }
    
}