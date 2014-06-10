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
    static protected String facebookAccessToken = "CAACEdEose0cBAASerGozYVMKCZADIeT8vCu28zXX2EsH73RxONJyZC43FcV6XIi5Izf3H0NOqPEG8si6Gv4IiNDcPl7IggmKZCMGtsAbkjtfsuAe2KLrZC9rQzl0gSyqJ1DtYWBpOKjAlKASmndv8TrDsyzVZAdjXN0ViaCVctpkrN5eXID65kspV1JWQWRIZD";
    
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
