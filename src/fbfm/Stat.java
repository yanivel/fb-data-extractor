package fbfm;


import com.restfb.FacebookClient;
import com.google.common.collect.SetMultimap;
import com.restfb.types.User;
import fbfm.util.DebugUtility;
import java.lang.reflect.Method;
import java.util.Map;



/** 
 *  The {@code Stat} class is an abstract class that should be base class for all {@code Stat} derived classes that extract data from facebook.
 *  Derive this class and implement calculateStat for each feature/property.
 */
public abstract class Stat {
    
  /** 
   *  Performs calculation of the {@code Stat} in facebook
   *  
   *  @param facebookClient facebook client instance
   *  @param parameters Zero or more Parameters for the calculations
   *  @throws StatException 
   *  @throws BadParameterException
   * @return StatResponse the {@code Stat} response of the calculation
   */
  public StatResponse performCalculation(FacebookClient facebookClient, SetMultimap<String,Object> parameters ) throws StatException,
                                                                                                    BadParameterException{
      DebugUtility.println(this.getClass() + ":");
      StatResponse response = this.calculateStat(facebookClient, parameters);
      
      StatInfo statInfo = this.getClass().getAnnotation(StatInfo.class);
      if (statInfo == null) 
          throw new StatException("Must annotate Stat "+this.getClass()+ " with StatInfo annotation");
      
      String name = statInfo.name();
      String description = statInfo.description();
      
      // checks
      if (name == null || description == null || name.isEmpty())
          throw new StatException(this.getClass()+ "'s StatInfo annotation is invalid.");

      this.checkParameters(parameters);
      
      response.setName(statInfo.name());
      response.setDescription(statInfo.description());
      
      User user = facebookClient.fetchObject("me", User.class);
      response.setUserId(user.getId());
      response.setStatClass(this.getClass());
      
      return response;
  }

  /** 
   *  Check that the parameters received are the required parameters by calculateState method
   * @param parameters
   * @throws BadParameterException
   */
  protected void checkParameters(SetMultimap<String,Object> parameters) throws BadParameterException
  {
      Class c = this.getClass();
  
      Method m;
      try {
         m = c.getDeclaredMethod("calculateStat", FacebookClient.class, SetMultimap.class);
      } catch (NoSuchMethodException exc) {
          throw new BadParameterException(exc);
      }
      
      if (m.isAnnotationPresent(StatParameters.class)) {

          StatParameters.RequiredParameter[] requiredParams = m.getAnnotation(StatParameters.class).value();
          for (StatParameters.RequiredParameter requiredParameter : requiredParams) {
          
              boolean exists = false;
                for (Map.Entry<String,Object> entry : parameters.entries()) {
                if (requiredParameter.name().equals(entry.getKey())) {
                    exists = true;
                    break;
                }
              }
              
              if (!exists) {
                  throw new BadParameterException("calculateStat missing parameter : " + requiredParameter.name());
              }
          }
          
      }
  }
  
  /** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters Zero or more Parameters for the calculations
   *  @throws StatException
   * @return StatResponse the {@code Stat} response of the calculation
   */
  protected abstract StatResponse calculateStat(FacebookClient facebookClient, SetMultimap<String,Object> parameters ) throws StatException;

}