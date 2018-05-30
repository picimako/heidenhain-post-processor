package picimako.heidenhain.process;

import java.util.List;
import java.util.Optional;

/**
 * This class is designed to be executed before the actual post-processing to add data right at the beginning of the file.
 * <p>
 * Currently the addition includes
 * <ul>
 * <li>the file beginning macro from the radio buttons</li>
 * <li>the in-braces comment from the text field</li>
 * <li>the without-braces prepare commands from the text field</li>
 * </ul>
 *
 * @author Tamas Balog
 */
public final class PostProcessedFileBeginningConfigurer {

    private final PostProcessorContext context;

    public PostProcessedFileBeginningConfigurer(PostProcessorContext context) {
        this.context = context;
    }

    public void configure(List<String> outputContent) {
        addFileBeginningTextFromRadioButton(outputContent);
        addInBracesComment(outputContent);
        addWithoutBracesPrepareCommands(outputContent);
    }

    private void addFileBeginningTextFromRadioButton(List<String> outputContent) {
        Optional.ofNullable(context.getFileBeginningRadioButton())
            .ifPresent(radioButton -> outputContent.add(radioButton.getText()));
    }

    private void addInBracesComment(List<String> outputContent) {
        String inBracesCommentText = context.getInBracesCommentTextArea().getText();
        if (!inBracesCommentText.isEmpty()) {
            outputContent.add("(" + inBracesCommentText + ")");
        }
    }

    private void addWithoutBracesPrepareCommands(List<String> outputContent) {
        String withoutBracesPrepareCommandsText = context.getWithoutBracesPrepareCommandsTextArea().getText();
        if (!withoutBracesPrepareCommandsText.isEmpty()) {
            outputContent.add(withoutBracesPrepareCommandsText);
        }
    }
}
