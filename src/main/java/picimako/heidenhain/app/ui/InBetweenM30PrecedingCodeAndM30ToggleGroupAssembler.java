package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.ui.ToggleGroupSetter.set;

import java.util.List;
import java.util.Map;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Creates a {@link ToggleGroup} with 3 radiobuttons, labeled with code chunks from the settings file, used for injecting that code after {@code m30PrecedingCodeTextArea} but
 * before {@code M30}.
 *
 * @author Tamas Balog
 */
final class InBetweenM30PrecedingCodeAndM30ToggleGroupAssembler {

    private final ToggleGroup fileBeginningToggleGroup = new ToggleGroup();
    private final Map<String, String> settings;
    private RadioButton fileBeginningToggleOptionOne;
    private RadioButton fileBeginningToggleOptionTwo;
    private RadioButton fileBeginningToggleOptionThree;

    InBetweenM30PrecedingCodeAndM30ToggleGroupAssembler(Map<String, String> settings) {
        this.settings = settings;
    }

    void setupToggleGroup() {
        fileBeginningToggleOptionOne = new RadioButton(settings.get("M30_ELE_MAKRO_1"));
        fileBeginningToggleOptionTwo = new RadioButton(settings.get("M30_ELE_MAKRO_2"));
        fileBeginningToggleOptionThree = new RadioButton(settings.get("M30_ELE_MAKRO_3"));
        set(fileBeginningToggleGroup).asToggleGroupOf(fileBeginningToggleOptionOne, fileBeginningToggleOptionTwo, fileBeginningToggleOptionThree);
        fileBeginningToggleOptionOne.setSelected(true);
    }

    RadioButton[] getRadioButtons() {
        return List.of(fileBeginningToggleOptionOne, fileBeginningToggleOptionTwo, fileBeginningToggleOptionThree).toArray(new RadioButton[0]);
    }
}
