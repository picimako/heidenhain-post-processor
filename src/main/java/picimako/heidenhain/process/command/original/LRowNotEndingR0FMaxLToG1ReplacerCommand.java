package picimako.heidenhain.process.command.original;

import static picimako.heidenhain.lang.LanguageConstants.G1;
import static picimako.heidenhain.lang.LanguageConstants.L;
import static picimako.heidenhain.lang.LanguageConstants.R0FMAX;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * Replaces {@code L} strings to {@code G1} at the end of rows that start with {@code L} but not end with {@code R0FMAX}.
 *
 * @author Tamas Balog
 */
public final class LRowNotEndingR0FMaxLToG1ReplacerCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        String row = context.getRow();
        if (row.startsWith(L) && !row.endsWith(R0FMAX)) {
            result = row.replaceFirst(L, G1);
        }
        return result;
    }
}
