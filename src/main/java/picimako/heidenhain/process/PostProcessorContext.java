package picimako.heidenhain.process;

import static picimako.heidenhain.lang.LanguageConstants.END;

import picimako.heidenhain.annotation.VisibleForTesting;

/**
 * Context for working with the post processor.
 *
 * @author Tamas Balog
 */
public final class PostProcessorContext {

    private boolean isPastToolCall;
    private boolean isPastLM09;
    private String row;
    private String inBracesCommentText;
    private String withoutBracesPrepareCommandsText;
    private String m30PrecedingCodeText;

    public PostProcessorContext() {
    }

    @VisibleForTesting
    //FIXME: this might not be a good design choice having this public. Might signal a bad package structure?
    public PostProcessorContext(String row) {
        this.row = row;
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

    public String getInBracesCommentText() {
        return inBracesCommentText;
    }

    public void setInBracesCommentText(String inBracesCommentText) {
        this.inBracesCommentText = inBracesCommentText;
    }

    public String getWithoutBracesPrepareCommandsText() {
        return withoutBracesPrepareCommandsText;
    }

    public void setWithoutBracesPrepareCommandsText(String withoutBracesPrepareCommandsText) {
        this.withoutBracesPrepareCommandsText = withoutBracesPrepareCommandsText;
    }

    public String getM30PrecedingCodeText() {
        return m30PrecedingCodeText;
    }

    public void setM30PrecedingCodeText(String m30PrecedingCodeText) {
        this.m30PrecedingCodeText = m30PrecedingCodeText;
    }
}
