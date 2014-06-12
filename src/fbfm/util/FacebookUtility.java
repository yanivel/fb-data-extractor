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
    static protected String facebookAccessToken = "CAACEdEose0cBAAzAzXwvUssB5uCBEJUtWfctRPBBkLBF15XADYhPLVFAXWTwu1BnHMoqd1jPC6iy3pVLYFZBr5Mc87VdIqZCO3Xi2ZCjbAhIvVZBbXFGL4neWn8YxmOAhdBjQ5ezFwPoDHQcMejt5ctr2XrcgsGtZAoSGs1JDafOx769yMHoTgDAm1eJCJdL4t7Ei6llD3wZDZD";
    
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
