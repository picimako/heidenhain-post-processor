package picimako.heidenhain.app;

import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

import picimako.heidenhain.app.ui.PrimaryStageAssembler;

/**
 * Assembles the main window for the application, runs the application and executes the post-processing.
 *
 * @author Tamas Balog
 */
public final class ApplicationExecutor extends Application {

    private static final String DEFAULT_SETTINGS_FILE_PATH = "C:\\heidenhain\\settings.properties";
    private static String settingsFilePath;

    public static void main(String[] args) {
        settingsFilePath = args.length == 0 ? DEFAULT_SETTINGS_FILE_PATH : args[0];
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Map<String, String> settings = new SettingsFileReader().read(settingsFilePath);
        new PrimaryStageAssembler(primaryStage, settings).assemble();
    }
}
