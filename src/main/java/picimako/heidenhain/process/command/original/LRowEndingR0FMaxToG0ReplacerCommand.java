package picimako.heidenhain.process.command.original;

import static picimako.heidenhain.lang.LanguageConstants.G0;
import static picimako.heidenhain.lang.LanguageConstants.L;
import static picimako.heidenhain.lang.LanguageConstants.R0FMAX;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * In rows that start with {@code L} and ends with {@code R0FMAX}
 * <ul>
 *     <li>removes the L at the beginning,</li>
 *     <li>replaces {@code R0FMAX} to {@code G0} at the end.</li>
 * </ul>
 *
 * @author Tamas Balog
 */
public final class LRowEndingR0FMaxToG0ReplacerCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        String row = context.getRow();
        if (row.startsWith(L) && row.endsWith(R0FMAX)) {
            result = row.replaceFirst(L, G0).replaceAll(R0FMAX, EMPTY_STRING);
        }
        return result;
    }
}
