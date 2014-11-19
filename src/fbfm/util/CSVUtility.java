/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Can implement this to automatically combine files
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 * @deprecated
 */
public class CSVUtility {
    private CSVUtility() {
        
    }
    
    static boolean combineFiles(String folderPath) {
        
        // check if folder exists
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("folder '" + folderPath + "' does not exist or is not a directory.");
            return false;
        }
        
        // get all files in folder
        File[] files = folder.listFiles();
        for (File file : files) {
            String filename = file.getName();
            
            // copy first file
            
            // append all files to first one
        }
        
        
        return true;
    }
}