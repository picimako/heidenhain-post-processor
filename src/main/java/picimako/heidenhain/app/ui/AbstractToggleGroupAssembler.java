package picimako.heidenhain.app.ui;

import java.util.Map;

import javafx.scene.control.RadioButton;

/**
 * Contains common logic for {@link javafx.scene.control.ToggleGroup} creation.
 *
 * @author Tamas Balog
 */
public abstract class AbstractToggleGroupAssembler {
    private final Map<String, String> settings;

    protected AbstractToggleGroupAssembler(Map<String, String> settings) {
        this.settings = settings;
    }

    /**
     * Sets the text of the radiobutton base on the argument rule if the value of the rule is not empty, otherwise makes the radiobutton inactive.
     * <p>
     * Empty rule value means it has no value.
     *
     * @param radioButton the radiobutton to set the properties of
     * @param rule        the rule to set the radiobutton based on from the settings file
     */
    protected void initialize(RadioButton radioButton, String rule) {
        String command = settings.get(rule);
        if (command.isEmpty()) {
            radioButton.setDisable(true);
        } else {
            radioButton.setText(command);
        }
    }

    RadioButton[] getAsArray(RadioButton... radioButtons) {
        return radioButtons;
    }

    /**
     * Initializes the {@link RadioButton}s with text, sets them enabled/disabled, sets one of them as selected, and adds them to a {@link javafx.scene.control.ToggleGroup}.
     */
    abstract void setupToggleGroup();

    /**
     * Returns the {@link RadioButton}s that are configured in the assembler.
     *
     * @return the configured radiobuttons
     */
    abstract RadioButton[] getRadioButtons();
}
