package fbfm;


import com.restfb.FacebookClient;
import java.util.HashMap;

/** 
 *  The Stat class is an abstract class that should be base class for all Stat derived classes that extract data from facebook.
 *  Derive this class and implement calculateStat for each feature/property.
 */
public abstract class Stat {
    
  /** 
   *  Performs calculation of the Stat in facebook
   *  
   *  @param facebookClient facebook client instance
   *  @param parameters Hashtable of strings, parameters for the calculations
   *  @throws StatException 
   * @return StatResponse the stat response of the calculation
   */
  public StatResponse performCalculation(FacebookClient facebookClient, HashMap<String, String> parameters ) throws StatException {
      
      StatResponse response = this.calculateStat(facebookClient, parameters);

      // set response name and description from annotation
      StatInfo statInfo = this.getClass().getAnnotation(StatInfo.class);
      String name = statInfo.name();
      String description = statInfo.description();
      
      if (name == null || description == null || name.isEmpty())
          throw new StatException(this.getClass()+ "'s StatInfo annotation is invalid.");
              
      response.setName(statInfo.name());
      response.setDescription(statInfo.description());
      
      return response;
  }

  /** 
   *  The method that does the actual calculation. 
   *  Override this in derived classes for use.
   * 
   *  @param facebookClient facebook client instance
   *  @param parameters Hashtable of strings, parameters for the calculations
   * @return StatResponse the stat response of the calculation
   */
  protected abstract StatResponse calculateStat(FacebookClient facebookClient, HashMap<String, String> parameters );

}