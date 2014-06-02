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
    static protected String facebookAccessToken = "CAACEdEose0cBACixuGwVwZCRuVHkbvMO5FpOV8pR2uEr2ZCvGd63kufoZB6Ffzm1Ba3Gwhp1YRttv1jy5FDQ8vSRgmIsvkBfTSDJrCDJxJJjmZBOePSj1y2tkKrzDvGWaKWq6RwFxV0VkpEJx7vc6h6lspqh2JMd4Y5YpQv2sIbyUvJnYGh0lw1wXhiZASZBmJnZBaO0dlXoAZDZD";
    
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
