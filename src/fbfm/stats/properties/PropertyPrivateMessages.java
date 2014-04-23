/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.properties;

import com.google.common.collect.Iterables;
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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number of private messages between friends",
          description="The number private messages between friends in a period of time",
          statType = StatType.PROPERTY)
public class PropertyPrivateMessages extends Stat{

    /** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters {@code: SetMultiMap} of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
    
    @StatParameters({
        @StatParameters.RequiredParameter(name="friendId"),
        @StatParameters.RequiredParameter(name="timePeriod") // days from now
    })
    @Override
    // add annotation of parameter needed
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("friendId");
    String timePeriod = Iterables.getFirst(parameters.get("timePeriod"), "").toString();
    
    StatResponse response = new StatResponse();
    
    for (Object profileId : params) {
        String query = "SELECT message_count FROM thread WHERE folder_id = 0 AND (originator = " + profileId + " OR (" + profileId + " in recipients))";
        List<JsonObject> messagesCount = facebookClient.executeFqlQuery(query, JsonObject.class);
        StatValue<?> value = (messagesCount.isEmpty()) 
                            ? new StatValue<>(0,0,100000) 
                            : new StatValue<>(Ints.tryParse(messagesCount.get(0).getString("message_count")), 0, 100000);
        response.setValue(profileId.toString(),value );
    }
    return response;
  }

}
