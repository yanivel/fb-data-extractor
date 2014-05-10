/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import fbfm.util.FacebookUtility;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    
    // timeout between calls to facebook in milliseconds
    protected int timeout = 0;
    
    protected StatExtractedData statData;
    
    
    public StatExtractor() 
    {
        this.profileIds = new ArrayList<>();
        this.stats = new HashSet();
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
                // TODO timeout option!!!!!!!!!
                try {
                    Stat stat = (Stat)statClass.newInstance(); 
                    
                    SetMultimap<String, Object> params = HashMultimap.create();
                    params.put("profileId", profileId);
                    
                    StatResponse result = stat.performCalculation(FacebookUtility.getFacebookClient(), params );
                    
                    this.statData.addData(result);
                } catch (Exception ex) { // change to approprtiate errors
                    // TODO: need here something??
                }
            }
        }
        
        return true;
    }
    
    // TO DO
    public boolean saveToFile(String filename)
    {
        
        return true;
    }
}
