package Styles;

import Utils.Formatter;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;


public class Fields {

    private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");
    // Because fuck regex, stupid shit
    private static final String[] upperChar = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    private static final String[] lowerChar = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ");
    private static final String[] digits = "0 1 2 3 4 5 6 7 8 9".split(" ");
    private static final String[] specialCharacters = "! @ # & ( ) – [ { } ]: ; ' , ? / * ~ $ ^ + = < > -".split(" ");
    private static Label validationLabel = new Label("Error");

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
