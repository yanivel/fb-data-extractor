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
    static protected String facebookAccessToken = "CAACEdEose0cBAP424q5RnMXYP8eP3a9YTkhlZCQPoyAZCcu14FgDCsHmHmBA9psZAlSUEmvL23C1lJr1vycJWp0OWpYk2Qs9YmoZBFs6Kbwq41K4kHVUQ3k8ZB6nMtSEZBXdtB46xXjz8bHZBfN1gVj84xSbPcbbJkJ2eNkrNaT9ENgEGiWP3dx9um4GusRJzEZD";
    
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
