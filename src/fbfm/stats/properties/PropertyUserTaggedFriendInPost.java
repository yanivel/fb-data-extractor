/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.Iterables;
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
@StatInfo(name="Number times user tagged friend in his posts",
          description="Number times user tagged friend in his posts where there are N tagged users.",
          statType = StatType.PROPERTY)
public class PropertyUserTaggedFriendInPost extends Stat{
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
        @StatParameters.RequiredParameter(name="tagAmount")
    })
    @Override
    // TODO: add time limit to query 
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("profileId");
    String tagAmount = Iterables.getFirst(parameters.get("tagAmount"), "").toString();

    StatResponse response = new StatResponse();
    
    User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id"));
    String userId = user.getId();
    
    int numFriendTags = 0;
 
    for (Object profileId : params) {     
        
        Connection<JsonObject> myFeed = facebookClient.fetchConnection("me/feed", JsonObject.class, Parameter.with("fields", "from,message_tags,with_tags,status_type"));

        for (List<JsonObject> myFeedConnectionPage : myFeed) {
            for (JsonObject post : myFeedConnectionPage) {
                
                JsonObject from = post.getJsonObject("from");
                boolean foundUser = false;
                if (from.getString("id").equals(userId)) {
                    // if this post is user tagged in other people's photo and shows as post
                    if (post.has("status_type") == false || post.getString("status_type").equals("tagged_in_photo") == false) {
                        if (post.has("message_tags") == true ) {
                            JsonObject messageTagsObj = post.getJsonObject("message_tags");
                            Iterator<?> messageTagsObjKeys = messageTagsObj.keys();
                            DebugUtility.println(messageTagsObj);
                            DebugUtility.println(post);
                            while (messageTagsObjKeys.hasNext() && foundUser == false) {
                                String key = messageTagsObjKeys.next().toString();
                                JsonArray messageTagsArrayForKey = messageTagsObj.getJsonArray(key);
                                int length = messageTagsArrayForKey.length();
                                
                                for (int i=0; i<length; ++i) {
                                    JsonObject tag = messageTagsArrayForKey.getJsonObject(i);
                                    
                                    if (tag.getString("id").equals(profileId)) {
                                        numFriendTags += 1;
                                        foundUser = true;
                                        break;
                                    }
                                }
                                
                            }
                        } 
                        if (post.has("with_tags") == true && foundUser == false) {
                            JsonObject withTagsObj = post.getJsonObject("with_tags");
                            JsonArray withTags = withTagsObj.getJsonArray("data");
                            DebugUtility.println(withTagsObj);
                            int numTags = withTags.length();
                            // if (numTags <= tagAmount)
                            for (int i=0; i<numTags; ++i) {
                                JsonObject tag = withTags.getJsonObject(i);
                                
                                if (tag.getString("id").equals(profileId)) {
                                    numFriendTags += 1;
                                    foundUser = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                
                
            }
        }
        
        response.setValue(profileId.toString(),new StatValue<>(numFriendTags, 0, 100000) );
        numFriendTags = 0;
    }
    return response;
  }
}
