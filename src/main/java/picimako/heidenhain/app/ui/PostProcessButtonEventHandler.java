package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.HeidenhainFileReader.read;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import picimako.heidenhain.process.PostProcessedFileBeginningConfigurer;
import picimako.heidenhain.process.PostProcessor;
import picimako.heidenhain.process.PostProcessorContext;

/**
 * Invokes post-processing when clicking on the post process button.
 *
 * @author Tamas Balog
 */
public class PostProcessButtonEventHandler implements EventHandler<ActionEvent> {

    private final Label postProcessingDoneLabel;
    private final TextField inputFileTextField;
    private final TextField outputFileTextField;
    private String inBracesCommentText;
    private String withoutBracesPrepareCommandsText;
    private String m30PrecedingCodeText;

    //TODO: megnézni, hogy hogy tudnám úgy a PostProcessContext-et átlökni ide, hogy az even handling-nél aktuális adatokat kapjak,
    //TODO: és ne kell JavaFx-es component-eket mock-olni
    public PostProcessButtonEventHandler(Label postProcessingDoneLabel, TextField inputFileTextField, TextField outputFileTextField) {
        this.postProcessingDoneLabel = postProcessingDoneLabel;
        this.inputFileTextField = inputFileTextField;
        this.outputFileTextField = outputFileTextField;
    }

    @Override
    public void handle(ActionEvent event) {
        executePostProcessing();
        doAfterSuccessfulPostProcessing();
    }

    public void setInBracesCommentText(String inBracesCommentText) {
        this.inBracesCommentText = inBracesCommentText;
    }

    public void setWithoutBracesPrepareCommandsText(String withoutBracesPrepareCommandsText) {
        this.withoutBracesPrepareCommandsText = withoutBracesPrepareCommandsText;
    }

    public void setM30PrecedingCodeText(String m30PrecedingCodeText) {
        this.m30PrecedingCodeText = m30PrecedingCodeText;
    }

    private void executePostProcessing() {
        //TODO: handle exceptions. Log to a file? Show them in a message box?
        //TODO: Before invocation, validate whether the input and output file paths exist.
        PostProcessorContext context = setupPostProcessorContext();
        PostProcessor postProcessor = new PostProcessor(new PostProcessedFileBeginningConfigurer(context), context);
        postProcessor.execute(read(inputFileTextField.getText()), getMidResultFilePath(), outputFileTextField.getText());
    }

    private PostProcessorContext setupPostProcessorContext() {
        PostProcessorContext context = new PostProcessorContext();
        context.setInBracesCommentText(inBracesCommentText);
        context.setWithoutBracesPrepareCommandsText(withoutBracesPrepareCommandsText);
        context.setM30PrecedingCodeText(m30PrecedingCodeText);
        return context;
    }

    private String getMidResultFilePath() {
        return inputFileTextField.getText().replaceFirst("\\.[hH]", EMPTY_STRING) + "_mid.h";
    }

    private void doAfterSuccessfulPostProcessing() {
        postProcessingDoneLabel.setVisible(true);
    }
}
