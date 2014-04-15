/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
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
        String accessToken = "CAACEdEose0cBAHnGdHE7HF0Q4KNOIEogC56qHZCZAtfK5WEZC7do0U3HBbLxMka00V4f0Qzipme6VNufAC5RlqdQjgbQ9n9OBpsbBncLRsKzA9zXGyGFMuMEzxaBTTLLK9JEtr3CicsbZC3IwceIwcydJuQPD6iy1AMtdApTpEZCE4Aw7nUauGv031VeitC5ikGQTMe5cbAZDZD";
        String friendId = "100001531730648";
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        
        FeatureMutualFriends instance = new FeatureMutualFriends();
        StatResponse expResult = null;
        StatResponse result = instance.performCalculation(facebookClient, Parameter.with("friendId", friendId));
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
