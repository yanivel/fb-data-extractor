/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

import com.google.common.base.Joiner;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import fbfm.util.FacebookUtility;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatExtractor {
    
    // user for whom to extract stats for
    protected String userId;
    
    // profile ids in relation to userId to get stats
    protected List<String> profileIds;
    
    protected Set<Class<?>> stats;
    
    protected Table<String, String, String> parameters;
    
    // timeout between calls to facebook in milliseconds
    protected int timeout = 0;
    
    protected StatExtractedData statData;
    
    
    public StatExtractor() 
    {
        this.profileIds = new ArrayList<>();
        this.stats = new HashSet();
        this.parameters = HashBasedTable.create();
    }
    
    public StatExtractor(int timeout)
    {
        this();
        this.timeout = timeout;
    }
    
    public StatExtractor(String userId, List<String> profileIds) 
    {
        this();
        this.userId = userId;
        for (String profileId : profileIds) {
            this.profileIds.add(profileId);
        }
    }
    
    public StatExtractor(String userId, List<String> profileIds, int timeout) 
    {
        this(userId, profileIds);
        this.timeout = timeout;
    }
    
    public StatExtractor(String userId, int timeout) 
    {
        this(timeout);
        this.userId = userId;
    }
    
    
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    public void setUser(String userId) 
    {
        this.userId = userId;
    }
    
    public String getUser()
    {
        return this.userId;
    }
    
    public void addProfileId(String profileId)
    {
        this.profileIds.add(profileId);
    }
    
    public void addParameter(Class<?> stat, String key, String value) 
    {
        this.parameters.put(stat.getName(), key, value);
    }
    
    public StatExtractedData getExtractedData()
    {
        return this.statData;
    }
    
    // replaces profileIds list with a new one
    public void setProfileIds(List<String> profileIds)
    {
        this.profileIds.clear();
        for (String profileId : profileIds) {
            this.profileIds.add(profileId);
        }
    }
    
    public void addStats(Set<Class<?>> stats) {
        this.stats = Sets.union(this.stats, stats).immutableCopy();
    }
    
    // replaces stats
    public void setStats(Set<Class<?>> stats) {
        this.stats = stats;
    }
    
    
    public boolean extract()
    {
        this.statData = new StatExtractedData(this.userId);
        
        for (String profileId : this.profileIds) {
            for (Class<?> statClass : this.stats) {
                try {
                    Stat stat = (Stat)statClass.newInstance(); 
                    
                    SetMultimap<String, Object> params = HashMultimap.create();
                    
                    // convert Map to SetMultiMap this way:
                    // in the meanwhile until https://code.google.com/p/guava-libraries/issues/detail?id=465 is fixed
                    for (Map.Entry<String, String> entry : this.parameters.row(statClass.getName()).entrySet()) {
                      params.put(entry.getKey(), entry.getValue());
                    }
                    // "profileId" is added here
                    params.put("profileId", profileId);
                    
                    StatResponse result = stat.performCalculation(FacebookUtility.getFacebookClient(), params );
                    
                    this.statData.addData(result);
                    // sleep for time out
                    Thread.sleep(this.timeout);
                } catch (Exception e) { // change to approprtiate errors
                    e.printStackTrace();
                    // TODO: need here something??
                }
            }
        }
        
        return true;
    }
    
    
    public boolean saveToCSVFile(String filename)
    {
        Table<String, String, String> table = this.statData.getDataTable();
        Set<String> traitsNames = table.columnKeySet();
        
        try
	{
	    FileWriter writer = new FileWriter(filename);
            // print the fields titles
            writer.append("main profile,friend profile,");
            ArrayList<String> traitArray = new ArrayList<>();
            for (String traitName : traitsNames) {
                traitArray.add(traitName);
            }
            writer.append(Joiner.on(",").join(traitArray));
            writer.append('\n');
            
            // print all the features/properties (traits)
	    
	    
	    for (String profileId : this.profileIds) {
                writer.append(this.userId + ',');
                writer.append(profileId);
                
                Map<String, String> traits = table.row(profileId);
                
                for (String traitName : traitsNames) {
                    writer.append(',' + traits.get(traitName));
                }
                writer.append('\n');
            }
 
	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
             return false;
	} 
        return true;
    }
    
    public boolean printToConsole()
    {
        System.out.println(this.statData);
        return true;
    }
}
