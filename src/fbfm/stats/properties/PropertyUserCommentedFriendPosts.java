/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

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
@StatInfo(name="Number times user commented on posts in friends's profile",
          description="Number times user commented on friend posts in friends's profile.",
          statType = StatType.PROPERTY)
public class PropertyUserCommentedFriendPosts extends Stat{
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
    
    int offsetVar = 500;
    int userPostComments = 0;
    
    for (Object profileId : params) {
        int offset = 0;
        int numOfPosts = 10000;
        while (numOfPosts >= 0) {
            DebugUtility.println("offset is " + offset);
            String query = "Select post_id FROM stream where source_id='" + profileId + "' "
                           + "AND actor_id='"+profileId+"' "
                           + /*AND comment_info.comment_count>0*/ " LIMIT "+offset+ ","+(offset+offsetVar-1) ;
            List<JsonObject> posts = facebookClient.executeFqlQuery(query, JsonObject.class);
            for (JsonObject post : posts) {
                String postId = post.getString("post_id");
                JsonObject commentConnection = facebookClient.fetchObject(postId+"/comments", JsonObject.class, 
                                                    Parameter.with("fields", "from"));
                JsonArray comments = commentConnection.getJsonArray("data");
                int numComments = comments.length();
                for (int i=0; i<numComments; ++i) {
                    JsonObject comment = comments.getJsonObject(i);
                    String commentatorId = comment.getJsonObject("from").getString("id");
                    if (commentatorId.equals(userId)) {
                        userPostComments += 1;
                        DebugUtility.println(post);
                    }
                }
            }
            
            
      
            offset += offsetVar;
            numOfPosts -= offsetVar;
        }
        response.setValue(profileId.toString(),new StatValue<>(userPostComments, 0, 10000) );
        userPostComments = 0;
    }
    return response;
  }   
}
