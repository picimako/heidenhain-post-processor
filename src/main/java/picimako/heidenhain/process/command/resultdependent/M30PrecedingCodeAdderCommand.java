package picimako.heidenhain.process.command.resultdependent;

import static picimako.heidenhain.lang.LanguageConstants.M30;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * If the current row is M30, it inserts the content of the M30PrecedingCodeTextArea, if it is not empty after trimming.
 *
 * @author Tamas Balog
 */
public class M30PrecedingCodeAdderCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        if (M30.equals(context.getRow())) {
            if (context.getM30PrecedingCodeText().trim().isEmpty()) {
                result = M30;
            } else {
                result = context.getM30PrecedingCodeText() + "\n" + M30;
            }
        }
        return result;
    }
}
