package Styles;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

public class Labels {
    public static Label titleLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setTextFill(Colors.titleLabelColor);
        label.setFont(Font.font("Impact",24));
        return label;
    }
    public static Label cardLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setTextFill(Colors.cardLabelColor);
        label.setFont(Font.font("Impact",24));
        return label;
    }

    public static Label checkOutLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(5));
        label.setTextFill(Colors.checkoutLabelColor);
        label.setFont(Font.font("Cooper",16));
        return label;
    }

    public static Label timeLabel(){
        Label label = new Label();
        label.setPadding(new Insets(10));
        label.setTextFill(Colors.timeLabelColor);
        label.setFont(Font.font("Cooper",18));
        return label;
    }

    public static Label notificationSymbol(String text, FontIcon fontIcon){
        Label label = new Label(text);
        label.setPadding(new Insets(10));
        label.setCursor(Cursor.HAND);
        fontIcon.setIconColor(Colors.notificationIconColor);
        fontIcon.setIconSize(32);
        label.setGraphic(fontIcon);
        label.setOnMouseEntered(e->{
            fontIcon.setIconSize(36);
        });
        label.setOnMouseExited(e->{
            fontIcon.setIconSize(32);
        });
        label.setFont(Font.font("Impact",18));
        return label;
    }
    public static Tooltip tooltip(String text){
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.seconds(0.2));
        return tooltip;
    }
}
