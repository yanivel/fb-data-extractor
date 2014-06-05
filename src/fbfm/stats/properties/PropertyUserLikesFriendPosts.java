/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.SetMultimap;
import com.google.common.primitives.Ints;
import com.restfb.FacebookClient;
import com.restfb.json.JsonObject;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatParameters;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatValue;
import fbfm.util.DebugUtility;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number times user liked posts in friends's profile",
          description="Number times user liked friend posts in friends's profile.",
          statType = StatType.PROPERTY)
public class PropertyUserLikesFriendPosts extends Stat {
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
    // TODO: add time limit to query 
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("profileId");

    StatResponse response = new StatResponse();
    
    int offsetVar = 500;
    int numOfPosts = 10000;
    int userPostLikes = 0;
    
    for (Object profileId : params) {
        int offset = 0;
        while (numOfPosts >= 0) {
            DebugUtility.println("offset is " + offset);
            String query = "Select post_id, source_id FROM stream where source_id='" + profileId + "' "
                           + "AND actor_id="+profileId+" AND like_info.user_likes=1 LIMIT "+offset+ ","+(offset+offsetVar-1) ;
            List<JsonObject> postsCount = facebookClient.executeFqlQuery(query, JsonObject.class);
            DebugUtility.println("number of posts likes by user: " + postsCount.size());
            if (!postsCount.isEmpty()) {
                userPostLikes += postsCount.size();
                DebugUtility.println(profileId);
            }
      
            offset += offsetVar;
            numOfPosts -= offsetVar;
        }
        response.setValue(profileId.toString(),new StatValue<>(userPostLikes, 0, 10000) );
        userPostLikes = 0;
    }
    return response;
  }
}
