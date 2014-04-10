package fbfm;

/** 
 *  This class includes the details of Stat data, such as name, description, value and type.
 */
public class StatResponse {

  protected StatValue<?> value;

  protected String description;

  protected String name;
  
  /**
   * StatResponse constructor
   * 
   * @param name name of the stat
   * @param description description of the stat
   */
  StatResponse(String name, String description)
  {
      this.name = name;
      this.description = description;
  }
  
  /**
   * Set the StatResponse value
   * 
   * @param value a StatValue
   */
  public void setValue(StatValue<?> value) {
      this.value = value;
  }
  
  /**
   * return the value of the StatResponse
   * 
   * @return StatValue
   */
  public StatValue<?> getValue() {
      return this.value;
  }
  
  /**
   * 
   * @return String
   */
  @Override 
  public String toString() {
      return this.name + ": " 
              + this.description 
              + ". Value = " + this.value.toString();
  }

}