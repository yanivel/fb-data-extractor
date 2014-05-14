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
    static protected String facebookAccessToken = "CAACEdEose0cBAFVZBkDwA4ZCbmS2baOq0jcl9l5DSXduxxST5rVWGqfHz8J1zaktgG3TN8YwnBy0pxZBCtZBguzUealLqWlAo8bVKdp3TpYhJJYLsUyqhMoKDkfIA4UjhdA5EO0nIPYx1RZCFmmFW8CdDWivoWtIUfFEoUThZCmgel1IoMcamOe02IcdSuo8AZD";
    
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
