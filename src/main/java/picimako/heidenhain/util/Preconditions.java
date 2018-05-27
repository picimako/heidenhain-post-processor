package picimako.heidenhain.util;

/**
 * Util class for checking expressions from certain aspects.
 *
 * @author Tamas Balog
 */
public final class Preconditions {

    private Preconditions() {
        //prevent instantiation
    }

    /**
     * Check whether the boolean expression is true, and throws {@link IllegalStateException} if it is not.
     *
     * @param expression a boolean expression
     * @param errorMessage the exception message to use if the check fails
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }
}
