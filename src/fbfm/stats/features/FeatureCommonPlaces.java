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
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Group;
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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number of places user and other profile have been in",
          description="The number of same places been by the 2 users, this is also contains tagged in others' checkins",
          statType = StatType.FEATURE)
public class FeatureCommonPlaces extends Stat{
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
    Collection<Object> params = parameters.get("profileId");
    
    StatResponse response = new StatResponse();

    User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id"));
    String userId = user.getId();
 
    Set<String> userPlaces;
    CacheUtility cache = CacheUtility.getInstance();
    if (cache.hasCacheData(userId, "userCheckins", "checkinSet")) {
        Map<Object, Object> placesMap = cache.getCacheData(userId, "userCheckins", "checkinSet");
        userPlaces = (Set<String>)placesMap.get("userPlaces");
    } else {
        userPlaces = new HashSet<String>();
        Connection<JsonObject> myLocationConnection = facebookClient.fetchConnection("me/locations", JsonObject.class, Parameter.with("fields", "place"));

        for (List<JsonObject> myLocationConnectionPage : myLocationConnection) {          
            for (JsonObject location : myLocationConnectionPage) {

                if (location.has("place")) { // add the place id (place is actually a page of a place)
                    userPlaces.add(location.getJsonObject("place").getString("id"));
                }
            }

        }
        
        cache.addUserCacheData(userId, "userCheckins", "checkinSet", "userPlaces", userPlaces);
    }
    // we got here so we have userPlaces set with user places ids in it
    // now get the friends' check-ins and places
    
    for (Object profileId : params) {
        Set<String> profilePlaces = new HashSet<String>();
        Set<String> locations = new HashSet<String>();
        // locations gives same locations after a while so we will have to end it
        boolean end = false;
        // same as for user but without caching
        Connection<JsonObject> profileLocationConnection = facebookClient.fetchConnection(profileId.toString()+"/locations", JsonObject.class, Parameter.with("fields", "place"));
        for (List<JsonObject> profileLocationConnectionPage : profileLocationConnection) {
            for (JsonObject location : profileLocationConnectionPage) {
                String locationId = location.getString("id");
                if (locations.contains(locationId)) {
                    end = true;
                    break;
                }
                locations.add(locationId);
                DebugUtility.println("running on location: " + location);
                if (location.has("place")) { // add the place id (place is actually a page of a place)
                    String id = location.getJsonObject("place").getString("id");
                    if (profilePlaces.contains(id)) {
                        end = true;
                        break;
                    }
                    profilePlaces.add(id);
                    DebugUtility.println("found place: " + location.getJsonObject("place").getString("id"));
                }
  
            }
            
            if (end == true) {
                break;
            }
        }
        
        // after calculating profile's places id, get the intersection with user's places
        Set<String> commonPlaces = Sets.intersection(userPlaces, profilePlaces);
        // print debug - common places ids
        DebugUtility.println(commonPlaces);
        response.setValue(profileId.toString(),new StatValue<>(commonPlaces.size(), 0, 100000) );

    }
    return response;
  }
}
