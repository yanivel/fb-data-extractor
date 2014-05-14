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
    
    static protected Map<String, Map<String, Table<Object, Object, Object>>> userCaches = null;
    
    private CacheUtility() {
        
    }
    
    static protected void initCache() {
        if (CacheUtility.userCaches == null) {
            CacheUtility.userCaches = new HashMap<>();
        }
    }
    
    static public void addUserCache(String userId, String cacheName) {
        CacheUtility.initCache();
        
        
        Map<String, Table<Object, Object, Object>> cache = new HashMap<>();
        
        cache.put(cacheName, HashBasedTable.create());
        
        CacheUtility.userCaches.put(userId, cache);
        
    }
    
    static public void addUserCacheData(String userId, String cacheName, Object r, Object c, Object v)
    {
        CacheUtility.initCache();
        
        if (!CacheUtility.userCaches.containsKey(userId)) {
            CacheUtility.addUserCache(userId, cacheName);
        } 
        
        Map<String, Table<Object, Object, Object>> map = CacheUtility.userCaches.get(userId);
        if (!map.containsKey(cacheName)) {
            map.put(cacheName, HashBasedTable.create());
        }
        
        map.get(cacheName).put(r, c, v);
    }
    
    static public Map<Object,Object> getCacheData(String userId, String cacheName, Object r)
    {
        Map<Object,Object> returnValue = null;
        if (CacheUtility.userCaches.containsKey(userId)) {
            Map<String, Table<Object, Object, Object>> map = CacheUtility.userCaches.get(userId);
            if (map.containsKey(cacheName)) {
                returnValue = map.get(cacheName).row(r);
            }
        }
        
        return returnValue;
    }
    
}
