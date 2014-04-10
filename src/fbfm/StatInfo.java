package fbfm;

/** 
 *  This annotation is used to generate data for a StatResponse.
 * 
 * @author Yaniv Elimor
 */
@interface StatInfo {

  String name();
  String description();
  StatType myStatType();

}
