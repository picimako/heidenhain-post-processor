package picimako.heidenhain.process.command.resultdependent;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * A command that does nothing but returns the current row string. This is designed as a fallback in the command chain, so that if for some reason none of the commands could
 * process the current row, then at the end the current row itself will be returned and added to the output file.
 *
 * @author Tamas Balog
 */
public class NoOpCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        return context.getRow();
    }
}
