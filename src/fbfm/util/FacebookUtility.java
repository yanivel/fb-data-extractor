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
    static protected String facebookAccessToken = "CAACEdEose0cBAOEAZCjt5qTE70bW6ZCfADG1y0RgUYYVWD4QolnqfER4Px2GRrLnEmBsDoKurknDPFZClEMAU2RPn3yPq8CZBQ7uTi7utfXGbki7b7DJBZCQVE4pimEtpCMIlnLZAFsZCSXUZBZA8DO6BauZBhRe0jRiWZB88nxZByRfj9GwtwBYAORj7vVOXvh0p54n5y8SeMCZBZAwZDZD";
    
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
