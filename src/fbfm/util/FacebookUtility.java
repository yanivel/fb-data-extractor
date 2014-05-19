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
    static protected String facebookAccessToken = "CAACEdEose0cBAK2xZCnsElHFW8EcS3hgK5n6Xj20e5ZAMFbNbnuZAoA3guVyrWJq5vPFBOtkZBozTd5eUZCb5ZAfmxsVjIkUbImhshvR6pSJnZB6die13ubQZCyZBte53Df5aIdZAdqNeQArYQu8hGZAAAsD2HIbUsLHlFcPhSeBP7bNWCiMQX8nKNSBN3cI79AaUUZD";
    
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
