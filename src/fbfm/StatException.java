/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatException extends Exception {
    public StatException(String message) {
        super(message);
    }
    
    public StatException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public StatException(Throwable throwable) {
        super(throwable);
    }
}
