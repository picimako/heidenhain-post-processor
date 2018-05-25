package picimako.heidenhain.process;

/**
 * Represents an executable command.
 *
 * @author Tamas Balog
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command and processes the string content from the argument context.
     *
     * @param context the context
     * @return the updated row value. {@code null} if the command could not process the row. Empty string if the command removes the input row.
     */
    String process(PostProcessorContext context);
}
