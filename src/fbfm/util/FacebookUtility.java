/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class FacebookUtility {
    static protected String facebookAccessToken = "CAACEdEose0cBAAbTG7c4wWH3QrlMUTA2QO8NSjrgrxhBeJiSOVSVPgRYGhFlx9DEEm15ZCzGueMW1fvccKqk3r5Fr4ZBbeFqAWnyCw6ZCesZBrPCmwbQ9PqbhMHZByOhWZBtS54rjekqyBlXpnBHlDZClre1lHadYo7jlVr5ffdG5PqkcClBkQbBD1aqxIKZBfMEM5yVnvJFAQZDZD";
    
    static protected FacebookClient facebookClient = null;
    
    private FacebookUtility() {
    
    }
    
    public static String getAccessToken() {
        return FacebookUtility.facebookAccessToken;
    }
    
    public static FacebookClient getFacebookClient() {
        if (FacebookUtility.facebookClient == null) {
            FacebookUtility.facebookClient = new DefaultFacebookClient(FacebookUtility.getAccessToken());
        }
        
        return FacebookUtility.facebookClient;
    }
    
}
