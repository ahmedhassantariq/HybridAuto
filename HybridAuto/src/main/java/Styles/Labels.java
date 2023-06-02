package Styles;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Labels {
    public static Label titleLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Impact",24));
        return label;
    }
    public static Label cardLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Impact",24));
        return label;
    }
}
