package picimako.heidenhain.process;

import java.util.LinkedList;
import java.util.List;

import picimako.heidenhain.process.command.original.LRowEndingR0FMaxToG0ReplacerCommand;
import picimako.heidenhain.process.command.original.LRowNotEndingR0FMaxLToG1ReplacerCommand;
import picimako.heidenhain.process.command.original.M30AdderForRowsPastLM09Command;
import picimako.heidenhain.process.command.original.RowPastLM09ToEmptyStringReplacerCommand;
import picimako.heidenhain.process.command.original.ToolCallPrecedingCodeRemoverCommand;

/**
 * Static configuration for post-processing. Stores the list of post-processing commands in their order of execution.
 *
 * @author Tamas Balog
 */
final class PostProcessorConfiguration {

    private static final List<Command> POST_PROCESSOR_COMMANDS;

    static {
        List<Command> commands = new LinkedList<>();
        commands.add(new ToolCallPrecedingCodeRemoverCommand());
        //TODO: M30AdderForRowsPastLM09Command might need to be merged with RowPastLM09ToEmptyStringReplacerCommand
        commands.add(new M30AdderForRowsPastLM09Command());
        commands.add(new RowPastLM09ToEmptyStringReplacerCommand());
        commands.add(new LRowEndingR0FMaxToG0ReplacerCommand());
        commands.add(new LRowNotEndingR0FMaxLToG1ReplacerCommand());

        POST_PROCESSOR_COMMANDS = List.copyOf(commands);
    }

    private PostProcessorConfiguration() {
        //prevent instantiation
    }

    static List<Command> get() {
        return POST_PROCESSOR_COMMANDS;
    }
}
