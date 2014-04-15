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
public class BadParameterException extends Exception {
    public BadParameterException(String message) {
        super(message);
    }
    
    public BadParameterException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public BadParameterException(Throwable throwable) {
        super(throwable);
    }
}
