package fbfm;

import com.google.common.collect.Iterators;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** 
 *  This class includes the details of stat data, such as name, description, value and type.
 */
public class StatResponse {

  protected List<StatValue<?>> values;

  protected String description;

  protected String name;
  
  
  
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
      this.values = new ArrayList<>();
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
  public void setValue(StatValue<?> value) {
      this.values.add(value);
  }
  
  /**
   * return the value of the StatResponse
   * 
   * @return StatValue
   */
  public Iterator<StatValue<?>> getValues() {
      return Iterators.unmodifiableIterator(this.values.iterator());
  }
  
  /**
   * 
   * @return String
   */
  @Override 
  public String toString() {
      String returnValue = this.name + ": " 
                         + this.description 
                         + ". Values = " + Joiner.on(",").join(this.values); 
      
      
      return returnValue;
  }

}