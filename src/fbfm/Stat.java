package fbfm;


import com.restfb.FacebookClient;
import com.restfb.Parameter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


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
  public StatResponse performCalculation(FacebookClient facebookClient, Parameter... parameters ) throws StatException,
                                                                                                    BadParameterException{
      
      StatResponse response = this.calculateStat(facebookClient, parameters);
      
      StatInfo statInfo = this.getClass().getAnnotation(StatInfo.class);
      String name = statInfo.name();
      String description = statInfo.description();
      
      // checks
      if (name == null || description == null || name.isEmpty())
          throw new StatException(this.getClass()+ "'s StatInfo annotation is invalid.");

      this.checkParameters(parameters);
      
      response.setName(statInfo.name());
      response.setDescription(statInfo.description());
      
      return response;
  }

  /** 
   *  Check that the parameters received are the required parameters by calculateState method
   * @param parameters
   * @throws BadParameterException
   */
  protected void checkParameters(Parameter... parameters) throws BadParameterException
  {
      Class c = this.getClass();
  
      Method m;
      try {
         m = c.getDeclaredMethod("calculateStat", FacebookClient.class, Parameter[].class);
      } catch (NoSuchMethodException exc) {
          throw new BadParameterException(exc);
      }
      
      if (m.isAnnotationPresent(StatParameters.class)) {

          RequiredParameter[] requiredParams = m.getAnnotation(StatParameters.class).value();
          for (Parameter parameter : parameters) {
              boolean exists = false;
              for (RequiredParameter requiredParameter : requiredParams) {
                if (requiredParameter.name().toLowerCase().equals(parameter.name)) {
                    exists = true;
                    break;
                }
              }
              
              if (!exists) {
                  throw new BadParameterException("calculateStat paremeters missing parameter : " + parameter.name);
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
   * @return StatResponse the {@code Stat} response of the calculation
   */
  protected abstract StatResponse calculateStat(FacebookClient facebookClient, Parameter... parameters );

}