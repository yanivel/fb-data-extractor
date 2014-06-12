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
    static protected String facebookAccessToken = "CAACEdEose0cBAJirN7HqeDXuF65qZANrBsnR9Y4ZBuWuWLbGyOdt1JVDSXKJIE2OJF3Ldo0859qCOYAJDIeoDccjO9ZBZCZA4drBeRsPCCod1aTUH9ZCiEfwXU3D0ZA42lWAhxCuVDgTy1jhSU6mpjmo557NqHLYTEZC7M8YPbTib5n5guzEO11i0tJpCbNLeEB2Fo0uUV1jXwZDZD";
    
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
