package fbfm;

/** 
 *  This annotation is used to require a {@code Stat} parameters.
 * 
 * @author Yaniv Elimor
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StatParameters {
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface RequiredParameter {

        String name();

    }
    RequiredParameter[] value();

}

