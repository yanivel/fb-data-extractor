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
    static protected String facebookAccessToken = "CAACEdEose0cBABRn51oyZAbtppn5DHAEqMwTep35gFl1sLQFJthVrvwRTBdx2mDjBBZBf7amqRCVs02qrRttRoUgWF2kVEyu0KKx4dG8IdvUwZBqZAlH8ZAHZAGmIyBgzieiya1rRqBcyjNzZBYeMsz8s5jdvGmffCkf6VEhyPoOVnNED8wFg3h5VnRpooih4EZD";
    
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
