package picimako.heidenhain.process.command.original;

import static picimako.heidenhain.lang.LanguageConstants.LM09;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * If the currently examined row is past the row that contains {@code " L M09"} than it returns an empty string, so that the row will be removed.
 *
 * @author Tamas Balog
 */
public final class RowPastLM09ToEmptyStringReplacerCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        if (context.isPastLM09()) {
            result = EMPTY_STRING;
        } else if (LM09.equals(context.getRow())) {
            context.setPastLM09(true);
            result = EMPTY_STRING;
        }
        return result;
    }
}
