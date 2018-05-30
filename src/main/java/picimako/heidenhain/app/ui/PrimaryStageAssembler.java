package picimako.heidenhain.app.ui;

import static java.util.Objects.requireNonNull;
import static picimako.heidenhain.app.ui.NodeAdder.add;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Assembles the primary stage for the UI.
 *
 * @author Tamas Balog
 */
public final class PrimaryStageAssembler {
    private static final int DEFAULT_BOX_SPACING = 10;
    private static final int PRIMARY_SCENE_WIDTH = 1200;
    private static final int PRIMARY_SCENE_HEIGHT = 900;
    private final Stage primaryStage;

    //File browsing components
    private FileBrowsingComponentsAssembler fileBrowsingComponentsAssembler;

    //Execute post-processing components
    private final Button postProcessButton = new Button("Csináld!");
    private PostProcessButtonEventHandler postProcessButtonEventHandler;
    private final Label postProcessingDoneLabel = new Label("Postprocesszált fájl készítése kész!");

    //Extra configuration fields. Empty lines are allowed, it doesn't affect the program execution.
    private final Label inBracesCommentTextAreaLabel = new Label("Kommentek a fájl elejére, zárójelek közé:");
    private final TextArea inBracesCommentTextArea = createTextAreaWithHeight(200); //Goes right at the beginning of the file, within braces.
    private final Label withoutBracesPrepareCommandsTextAreaLabel = new Label("Előkészítő parancsok a fájl elejére, zárójelezés nélkül:");
    private final TextArea withoutBracesPrepareCommandsTextArea = createTextAreaWithHeight(200); //Doesn't go within braces. Goes right after inBracesCommentTextArea.
    private final Label m30PrecedingCodeTextAreaLabel = new Label("Kód az M30 parancs elé:");
    private final TextArea m30PrecedingCodeTextArea = createTextAreaWithHeight(200); //Goes right before M30.

    //TODO: ezt a textarea-s injectálás előtt kell lefuttatni
    private final FileBeginningToggleGroupAssembler fileBeginningToggleGroupAssembler;
    //TODO: ezt meg kell nézni, hogy a mid vagy a final fájl készítésénél lenne érdemes megcsinálni
    private final InBetweenM30PrecedingCodeAndM30ToggleGroupAssembler inBetweenM30PrecedingCodeAndM30ToggleGroupAssembler;

    public PrimaryStageAssembler(Stage primaryStage, Map<String, String> settings) {
        this.primaryStage = requireNonNull(primaryStage);
        fileBeginningToggleGroupAssembler = new FileBeginningToggleGroupAssembler(settings);
        inBetweenM30PrecedingCodeAndM30ToggleGroupAssembler = new InBetweenM30PrecedingCodeAndM30ToggleGroupAssembler(settings);
    }

    /**
     * Assembles the primary stage.
     */
    public void assemble() {
        primaryStage.setTitle("Heidenhain postprocessor");
        postProcessButton.setDisable(true);

        fileBrowsingComponentsAssembler = new FileBrowsingComponentsAssembler(primaryStage, postProcessingDoneLabel, postProcessButton);

        Scene scene = new Scene(setUpLayout(), PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane setUpLayout() {
        BorderPane layout = new BorderPane();
        layout.setCenter(centerElements());
        layout.setLeft(leftElements());
        layout.setRight(rightElements());
        layout.setBottom(bottomElements());
        return layout;
    }

    private VBox centerElements() {
        VBox vBox = new VBox();
        vBox.setPadding(offsets());
        vBox.setSpacing(DEFAULT_BOX_SPACING);

        postProcessingDoneLabel.setVisible(false);
        add(fileBrowsingComponentsAssembler.getInputFileTextField(),
            fileBrowsingComponentsAssembler.getOutputFileTextField(),
            postProcessingDoneLabel,
            inBracesCommentTextAreaLabel,
            inBracesCommentTextArea,
            withoutBracesPrepareCommandsTextAreaLabel,
            withoutBracesPrepareCommandsTextArea,
            m30PrecedingCodeTextAreaLabel,
            m30PrecedingCodeTextArea
        ).to(vBox);
        return vBox;
    }

    private VBox leftElements() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(17, 12, 15, 12));
        vBox.setSpacing(22);

        add(fileBrowsingComponentsAssembler.getInputFileLabel(),
            fileBrowsingComponentsAssembler.getOutputFileLabel()
        ).to(vBox);
        fileBeginningRadioButtonRegistry.setupToggleGroup();
        add(fileBeginningRadioButtonRegistry.getRadioButtons()).to(vBox);
        inBetweenM30PrecedingCodeAndM30RadioButtonRegistry.setupToggleGroup();
        add(inBetweenM30PrecedingCodeAndM30RadioButtonRegistry.getRadioButtons()).to(vBox);
        return vBox;
    }

    private VBox rightElements() {
        VBox vBox = new VBox();
        vBox.setPadding(offsets());
        vBox.setSpacing(DEFAULT_BOX_SPACING);

        fileBrowsingComponentsAssembler.setupEventHandler();
        add(fileBrowsingComponentsAssembler.getFileBrowserButton()).to(vBox);
        return vBox;
    }

    private VBox bottomElements() {
        VBox vBox = new VBox();
        vBox.setPadding(offsets());
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #336699;");

        postProcessButtonEventHandler = new PostProcessButtonEventHandler(postProcessingDoneLabel,
            fileBrowsingComponentsAssembler.getInputFileTextField(),
            fileBrowsingComponentsAssembler.getOutputFileTextField());
        postProcessButtonEventHandler.setInBracesCommentText(inBracesCommentTextArea);
        postProcessButtonEventHandler.setWithoutBracesPrepareCommandsText(withoutBracesPrepareCommandsTextArea);
        postProcessButtonEventHandler.setM30PrecedingCodeText(m30PrecedingCodeTextArea);
        postProcessButtonEventHandler.setFileBeginningRadioButtonRegistry(fileBeginningRadioButtonRegistry);
        postProcessButtonEventHandler.setInBetweenM30PrecedingCodeAndM30RadioButtonRegistry(inBetweenM30PrecedingCodeAndM30RadioButtonRegistry);

        postProcessButton.setOnAction(postProcessButtonEventHandler);
        postProcessButton.setMinWidth(100);
        add(postProcessButton).to(vBox);
        return vBox;
    }

    private Insets offsets() {
        return new Insets(15, 12, 15, 12);
    }

    private TextArea createTextAreaWithHeight(int height) {
        TextArea textArea = new TextArea();
        textArea.setMinHeight(height);
        return textArea;
    }
}
