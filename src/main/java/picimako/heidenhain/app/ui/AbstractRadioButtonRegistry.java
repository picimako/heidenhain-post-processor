package picimako.heidenhain.app.ui;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

/**
 * Contains common logic for {@link javafx.scene.control.ToggleGroup} creation.
 *
 * @author Tamas Balog
 */
abstract class AbstractRadioButtonRegistry implements ToggleGroupAssemblerRadioButtonRegistry {
    private final Map<String, String> settings;

    protected AbstractRadioButtonRegistry(Map<String, String> settings) {
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

    /**
     * Returns the argument radio buttons, converting them from varargs to array type.
     * @param radioButtons the radio buttons
     * @return the argument radio buttons as array
     */
    protected RadioButton[] getAsArray(RadioButton... radioButtons) {
        return radioButtons;
    }

    /**
     * Sets the first active (not disabled) radio button in the toggle group as selected.
     * <p>
     * If no active radio button is found, then none of them in the same toggle group is selected.
     */
    protected void setFirstActiveRadioButtonSelected() {
        Arrays.stream(getRadioButtons())
            .filter(radioButton -> !radioButton.isDisabled())
            .findFirst()
            .ifPresent(rb -> rb.setSelected(true));
    }

    @Override
    public Optional<RadioButton> getSelectedRadioButton() {
        return Arrays.stream(getRadioButtons())
            .filter(ToggleButton::isSelected)
            .findFirst();
    }
}
