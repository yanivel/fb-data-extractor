/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.SetMultimap;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import fbfm.Stat;
import fbfm.StatException;
import fbfm.StatInfo;
import fbfm.StatParameters;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatValue;
import fbfm.util.CacheUtility;
import fbfm.util.DebugUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
/*@StatInfo(name="Number of places user and other profile been in",
          description="The number of same places made with check-ins by 2 users",
          statType = StatType.FEATURE)*/
public class FeatureCheckinsPlace extends Stat{
   /** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters {@code: SetMultiMap} of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
    @StatParameters({
        @StatParameters.RequiredParameter(name="profileId"),
    })
    @Override
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters ) throws StatException
  {
    //Collection<Object> params = parameters.get("profileId");
    
    StatResponse response = new StatResponse();
    /*
    int numFriendLikes = 0;
    User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id"));
    String userId = user.getId();
 
    Connection<JsonObject> myFeed = facebookClient.fetchConnection("me/checkins", JsonObject.class, Parameter.with("fields", "place"));
    
    Set<String> userPlaces;
    
    for (List<JsonObject> myFeedConnectionPage : myFeed) {
            for (JsonObject checkin : myFeedConnectionPage) {
                
                if (post.has("place") == true) {
                    if (post.has("likes") == true ) {
                        JsonObject likes = post.getJsonObject("likes");
                        JsonArray likers = likes.getJsonArray("data");
                        int numLikers = likers.length();
                        for (int i=0; i<numLikers; ++i) {
                            JsonObject liker = likers.getJsonObject(i);

                            if (liker.getString("id").equals(profileId)) {
                                numFriendLikes += 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
    
    for (Object profileId : params) {     
 
        response.setValue(profileId.toString(),new StatValue<>(numFriendLikes, 0, 100000) );
        numFriendLikes = 0;
    }*/
    return response;
  }
}
