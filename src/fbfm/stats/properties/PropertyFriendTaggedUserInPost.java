/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.Iterables;
import com.google.common.collect.SetMultimap;
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
import java.util.List;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number times friend tagged user in posts in friends's profile",
          description="Number times friend tagged user in posts with certain amount of tags in friends's profile.",
          statType = StatType.PROPERTY)
public class PropertyFriendTaggedUserInPost extends Stat{
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
    
    int offsetVar = 500;
    
    for (Object profileId : params) {
        int userPostTags = 0;
        int offset = 0;
        int numOfPosts = 10000;
        while (numOfPosts >= 0) {
            DebugUtility.println("offset is " + offset);
            String query = "Select tagged_ids, with_tags FROM stream where source_id='" + profileId + "' "
                           + "AND actor_id='"+profileId+"' LIMIT "+offset+ ","+(offset+offsetVar-1) ;
            List<JsonObject> posts = facebookClient.executeFqlQuery(query, JsonObject.class);
            DebugUtility.println("found " + posts.size() + " posts for " + profileId);
            
            // no posts, so don't continue
            if (posts.isEmpty()) {
                break;
            }
            
            for (JsonObject post : posts) {
                boolean foundUser = false;
                if (post.has("with_tags") && foundUser == false) {
                    JsonArray tags = post.getJsonArray("with_tags");
                    int length = tags.length();
                    for (int i=0; i<length; ++i) {
                        String tag = tags.getString(i);
                        if (tag.equals(userId)) {
                            DebugUtility.println(post);
                            userPostTags++;
                            foundUser = true;
                            break;
                        }
                    }
                }
                if (post.has("tagged_ids") && foundUser == false) {
                    JsonArray tags = post.getJsonArray("tagged_ids");
                    int length = tags.length();
                    for (int i=0; i<length; ++i) {
                        String tag = tags.getString(i);
                        if (tag.equals(userId)) {
                            DebugUtility.println(post);
                            foundUser = true;
                            userPostTags++;
                            break;
                        }
                    }
                }
            }
 
            offset += offsetVar;
            numOfPosts -= offsetVar;

        }
        response.setValue(profileId.toString(),new StatValue<>(userPostTags, 0, 10000) );
        userPostTags = 0;
    }
    return response;
  }    
}
