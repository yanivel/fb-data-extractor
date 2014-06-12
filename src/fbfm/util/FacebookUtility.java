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
    static protected String facebookAccessToken = "CAACEdEose0cBADmNEmOmqcUsadsPtT6CoL4qZBhaUTjZA8U7LpSASqmPS2b5i5pr9pYeVa1cAzJjDmSlEKiesuxlTZC0UmvwdrRT3CiY28urx51CmP9pumzLuqNLRSp9elLTcJqxM7jhXcxwRlzcZCDQ0zVJkWjK31OmobZB9CjQbHJDo1jie0hBip8xda1TSA9Q1FppduQZDZD";
    
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
