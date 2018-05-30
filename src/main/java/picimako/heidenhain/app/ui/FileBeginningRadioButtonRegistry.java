package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.ui.ToggleGroupSetter.set;

import java.util.Map;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Creates a {@link ToggleGroup} with 3 radiobuttons, labeled with code chunks from the settings file, used for injecting that code right at the beginning of the file.
 * Even before {@code inBracesCommentTextArea} and {@code withoutBracesPrepareCommandsTextArea}.
 *
 * @author Tamas Balog
 */
final class FileBeginningRadioButtonRegistry extends AbstractRadioButtonRegistry {

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final RadioButton optionOne = new RadioButton();
    private final RadioButton optionTwo = new RadioButton();
    private final RadioButton optionThree = new RadioButton();

    FileBeginningRadioButtonRegistry(Map<String, String> settings) {
        super(settings);
    }

    @Override
    public void setupToggleGroup() {
        initialize(optionOne, "FAJL_ELEJE_MAKRO_1");
        initialize(optionTwo, "FAJL_ELEJE_MAKRO_2");
        initialize(optionThree, "FAJL_ELEJE_MAKRO_3");
        set(toggleGroup).asToggleGroupOf(optionOne, optionTwo, optionThree);
        setFirstActiveRadioButtonSelected();
    }

    @Override
    public RadioButton[] getRadioButtons() {
        return getAsArray(optionOne, optionTwo, optionThree);
    }
}
