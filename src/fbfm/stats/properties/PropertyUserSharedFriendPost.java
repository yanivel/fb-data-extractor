/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.SetMultimap;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatParameters;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatValue;
import fbfm.util.DebugUtility;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number times user shared friend's post",
          description="Number times user shared a story or post of a friend on his wall.",
          statType = StatType.PROPERTY)
public class PropertyUserSharedFriendPost extends Stat{
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
    
    User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id"));
    String userId = user.getId();
    
    int numShares = 0;
 
    for (Object profileId : params) {     
        JsonObject profileObject = facebookClient.fetchObject(profileId.toString(), JsonObject.class, Parameter.with("fields", "link"));
        String profileLink = profileObject.getString("link");
        Connection<JsonObject> myFeed = facebookClient.fetchConnection("me/feed", JsonObject.class, Parameter.with("fields", "from,properties,status_type"));

        for (List<JsonObject> myFeedConnectionPage : myFeed) {
            for (JsonObject post : myFeedConnectionPage) {
                
                JsonObject from = post.getJsonObject("from");

                if (from.getString("id").equals(userId)) {
                    // if this post is a shared post it should have status_type equals shared_story and properties array
                    if (post.has("status_type") == true && post.has("properties") == true) {
                        if (post.getString("status_type").equals("shared_story")) {
                            JsonArray properties = post.getJsonArray("properties");
                            int length = properties.length();
                            for (int i=0; i<length; ++i) {
                                JsonObject property = properties.getJsonObject(i);
                                if (property.has("name") == false || property.has("href") == false) 
                                    continue;
                                else {
                                    if (property.getString("name").equals("By")
                                            && property.getString("href").equals(profileLink)) {
                                        DebugUtility.println(post);
                                        numShares++;
                                    }
                                }
                            }
                        }
                    }
                }
                
                
            }
        }
        
        response.setValue(profileId.toString(),new StatValue<>(numShares, 0, 100000) );
        numShares = 0;
    }
    return response;
  }
}
