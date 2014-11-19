/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import fbfm.StatInfo;
import fbfm.StatType;
import java.util.Set;
import org.reflections.Reflections;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatUtility {
    
    static private SetMultimap<StatType,Class<?>> stats = null;
    
    private StatUtility() {
        
    }
    
    // load all the stats from statInfo annotation in fbfm package
    static private void buildStatCollection() 
    {
        if (StatUtility.stats == null) {
            StatUtility.stats = HashMultimap.create();
            
            Reflections reflections = new Reflections("fbfm.stats");
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(StatInfo.class);
            
            for (Class<?> stat : annotated) {
                StatInfo statInfo = stat.getAnnotation(StatInfo.class);
                StatUtility.stats.put(statInfo.statType(), stat) ;
            }
        }
    }
    
    /**
     * @return a set of available stat classes
     */
    static public Set<Class<?>> getAvailableStats() {
        StatUtility.buildStatCollection();
        
        return Sets.union(StatUtility.getAvailableFeatures(), StatUtility.getAvailableProperties()).immutableCopy();
    }
    
    /**
     * @return a set of available property classes
     */
    static public Set<Class<?>> getAvailableProperties() {
        StatUtility.buildStatCollection();
        
        return stats.get(StatType.PROPERTY);
    }
    
    /**
     * @return a set of available features classes
     */
    static public Set<Class<?>> getAvailableFeatures() {
        StatUtility.buildStatCollection();
        
        return stats.get(StatType.FEATURE);
    }

}
