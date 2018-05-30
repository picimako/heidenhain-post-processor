package picimako.heidenhain.app.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Sets up components and an event handler for file browsing.
 *
 * @author Tamas Balog
 */
final class FileBrowsingComponentsAssembler {

    private final Label inputFileLabel = new Label("Bemeneti fájl:");
    private final Label outputFileLabel = new Label("Átalakított fájl:");
    private final TextField inputFileTextField = new TextField();
    private final TextField outputFileTextField = new TextField();
    private final Button fileBrowserButton = new Button("Tallózás...");
    private final Stage primaryStage;
    private final Label postProcessingDoneLabel;
    private final Button postProcessButton;
    private FileBrowserButtonEventHandler fileBrowserButtonEventHandler;

    FileBrowsingComponentsAssembler(Stage primaryStage, Label postProcessingDoneLabel, Button postProcessButton) {
        this.primaryStage = primaryStage;
        this.postProcessingDoneLabel = postProcessingDoneLabel;
        this.postProcessButton = postProcessButton;
    }

    /**
     * Initializes the event handler with the necessary UI components.
     */
    void setupEventHandler() {
        fileBrowserButtonEventHandler = new FileBrowserButtonEventHandler(primaryStage, postProcessingDoneLabel, postProcessButton);
        fileBrowserButtonEventHandler.setInputFileTextField(inputFileTextField);
        fileBrowserButtonEventHandler.setOutputFileTextField(outputFileTextField);
        fileBrowserButton.setOnAction(fileBrowserButtonEventHandler);
    }

    Label getInputFileLabel() {
        return inputFileLabel;
    }

    Label getOutputFileLabel() {
        return outputFileLabel;
    }

    TextField getInputFileTextField() {
        return inputFileTextField;
    }

    TextField getOutputFileTextField() {
        return outputFileTextField;
    }

    Button getFileBrowserButton() {
        return fileBrowserButton;
    }
}
