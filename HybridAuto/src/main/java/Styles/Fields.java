package Styles;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.geometry.Pos;

public class Fields {

    public static MFXTextField textField(String fieldLabel,double width,double height) {
        MFXTextField textField = new MFXTextField();
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setMinSize(width, height);
        textField.setFloatingText(fieldLabel);
        return textField;
    }
    public static MFXPasswordField passwordField(String fieldLabel,double width,double height) {
        MFXPasswordField passwordField = new MFXPasswordField();
        passwordField.setAlignment(Pos.CENTER_LEFT);
        passwordField.setMinSize(width, height);
        passwordField.setFloatingText(fieldLabel);
        return passwordField;
    }

}
