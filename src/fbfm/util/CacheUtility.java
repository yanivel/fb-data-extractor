/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Table;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Cache singleton utility to cache results got from facebook
 * can store a cache for different users in a table like manner columns, row value
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class CacheUtility {
    
    protected Map<String, Map<String, Table<Object, Object, Object>>> userCaches = null;
    
    private CacheUtility() {
        this.initCache();
    }
    
    private static class LazyHolder {
        private static final CacheUtility INSTANCE = new CacheUtility();
    }
 
    public static CacheUtility getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    protected void initCache() {
        if (this.userCaches == null) {
            this.userCaches = new HashMap<>();
        }
    }
    
    /**
     * adds a user cache
     * 
     * @param userId
     * @param cacheName 
     */
    public void addUserCache(String userId, String cacheName) {

        Map<String, Table<Object, Object, Object>> cache = new HashMap<>();
        
        cache.put(cacheName, HashBasedTable.create());
        
        this.userCaches.put(userId, cache);
        
    }
    
    /**
     * adds data to a user cache, will create the cache if it doesn't exist
     * 
     * @param userId
     * @param cacheName
     * @param r row
     * @param c column
     * @param v value
     */
    public void addUserCacheData(String userId, String cacheName, Object r, Object c, Object v)
    {
       
        if (!this.userCaches.containsKey(userId)) {
            this.addUserCache(userId, cacheName);
        } 
        
        Map<String, Table<Object, Object, Object>> map = this.userCaches.get(userId);
        if (!map.containsKey(cacheName)) {
            map.put(cacheName, HashBasedTable.create());
        }
        
        map.get(cacheName).put(r, c, v);
    }
    
    /**
     * get the data at a given user's cache and row
     * 
     * @param userId
     * @param cacheName
     * @param r
     * @return Map<Object,Object> 
     */
    public Map<Object,Object> getCacheData(String userId, String cacheName, Object r)
    {
    
        Map<Object,Object> returnValue = null;
        if (this.userCaches.containsKey(userId)) {
            Map<String, Table<Object, Object, Object>> map = this.userCaches.get(userId);
            if (map.containsKey(cacheName)) {
                returnValue = map.get(cacheName).row(r);
            }
        }
        
        return returnValue;
    }
    
    /**
     * user has cache?
     * 
     * @param userId
     * @param cacheName
     * @return true if user has a cache
     */
    public boolean hasCacheData(String userId, String cacheName) {
        return  (this.userCaches.containsKey(userId) && this.userCaches.get(userId).containsKey(cacheName));
    }
    
    /**
     * has cache data in row
     * 
     * @param userId
     * @param cacheName
     * @param r
     * @return true if user has cache data in row 
     */
    public boolean hasCacheData(String userId, String cacheName, Object r) {

        if (this.userCaches.containsKey(userId)) {
            Map<String, Table<Object, Object, Object>> map = this.userCaches.get(userId);
            if (map.containsKey(cacheName) && map.get(cacheName).containsRow(r)) {
                return true;
            }
        }
        
        return false;
    }
    
}
