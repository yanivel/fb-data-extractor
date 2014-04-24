/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.SetMultimap;
import com.restfb.FacebookClient;
import com.restfb.Connection;
import com.restfb.types.User;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatParameters;
import fbfm.StatValue;
import java.util.Collection;


/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number of mutual friends",
          description="The number of mutual friends between 2 profiles which are not friends",
          statType = StatType.FEATURE)
public class FeatureMutualFriends extends Stat{
    
    
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
  protected StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters )
  {
      
    Collection<Object> params = parameters.get("profileId");
      
    StatResponse response = new StatResponse();
    int mutualCount = 0;
    for (Object profileId : params) {
        Connection<User> mutualFriends = facebookClient.fetchConnection("me/mutualfriends/"+profileId, User.class);
        mutualCount = mutualFriends.getData().size();
        response.setValue(profileId.toString(), new StatValue<>(mutualCount, 0, 10000));
    }
    return response;
  }
}
