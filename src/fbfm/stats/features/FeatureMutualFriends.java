/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Connection;
import com.restfb.types.User;
import fbfm.Stat;
import fbfm.StatInfo;
import fbfm.StatResponse;
import fbfm.StatType;
import fbfm.StatParameters;
import fbfm.StatValue;


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
   *  @param parameters Hashtable of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
    
    @StatParameters({
        @StatParameters.RequiredParameter(name="friendId")
    })
    @Override
    // add annotation of parameter needed
  protected StatResponse calculateStat(FacebookClient facebookClient, Parameter... parameters )
  {
      String friendId = "";
      for (Parameter param : parameters) {
          if (param.name.equals("friendId")) {
              friendId = param.value;
          }
      }
      
      Connection<User> mutualFriends = facebookClient.fetchConnection("me/mutualfriends/"+friendId, User.class);
      
      int mutualCount = mutualFriends.getData().size();
      
      StatResponse response = new StatResponse();
      response.setValue(new StatValue<>(mutualCount, 0, 10000));
      
      return response;
  }
}
