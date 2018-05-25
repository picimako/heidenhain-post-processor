package picimako.heidenhain.process;

import java.util.List;

/**
 * This class is designed to be executed before the actual post-processing to add data right at the beginning of the file.
 *
 * Currently the addition includes the in-braces comment and the without-braces prepare commands.
 *
 * @author Tamas Balog
 */
public final class PostProcessedFileBeginningConfigurer {

    private final PostProcessorContext context;

    public PostProcessedFileBeginningConfigurer(PostProcessorContext context) {
        this.context = context;
    }

    public void configure(List<String> outputContent) {
        addInBracesComment(outputContent);
        addWithoutBracesPrepareCommands(outputContent);
    }

    private void addInBracesComment(List<String> outputContent) {
        String inBracesCommentText = context.getInBracesCommentText();
        if (!inBracesCommentText.isEmpty()) {
            outputContent.add("(" + inBracesCommentText + ")");
        }
    }

    private void addWithoutBracesPrepareCommands(List<String> outputContent) {
        String withoutBracesPrepareCommandsText = context.getWithoutBracesPrepareCommandsText();
        if (!withoutBracesPrepareCommandsText.isEmpty()) {
            outputContent.add(withoutBracesPrepareCommandsText);
        }
    }
}
