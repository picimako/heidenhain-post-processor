package picimako.heidenhain.app.ui;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Opens up a file chooser dialog for files with .h extension, when clicking on the file browser button.
 *
 * @author Tamas Balog
 */
public class FileBrowserButtonEventHandler implements EventHandler<ActionEvent> {

    //TODO: what if a file is selected. But then the button is clicked and the dialog is Canceled. The file is still saved?

    private final Stage stage;
    private final Label postProcessingDoneLabel;
    private final Button postProcessButton;
    private TextField inputFileTextField;
    private TextField outputFileTextField;

    public FileBrowserButtonEventHandler(Stage stage, Label postProcessingDoneLabel, Button postProcessButton) {
        this.stage = stage;
        this.postProcessingDoneLabel = postProcessingDoneLabel;
        this.postProcessButton = postProcessButton;
    }

    @Override
    public void handle(ActionEvent event) {
        postProcessingDoneLabel.setVisible(false);
        Optional.ofNullable(fileChooser().showOpenDialog(stage))
            .ifPresent(file -> {
                inputFileTextField.setText(file.getAbsolutePath());
                outputFileTextField.setText(outputFilePath(file));
                postProcessButton.setDisable(false);
            });
    }

    public void setInputFileTextField(TextField inputFileTextField) {
        this.inputFileTextField = inputFileTextField;
    }

    public void setOutputFileTextField(TextField outputFileTextField) {
        this.outputFileTextField = outputFileTextField;
    }

    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("H fájlok", "*.h"));
        return fileChooser;
    }

    private String outputFilePath(File file) {
        return file.getParent() + "\\" + file.getName().replaceFirst("\\.h", "") + "_final.prg";
    }
}
