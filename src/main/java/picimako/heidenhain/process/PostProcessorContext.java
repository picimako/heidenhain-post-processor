package picimako.heidenhain.process;

import static picimako.heidenhain.lang.LanguageConstants.END;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import picimako.heidenhain.annotation.VisibleForTesting;

/**
 * Context for working with the post processor.
 *
 * @author Tamas Balog
 */
public final class PostProcessorContext {

    private boolean isContainToolCall;
    private boolean isPastToolCall;
    private boolean isPastLM09;
    private String row;
    private TextArea inBracesCommentTextArea;
    private TextArea withoutBracesPrepareCommandsTextArea;
    private TextArea m30PrecedingCodeTextArea;
    private RadioButton fileBeginningRadioButton;
    private RadioButton inBetweenM30PrecedingCodeAndM30RadioButton;

    public PostProcessorContext() {
    }

    @VisibleForTesting
    //FIXME: this might not be a good design choice having this public. Might signal a bad package structure?
    public PostProcessorContext(String row) {
        this.row = row;
    }


    public boolean isContainToolCall() {
        return isContainToolCall;
    }

    public void setContainToolCall(boolean containToolCall) {
        isContainToolCall = containToolCall;
    }

    public boolean isPastToolCall() {
        return isPastToolCall;
    }

    public void setPastToolCall(boolean pastToolCall) {
        isPastToolCall = pastToolCall;
    }

    public boolean isPastLM09() {
        return isPastLM09;
    }

    public void setPastLM09(boolean pastLM09) {
        isPastLM09 = pastLM09;
    }

    public boolean isLastRow() {
        return row.startsWith(END);
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public TextArea getInBracesCommentTextArea() {
        return inBracesCommentTextArea;
    }

    public void setInBracesCommentTextArea(TextArea inBracesCommentTextArea) {
        this.inBracesCommentTextArea = inBracesCommentTextArea;
    }

    public TextArea getWithoutBracesPrepareCommandsTextArea() {
        return withoutBracesPrepareCommandsTextArea;
    }

    public void setWithoutBracesPrepareCommandsTextArea(TextArea withoutBracesPrepareCommandsTextArea) {
        this.withoutBracesPrepareCommandsTextArea = withoutBracesPrepareCommandsTextArea;
    }

    public TextArea getM30PrecedingCodeTextArea() {
        return m30PrecedingCodeTextArea;
    }

    public void setM30PrecedingCodeTextArea(TextArea m30PrecedingCodeTextArea) {
        this.m30PrecedingCodeTextArea = m30PrecedingCodeTextArea;
    }

    public RadioButton getFileBeginningRadioButton() {
        return fileBeginningRadioButton;
    }

    /**
     * .
     * @param fileBeginningRadioButton can be null if it is in a toggle group in which no radio button has value, thus none of them is selected
     */
    public void setFileBeginningRadioButton(RadioButton fileBeginningRadioButton) {
        this.fileBeginningRadioButton = fileBeginningRadioButton;
    }

    public RadioButton getInBetweenM30PrecedingCodeAndM30RadioButton() {
        return inBetweenM30PrecedingCodeAndM30RadioButton;
    }

    /**
     * .
     * @param inBetweenM30PrecedingCodeAndM30RadioButton can be null if it is in a toggle group in which no radio button has value, thus none of them is selected
     */
    public void setInBetweenM30PrecedingCodeAndM30RadioButton(RadioButton inBetweenM30PrecedingCodeAndM30RadioButton) {
        this.inBetweenM30PrecedingCodeAndM30RadioButton = inBetweenM30PrecedingCodeAndM30RadioButton;
    }
}
