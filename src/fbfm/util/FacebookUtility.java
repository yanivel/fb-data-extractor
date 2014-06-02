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
    static protected String facebookAccessToken = "CAACEdEose0cBAKvj2EbcaUCZB6ToEJ1CPtJdpJWxA5J0aTmqb0cjohCoMxAmIha60Pc2mfA2GwAWjeqzwy0LyuweHZA0CjPfuSHgRGO30ZCtHCZBqViAcF8R6YIZCGfUMEV2iLM2tzs4MUp7tZCEK5eSVrP3FZC5YK61aRZBwHLHLCWZAcAURaR6Qutzc6nGd5ogS4rqhWTfCNgZDZD";
    
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
