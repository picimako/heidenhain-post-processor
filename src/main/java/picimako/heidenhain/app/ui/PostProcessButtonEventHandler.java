package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.HeidenhainFileReader.read;
import static picimako.heidenhain.util.StringUtils.EMPTY_STRING;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    private TextArea inBracesCommentText;
    private TextArea withoutBracesPrepareCommandsText;
    private TextArea m30PrecedingCodeText;
    private FileBeginningRadioButtonRegistry fileBeginningRadioButtonRegistry;
    private InBetweenM30PrecedingCodeAndM30RadioButtonRegistry inBetweenM30PrecedingCodeAndM30RadioButtonRegistry;

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

    public void setInBracesCommentText(TextArea inBracesCommentText) {
        this.inBracesCommentText = inBracesCommentText;
    }

    public void setWithoutBracesPrepareCommandsText(TextArea withoutBracesPrepareCommandsText) {
        this.withoutBracesPrepareCommandsText = withoutBracesPrepareCommandsText;
    }

    public void setM30PrecedingCodeText(TextArea m30PrecedingCodeText) {
        this.m30PrecedingCodeText = m30PrecedingCodeText;
    }

    public void setFileBeginningRadioButtonRegistry(FileBeginningRadioButtonRegistry fileBeginningRadioButtonRegistry) {
        this.fileBeginningRadioButtonRegistry = fileBeginningRadioButtonRegistry;
    }

    public void setInBetweenM30PrecedingCodeAndM30RadioButtonRegistry(InBetweenM30PrecedingCodeAndM30RadioButtonRegistry inBetweenM30PrecedingCodeAndM30RadioButtonRegistry) {
        this.inBetweenM30PrecedingCodeAndM30RadioButtonRegistry = inBetweenM30PrecedingCodeAndM30RadioButtonRegistry;
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
        context.setFileBeginningRadioButton(fileBeginningRadioButtonRegistry.getSelectedRadioButton().orElse(null));
        context.setInBracesCommentTextArea(inBracesCommentText);
        context.setWithoutBracesPrepareCommandsTextArea(withoutBracesPrepareCommandsText);
        context.setM30PrecedingCodeTextArea(m30PrecedingCodeText);
        context.setInBetweenM30PrecedingCodeAndM30RadioButton(inBetweenM30PrecedingCodeAndM30RadioButtonRegistry.getSelectedRadioButton().orElse(null));
        return context;
    }

    private String getMidResultFilePath() {
        return inputFileTextField.getText().replaceFirst("\\.[hH]", EMPTY_STRING) + "_mid.h";
    }

    private void doAfterSuccessfulPostProcessing() {
        postProcessingDoneLabel.setVisible(true);
    }
}
