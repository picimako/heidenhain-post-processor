package picimako.heidenhain.app.ui;

import static picimako.heidenhain.app.ui.ToggleGroupSetter.set;

import java.util.List;
import java.util.Map;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Creates a {@link ToggleGroup} with 3 radiobuttons, labeled with code chunks from the settings file, used for injecting that code right at the beginning of the file.
 * Even before {@code inBracesCommentTextArea} and {@code withoutBracesPrepareCommandsTextArea}.
 *
 * @author Tamas Balog
 */
final class FileBeginningToggleGroupAssembler {

    private final ToggleGroup fileBeginningToggleGroup = new ToggleGroup();
    private final Map<String, String> settings;
    private RadioButton fileBeginningToggleOptionOne;
    private RadioButton fileBeginningToggleOptionTwo;
    private RadioButton fileBeginningToggleOptionThree;

    FileBeginningToggleGroupAssembler(Map<String, String> settings) {
        this.settings = settings;
    }

    void setupToggleGroup() {
        fileBeginningToggleOptionOne = new RadioButton(settings.get("FAJL_ELEJE_MAKRO_1"));
        fileBeginningToggleOptionTwo = new RadioButton(settings.get("FAJL_ELEJE_MAKRO_2"));
        fileBeginningToggleOptionThree = new RadioButton(settings.get("FAJL_ELEJE_MAKRO_3"));
        set(fileBeginningToggleGroup).asToggleGroupOf(fileBeginningToggleOptionOne, fileBeginningToggleOptionTwo, fileBeginningToggleOptionThree);
        fileBeginningToggleOptionOne.setSelected(true);
    }

    RadioButton[] getRadioButtons() {
        return List.of(fileBeginningToggleOptionOne, fileBeginningToggleOptionTwo, fileBeginningToggleOptionThree).toArray(new RadioButton[0]);
    }

//    String getValueOf(String key) {
//        return Optional.ofNullable(settings.get(key)).filter(value -> !value.isEmpty()).orElseGet(() -> "");
//    }
}
