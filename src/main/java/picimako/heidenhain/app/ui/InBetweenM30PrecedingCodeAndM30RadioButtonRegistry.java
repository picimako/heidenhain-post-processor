package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.ui.ToggleGroupSetter.set;

import java.util.Map;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Creates a {@link ToggleGroup} with 3 radiobuttons, labeled with code chunks from the settings file, used for injecting that code after {@code m30PrecedingCodeTextArea} but
 * before {@code M30}.
 *
 * @author Tamas Balog
 */
final class InBetweenM30PrecedingCodeAndM30RadioButtonRegistry extends AbstractRadioButtonRegistry {

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final RadioButton optionOne = new RadioButton();
    private final RadioButton optionTwo = new RadioButton();
    private final RadioButton optionThree = new RadioButton();

    InBetweenM30PrecedingCodeAndM30RadioButtonRegistry(Map<String, String> settings) {
        super(settings);
    }

    @Override
    public void setupToggleGroup() {
        initialize(optionOne, "M30_ELE_MAKRO_1");
        initialize(optionTwo, "M30_ELE_MAKRO_2");
        initialize(optionThree, "M30_ELE_MAKRO_3");
        set(toggleGroup).asToggleGroupOf(optionOne, optionTwo, optionThree);
        setFirstActiveRadioButtonSelected();
    }

    @Override
    public RadioButton[] getRadioButtons() {
        return getAsArray(optionOne, optionTwo, optionThree);
    }
}
