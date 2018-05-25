package picimako.heidenhain.app.ui;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Helper class to be able to set a {@link ToggleGroup} as the toggle group of {@link RadioButton} objects easier and in a more fluent way.
 *
 * @author Tamas Balog
 */
final class ToggleGroupSetter {

    private final ToggleGroup toggleGroup;

    private ToggleGroupSetter(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    static ToggleGroupSetter set(ToggleGroup toggleGroup) {
        return new ToggleGroupSetter(toggleGroup);
    }

    /**
     * Set the preset toggle group to the argument radiobuttons.
     *
     * @param radioButtons to radiobuttons to set the toggle group to
     */
    void asToggleGroupOf(RadioButton... radioButtons) {
        for (RadioButton radioButton : radioButtons) {
            radioButton.setToggleGroup(toggleGroup);
        }
    }
}
