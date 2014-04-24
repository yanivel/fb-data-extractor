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
import com.restfb.exception.FacebookOAuthException;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number times friend tagged user in photos",
          description="The number of times a friend tagged the user in photos in given tags count.",
          statType = StatType.PROPERTY)
public class PropertyFriendTaggedMePhoto extends Stat{
 
    /** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters {@code: SetMultiMap} of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
    // TODO: add tag amount to the equation :)
    @StatParameters({
        @StatParameters.RequiredParameter(name="friendId"),
        @StatParameters.RequiredParameter(name="tagAmount") // number of people tagged in the photo - to prevent promoter data
    })
    @Override
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters ) throws StatException
  {
      
    Collection<Object> params = parameters.get("friendId");
    String timePeriod = Iterables.getFirst(parameters.get("tagAmount"), "").toString();
    
    StatResponse response = new StatResponse();
    // get user's id
    User user = facebookClient.fetchObject("me", User.class);
    String userId = user.getId();
    
    // get all photos I'm tagged in
    String query = "SELECT object_id " +
                        "FROM photo " +
                        "WHERE object_id IN (SELECT object_id FROM photo_tag WHERE subject = me());";
    List<JsonObject> photoCount = facebookClient.executeFqlQuery(query, JsonObject.class);
    // change from json objects to String photo ids of tagged photos
    List<String> photoIds = new ArrayList<>();
    for(JsonObject photoObject : photoCount) {
        photoIds.add(photoObject.getString("object_id"));
    }
    
    // run for every friend in the parameters
    for (Object friendId : params) {
        int tagCounter = 0;
        // if user has no photos he is tagged in, just put empty tagCounter values for all the friends
        if (!photoIds.isEmpty()) {
            for (String photoId : photoIds) {
                // get photo tags
                System.out.println("getting tags for photo : " + photoId);
               
                try {
                    JsonObject photoTagsConnection = facebookClient.fetchObject(photoId+"/tags", JsonObject.class,
                                                                    Parameter.with("fields", "tagging_user"));
                    
                    JsonArray photoTags = photoTagsConnection.getJsonArray("data");
                    System.out.println("finished reading photo tags, total tags for photo is : " + photoTags.length());
                    int numTags = photoTags.length();
                    for (int i=0; i<numTags; ++i) {
                        System.out.println("running on tag " + i);
                        // get tag objects and check if the tag is of the user and the tagger is the friend
                        JsonObject tag = photoTags.getJsonObject(i);
                        if (tag.getString("id").equals(userId)) {
                            if (tag.getJsonObject("tagging_user").getString("id").equals(friendId)) {
                                tagCounter += 1;
                            }
                        }
                    }
                } catch (FacebookOAuthException e) {
                    if (e.getErrorCode() == 1) {
                        System.out.println("error with photo " + photoId + " (probably old photo with no tagging data) - continuing.");
                    } else {
                        throw new StatException("thrown error : " + e.getErrorMessage() + " : code " + e.getErrorCode());
                    }
                }
                
                System.out.println("finished photo : " + photoId);
            }
        } 
        response.setValue(friendId.toString(), new StatValue<>(tagCounter,0,100000) );
    }
    return response;
  }    
}
