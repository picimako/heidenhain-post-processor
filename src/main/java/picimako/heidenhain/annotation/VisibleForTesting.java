package picimako.heidenhain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker interface for methods and constructors that are created only for unit testing.
 * <p>
 * It is created so that Guava is not need to be added as dependency only for this.
 *
 * @author Tamas Balog
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VisibleForTesting {
}
