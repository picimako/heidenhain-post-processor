package picimako.heidenhain.process.command.resultdependent;

import static picimako.heidenhain.lang.LanguageConstants.M30;

import java.util.Optional;

import picimako.heidenhain.process.Command;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * If the current row is M30, it inserts the content of
 * <ul>
 * <li>the text of M30PrecedingCodeTextArea, if it is not empty after trimming.</li>
 * <li>the text of the selected radio button from InBetweenM30PrecedingCodeAndM30RadioButtonRegistry, if it is not empty.</li>
 * </ul>
 *
 * @author Tamas Balog
 */
public class M30PrecedingCodeAdderCommand implements Command {

    @Override
    public String process(PostProcessorContext context) {
        String result = null;
        if (M30.equals(context.getRow())) {
            if (!context.getM30PrecedingCodeTextArea().getText().trim().isEmpty()) {
                result = context.getM30PrecedingCodeTextArea().getText();
            }
            if (!context.getInBetweenM30PrecedingCodeAndM30RadioButton().getText().isEmpty()) {
                result += "\n" + context.getInBetweenM30PrecedingCodeAndM30RadioButton().getText();
            }
        }
        return Optional.ofNullable(result).map(res -> res + "\n" + M30).orElse(result);
    }
}
