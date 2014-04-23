/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Group;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatParameters;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatValue;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Common groups of two profiles",
          description="The number of groups the two profiles are in together",
          statType = StatType.FEATURE)
public class FeatureCommonGroups extends Stat{
/** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters {@code: SetMultiMap} of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
    
    @StatParameters({
        @StatParameters.RequiredParameter(name="profileId")
    })
    @Override
    // add annotation of parameter needed
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("profileId");
    
    // get user likes - only id field to save bandwidth
    Connection<Group> userGroups = facebookClient.fetchConnection("me/groups", Group.class,
                                                                    Parameter.with("field", "id"));
    Set<String> userGroupSet = new HashSet<String>();
    for(List<Group> groupList : userGroups) {
        for (Group group : groupList) {
            userGroupSet.add(group.getId());
        }
    }
      
    StatResponse response = new StatResponse();
    
    for (Object profileId : params) {
        // get friends' likes
        Connection<Group> friendGroups = facebookClient.fetchConnection(profileId+"/groups", Group.class, 
                                                                            Parameter.with("field", "id"));
        // create set from friend likes
        Set<String> friendGroupSet = new HashSet<String>();
        for(List<Group> groupList : friendGroups) {
            for (Group group : groupList) {
                friendGroupSet.add(group.getId());
            }
        }
        
        // get common groups
        Set<String> commonGroupsSet = Sets.intersection(userGroupSet, friendGroupSet);
        
        // add value to response
        response.setValue(profileId.toString(), new StatValue<>(commonGroupsSet.size(), 0, 1000));
    }
    return response;
  }    
}
