/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

import com.google.common.collect.Range;

/**
 * Contain the a Stat value including the range and type {@link StatValueType}
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 * @param <T> should be Integer/Long or Float/Double
 */
public class StatValue<T extends Comparable> {
    protected T value;
    protected Range<T> range;

    /**
     * Create the stat value with a value and a closed range
     * 
     * @param value the value of the stat
     * @param lowerRange lower range of the stat
     * @param upperRange upper range of the stat
     */
    public StatValue(T value, T lowerRange, T upperRange) {
        this.value = value;
        this.range = Range.closed(lowerRange, upperRange);
    }
    
    /**
     * Return the range of the StatValue
     * 
     * @return Range
     */
    public Range<T> getRange() {
        return this.range;
    }
    
    /**
     * Returns the value of the StatValue
     * 
     * @return T the value of the StatValue
     */
    public T getValue() {
        return this.value;
    }
    
    public String stringValue()
    {
        return this.value.toString();
    }
    
    /**
     * 
     * @return String
     */
    @Override 
    public String toString() {
        return this.value + " (Range: " + this.range.toString() + ")";
    }
}
