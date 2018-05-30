package picimako.heidenhain.process;

import java.util.LinkedList;
import java.util.List;

import picimako.heidenhain.process.command.resultdependent.NoOpCommand;
import picimako.heidenhain.process.command.resultdependent.M30PrecedingCodeAdderCommand;

/**
 * Static configuration for the second round of post-processing.
 * <p>
 * Stores the list of post-processing commands that depend on the result of a previous command, in their order of execution.
 *
 * @author Tamas Balog
 */
final class ResultDependentConfiguration {

    private static final List<Command> POST_PROCESSOR_COMMANDS;

    static {
        List<Command> commands = new LinkedList<>();
        commands.add(new M30PrecedingCodeAdderCommand());
        commands.add(new NoOpCommand());

        POST_PROCESSOR_COMMANDS = List.copyOf(commands);
    }

    private ResultDependentConfiguration() {
        //prevent instantiation
    }

    static List<Command> get() {
        return POST_PROCESSOR_COMMANDS;
    }
}
