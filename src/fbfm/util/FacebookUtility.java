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
    static protected String facebookAccessToken = "CAACEdEose0cBAEAJq38OD42SPwYSj9FAo2kZBuqdGAacLYRZC73HyqcTZCS2viffP8N9q7kiqeWVpwXZA9SjKFsD6BwhZAdGeb4ouLJaU76VwLr2zMKS69Y8nK23yvPfYOdS5HHvThBKCP4RXLQ8IXWtZBRNWRgwHXds7SIljc6bEbeOJLarNU1efZCaphsTngZD";
    
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
