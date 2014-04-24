/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class TokenUtility {
    static protected String facebookAccessToken = "";
    
    public static String getAccessToken() {
        return TokenUtility.facebookAccessToken;
    }
    
}
