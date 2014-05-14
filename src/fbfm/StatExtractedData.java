/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatExtractedData {
    
    protected String userId;
    protected Table<String, String, String> data;

    public StatExtractedData()
    {
        this.data = HashBasedTable.create();
    }
    
    public StatExtractedData(String userId) 
    {
        this();
        this.userId = userId;
    }
    
    public void addData(String profileId, String statName, String statValue) 
    {
        this.data.put(profileId, statName, statValue);
    }
    
    public void addData(StatResponse statResponse) 
    {
        Set<String> keys = statResponse.getKeys();
        for (String key : keys) {
            StatValue<?> statValue = statResponse.getValue(key);
            this.addData(key, statResponse.getStatClass().getName(), statValue.stringValue());
        }
    }
    
    public Table<String, String, String> getDataTable() {
        return this.data;
    }
    
    /**
     * 
     * @return String
     */
    @Override 
    public String toString() {
        return this.data.toString();
    }
}
