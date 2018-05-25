package picimako.heidenhain.process;

import java.util.List;

/**
 * Executes a list of predefined commands.
 * <p>
 * If an underlying command return a non-null value, that means, that command could process the current row.
 * <p>
 * If no command could process the row, it returns the row itself.
 *
 * @author Tamas Balog
 */
public class ChainedCommand implements Command {

    private final List<Command> commands;

    public ChainedCommand(List<Command> commands) {
        this.commands = List.copyOf(commands);
    }

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        for (Command command : commands) {
            result = command.process(context);
            if (result != null) {
                break;
            }
        }
        return result == null ? context.getRow() : result;
    }
}
