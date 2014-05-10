package fbfm;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.SetMultimap;
import java.util.Map.Entry;
import java.util.Set;


/** 
 *  This class includes the details of stat data, such as name, description, value and type.
 */
public class StatResponse {

  protected SetMultimap<String,StatValue<?>> values;

  protected String description;

  protected String name;
  
  protected String userId;
  
  
  
  /**
   * StatResponse constructor
   * 
   * @param name name of the stat
   * @param description description of the stat
   */
  public StatResponse(String name, String description)
  {
      this.name = name;
      this.description = description;
      this.values = HashMultimap.create();
  }
  
  /**
   * StatResponse constructor
   * 
   */
  public StatResponse()
  {
      this("","");
  }
  
  /**
   * Set the StatResponse name
   * 
   * @param name
   */
  public void setName(String name) {
      this.name = name;
  }
  
  /**
   * return the name of the StatResponse
   * 
   * @return String
   */
  public String getName() {
      return this.name;
  }
  
  /**
   * Set the StatResponse userId
   * 
   * @param userId
   */
  public void setUserId(String userId) {
      this.userId = userId;
  }
  
  /**
   * return the userId of the StatResponse
   * 
   * @return String
   */
  public String getUserId() {
      return this.userId;
  }
  
  /**
   * Set the StatResponse description
   * 
   * @param description
   */
  public void setDescription(String description) {
      this.description = description;
  }
  
  /**
   * return the description of the StatResponse
   * 
   * @return String
   */
  public String getDescription() {
      return this.description;
  }
  
  /**
   * Set the StatResponse value
   * 
   * @param value a StatValue
   */
  public void setValue(String key, StatValue<?> value) {
      this.values.put(key, value);
  }
  
  /**
   * return the value of the StatResponse by key
   * 
   * @return StatValue
   */
  public StatValue<?> getValue(String key) {
      return Iterables.getFirst(this.values.get(key), null);
  }
  
    /**
   * return Iterator to values
   * 
   * @return Iterator
   */
  public Set<Entry<String,StatValue<?>>> getValues() {
      return this.values.entries();
  }
  
  /**
   * return Set of keys in the response
   * 
   * @return Set of keys
   */
  public Set<String> getKeys() {
      return this.values.keySet();
  }
  
  /**
   * 
   * @return String
   */
  @Override 
  public String toString() {
      String returnValue = this.name + ": " 
                         + this.description 
                         + ". Values = " + Joiner.on(",").join(this.getValues()); 
      
      
      return returnValue;
  }

}