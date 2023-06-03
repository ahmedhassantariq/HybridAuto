package Functionality.Forms.Controllers;

import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class InputControlWrapper {

    private final Node input;

    public InputControlWrapper(MFXFilterComboBox cbx) {
        this.input = cbx;
    }

    public InputControlWrapper(MFXTextField mtf) {
        this.input = mtf;
    }

    public <T> T getEnteredInput() {
        if(input instanceof MFXFilterComboBox<?> c)
            return (T) c.getSelectedItem();
        if(input instanceof TextField t)
            return (T) t.getText();

        return null;
    }
    public String getStringInput() {
        return getEnteredInput();
    }

    public <T> T getInputControl() {
        return (T) input;
    }
}
