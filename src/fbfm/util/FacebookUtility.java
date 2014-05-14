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
    static protected String facebookAccessToken = "CAACEdEose0cBANpigmjqhM6OQMZA4jGkdHQ0TWSDt0oS1uyGmE7xdEz8OMbQ2Fsn4xkIp2YQkZCjhCZB39otgM55re8wW3gEwFw5sG3jilQ3iLuabndAGWW0VHofrDsQVrutXCnXzRjZCITAzmZATVfl1S3ERibxUrwf2wgR0g5ZAdQEX0NvFcaKr8F980uw4ZD";
    
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
