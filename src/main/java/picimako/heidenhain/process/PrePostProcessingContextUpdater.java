package picimako.heidenhain.process;

import static picimako.heidenhain.lang.LanguageConstants.TOOLCALL;

import java.util.List;

/**
 * Designed to run check on the input file before the first round of postprocessing and set the {@link PostProcessorContext} accordingly.
 *
 * @author Tamas Balog
 */
final class PrePostProcessingContextUpdater {

    /**
     * Updates the argument {@code context} based on the contents of argument {@code file}.
     *
     * @param context the post processor context
     * @param file    the input file
     */
    void update(PostProcessorContext context, List<String> file) {
        setIsContainToolCall(context, file);
    }

    private void setIsContainToolCall(PostProcessorContext context, List<String> file) {
        boolean isContainToolCall = file.stream().anyMatch(row -> row.contains(TOOLCALL));
        context.setContainToolCall(isContainToolCall);
    }
}
