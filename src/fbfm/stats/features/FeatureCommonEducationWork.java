/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.stats.features;

import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
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
import fbfm.util.CacheUtility;
import fbfm.util.DebugUtility;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
@StatInfo(name="Number of common education/work places between 2 users",
          description="Number of common education/work places between 2 users, including school and workplace",
          statType = StatType.FEATURE)
public class FeatureCommonEducationWork extends Stat {
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
 
    Set<String> userEduSchool;
    Set<String> userEduDegree;
    Set<String> userWorkEmployer;
    Set<String> userWorkPosition;
    
    CacheUtility cache = CacheUtility.getInstance();
    if (cache.hasCacheData(userId, "userEducationWork", "Sets")) {
        Map<Object, Object> EduWorkSets = cache.getCacheData(userId, "userEducationWork", "Sets");
        userEduSchool = (Set<String>)EduWorkSets.get("EduSchool");
        userEduDegree = (Set<String>)EduWorkSets.get("EduDegree");
        userWorkEmployer = (Set<String>)EduWorkSets.get("WorkEmployer");
        userWorkPosition = (Set<String>)EduWorkSets.get("WorkPosition");
    } else {
        userEduSchool = new HashSet<String>();
        userEduDegree = new HashSet<String>();
        userWorkEmployer = new HashSet<String>();
        userWorkPosition = new HashSet<String>();

        JsonObject details = facebookClient.fetchObject("me", JsonObject.class, 
                Parameter.with("fields", "education.school,education.degree,work.employer,work.position"));

        if (details.has("education")) {
            JsonArray educations = details.getJsonArray("education");
            int length = educations.length();
            for (int i=0; i<length; ++i) {
                JsonObject education = educations.getJsonObject(i);

                if (education.has("degree")) {
                    userEduDegree.add(education.getJsonObject("degree").getString("id"));
                }
                if (education.has("school")) {
                    userEduSchool.add(education.getJsonObject("school").getString("id"));
                }
            }
        }

        if (details.has("work")) {
            JsonArray works = details.getJsonArray("work");
            int length = works.length();
            for (int i=0; i<length; ++i) {
                JsonObject work = works.getJsonObject(i);

                if (work.has("employer")) {
                    userWorkEmployer.add(work.getJsonObject("employer").getString("id"));
                }
                if (work.has("position")) {
                    userWorkPosition.add(work.getJsonObject("position").getString("id"));
                }
            }
        }
     
        
        cache.addUserCacheData(userId, "userEducationWork", "Sets", "EduSchool", userEduSchool);
        cache.addUserCacheData(userId, "userEducationWork", "Sets", "EduDegree", userEduDegree);
        cache.addUserCacheData(userId, "userEducationWork", "Sets", "WorkEmployer", userWorkEmployer);
        cache.addUserCacheData(userId, "userEducationWork", "Sets", "WorkPosition", userWorkPosition);
        
        DebugUtility.println("userEduSchool :" + userEduSchool);
        DebugUtility.println("EduDegree :" + userEduDegree);
        DebugUtility.println("WorkEmployer :" + userWorkEmployer);
        DebugUtility.println("WorkPosition :" + userWorkPosition);
    }
    // we got here so we have userPlaces set with user places ids in it
    // now get the friends' check-ins and places
    
    
    for (Object profileId : params) {
        Set<String> profileEduSchool = new HashSet<String>();
        Set<String> profileEduDegree = new HashSet<String>();
        Set<String> profileWorkEmployer = new HashSet<String>();
        Set<String> profileWorkPosition = new HashSet<String>();

        JsonObject profileDetails = facebookClient.fetchObject(profileId.toString(), JsonObject.class, 
                Parameter.with("fields", "education.school,education.degree,work.employer,work.position"));

        if (profileDetails.has("education")) {
            JsonArray educations = profileDetails.getJsonArray("education");
            int length = educations.length();
            for (int i=0; i<length; ++i) {
                JsonObject education = educations.getJsonObject(i);

                if (education.has("degree")) {
                    profileEduDegree.add(education.getJsonObject("degree").getString("id"));
                }
                if (education.has("school")) {
                    profileEduSchool.add(education.getJsonObject("school").getString("id"));
                }
            }
        }

        if (profileDetails.has("work")) {
            JsonArray works = profileDetails.getJsonArray("work");
            int length = works.length();
            for (int i=0; i<length; ++i) {
                JsonObject work = works.getJsonObject(i);

                if (work.has("employer")) {
                    profileWorkEmployer.add(work.getJsonObject("employer").getString("id"));
                }
                if (work.has("position")) {
                    profileWorkPosition.add(work.getJsonObject("position").getString("id"));
                }
            }
        }
        
        // after calculating profile's education/work sets, get the intersection with user's sets
        Set<String> commonEduDegree = Sets.intersection(userEduDegree, profileEduDegree);
        Set<String> commonEduSchool = Sets.intersection(userEduSchool, profileEduSchool);
        Set<String> commonWorkEmployer = Sets.intersection(userWorkEmployer, profileWorkEmployer);
        Set<String> commonWorkPosition = Sets.intersection(userWorkPosition, profileWorkPosition);
        // print debug - common places ids
        DebugUtility.println("commonEduDegree: "+ commonEduDegree);
        DebugUtility.println("commonEduSchool: "+ commonEduSchool);
        DebugUtility.println("commonWorkEmployer: "+ commonWorkEmployer);
        DebugUtility.println("commonWorkPosition: "+ commonWorkPosition);
        int commonSetsCount = commonEduDegree.size() + commonEduSchool.size() + commonWorkEmployer.size() + commonWorkPosition.size();
        response.setValue(profileId.toString(),new StatValue<>(commonSetsCount, 0, 100000) );

    }
    return response;
  }    
}
