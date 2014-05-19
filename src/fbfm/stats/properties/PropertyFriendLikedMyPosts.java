/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.SetMultimap;
import com.google.common.primitives.Ints;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.json.JsonObject;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Post;
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
@StatInfo(name="Number times friend liked posts in user's profile",
          description="Number times friend liked user's posts in user's profile.",
          statType = StatType.PROPERTY)
public class PropertyFriendLikedMyPosts extends Stat{
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
    
    int numFriendLikes = 0;
    int timeoutMax = 20;
    int timeoutCount = 0;
            
    for (Object profileId : params) {     
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

        for (List<Post> myFeedConnectionPage : myFeed) {
            for (Post post : myFeedConnectionPage) {
                
                if (post.getLikes() != null ) {
                    Post.Likes postLikes = post.getLikes();
                    List<NamedFacebookType> users = postLikes.getData();
                    for (NamedFacebookType user: users) {
                        if (user.getId() == profileId) {
                            numFriendLikes += 1;
                            break;
                        }
                    }
                }
                
            }
            
            DebugUtility.println(myFeedConnectionPage);
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        response.setValue(profileId.toString(),new StatValue<>(numFriendLikes, 0, 100000) );
    }
    return response;
  }
}
