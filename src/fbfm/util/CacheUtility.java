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
    
    public void addUserCache(String userId, String cacheName) {

        Map<String, Table<Object, Object, Object>> cache = new HashMap<>();
        
        cache.put(cacheName, HashBasedTable.create());
        
        this.userCaches.put(userId, cache);
        
    }
    
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
    
    
    public boolean hasCacheData(String userId, String cacheName) {
        return  (this.userCaches.containsKey(userId) && this.userCaches.get(userId).containsKey(cacheName));
    }
    
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