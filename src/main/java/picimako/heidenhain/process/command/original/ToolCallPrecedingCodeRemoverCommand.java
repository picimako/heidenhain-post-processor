package picimako.heidenhain.process.command.original;

import static picimako.heidenhain.lang.LanguageConstants.TOOLCALL;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * Checks whether there is a {@code TOOLCALL} statement in the input file and if there is, then all rows before that statement are removed in the final file.
 *
 * @author Tamas Balog
 */
public class ToolCallPrecedingCodeRemoverCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        if (context.isContainToolCall()) {
            if (context.getRow().startsWith(TOOLCALL)) {
                context.setPastToolCall(true);
                result = context.getRow();
            } else if (!context.isPastToolCall()) {
                result = EMPTY_STRING;
            }
        }
        return result;
    }
}
