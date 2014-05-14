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
public class DebugUtility {
    
    static protected boolean debug = false;
    
    private DebugUtility() {
        
    }
    
    static public void setDebug(boolean debug) {
        DebugUtility.debug = debug;
    }
    
    static public void print(Object s) {
        if (DebugUtility.debug == true) {
            System.out.print(s);
        }
             
    }
    
    static public void println(Object s) {
        if (DebugUtility.debug == true) {
            System.out.println(s);
        }
             
    }
}
