/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.restfb.Connection;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatParameters;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatValue;
import fbfm.util.DebugUtility;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Ratio of common likes",
          description="The ratio (0 to 1) of common page likes in relation to number of total likes of two profiles.",
          statType = StatType.FEATURE)
public class FeatureCommonLikeRatio extends Stat {
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
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("profileId");
    
    // get user likes - only id field to save bandwidth
    Connection<Page> userPageLikes = facebookClient.fetchConnection("me/likes", Page.class,
                                                                    Parameter.with("field", "id"));
    Set<String> userLikeSet = new HashSet<String>();
    for(List<Page> likeList : userPageLikes) {
        for (Page like : likeList) {
            userLikeSet.add(like.getId());
        }
    }
    int numUserLikes = userLikeSet.size();
      
    StatResponse response = new StatResponse();
    DebugUtility.println("num user likes : " + numUserLikes);
    for (Object friendId : params) {
        // get friends' likes
        Connection<Page> friendPageLikes = facebookClient.fetchConnection(friendId+"/likes", Page.class, 
                                                                            Parameter.with("field", "id"));
        // create set from friend likes
        Set<String> friendLikeSet = new HashSet<String>();
        for(List<Page> likeList : friendPageLikes) {
            for (Page like : likeList) {
                friendLikeSet.add(like.getId());
            }
        }
        DebugUtility.println("num of friend's likes : " + friendLikeSet.size());
        // get common likes
        Set<String> commonSet = Sets.intersection(userLikeSet, friendLikeSet);
        DebugUtility.println("num of intersection likes : " + commonSet.size());
        // get like ratio
        int commonSetSize = commonSet.size();
        int totalNumLikes = numUserLikes + friendLikeSet.size() - commonSetSize;
        float commonLikeRatio = (1.0f * commonSetSize) / totalNumLikes;
        // add value to response
        response.setValue(friendId.toString(), new StatValue<>(commonLikeRatio, 0.0f, 1.0f));
    }
    return response;
  }
}
