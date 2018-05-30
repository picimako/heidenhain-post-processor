package picimako.heidenhain.app.ui;

import java.util.Optional;

import javafx.scene.control.RadioButton;

/**
 * Defines actions for toggle group handling.
 * <p>
 * TODO: might be separated more
 *
 * @author Tamas Balog
 */
public interface ToggleGroupAssemblerRadioButtonRegistry {

    /**
     * Initializes the {@link RadioButton}s with text, sets them enabled/disabled, sets one of them as selected, and adds them to a {@link javafx.scene.control.ToggleGroup}.
     */
    void setupToggleGroup();

    /**
     * Returns the {@link RadioButton}s that are configured in the assembler.
     *
     * @return the configured radiobuttons
     */
    RadioButton[] getRadioButtons();

    /**
     * Returns the {@link RadioButton} from the current {@link javafx.scene.control.ToggleGroup} that is selected.
     * <p>
     * There might be no radio button selected if none of the radio buttons in the toggle group has value.
     *
     * @return the selected radio button
     */
    Optional<RadioButton> getSelectedRadioButton();
}
