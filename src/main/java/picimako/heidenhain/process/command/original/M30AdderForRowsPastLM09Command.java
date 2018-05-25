package picimako.heidenhain.process.command.original;

import static picimako.heidenhain.lang.LanguageConstants.M30;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * If the currently examined row if past the row containing {@code " L M09"} and it is the last row in the file, then replace it with {@code M30}.
 *
 * @author Tamas Balog
 */
public final class M30AdderForRowsPastLM09Command implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        if (context.isPastLM09() && context.isLastRow()) {
            result = M30;
        }
        return result;
    }
}
