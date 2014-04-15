package fbfm;

/** 
 *  This annotation is used to generate data for a StatResponse.
 * 
 * @author Yaniv Elimor
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface StatInfo {

  String name();
  String description();
  StatType myStatType();

}
