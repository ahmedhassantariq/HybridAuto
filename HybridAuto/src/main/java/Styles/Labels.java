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
    private static Font titleLabelFont = Font.font("Impact",24);
    private static Font carLabelFont = Font.font("Impact",24);
    private static Font checkOutLabelFont = Font.font("Cooper",16);
    private static Font timeLabelFont =  Font.font("Cooper",18);
    private static Font notificationSymbolFont = Font.font("Impact",18);

    private static Color titleLabelColor = Color.BLACK;
    private static Color cardLabelColor = Color.BLACK;
    private static Color checkoutLabelColor = Color.BLACK;
    private static Color timeLabelColor = Color.WHITE;
    private static Color notificationIconColor = Color.WHITE;

    public static Label titleLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setTextFill(titleLabelColor);
        label.setFont(titleLabelFont);
        return label;
    }
    public static Label cardLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setTextFill(cardLabelColor);
        label.setFont(carLabelFont);
        return label;
    }

    public static Label checkOutLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(5));
        label.setTextFill(checkoutLabelColor);
        label.setFont(checkOutLabelFont);
        return label;
    }

    public static Label timeLabel(){
        Label label = new Label();
        label.setPadding(new Insets(10));
        label.setTextFill(timeLabelColor);
        label.setFont(timeLabelFont);
        return label;
    }

    public static Label notificationSymbol(String text, FontIcon fontIcon){
        Label label = new Label(text);
        label.setPadding(new Insets(10));
        label.setCursor(Cursor.HAND);
        fontIcon.setIconColor(notificationIconColor);
        fontIcon.setIconSize(32);
        label.setGraphic(fontIcon);
        label.setOnMouseEntered(e->{
            fontIcon.setIconSize(36);
        });
        label.setOnMouseExited(e->{
            fontIcon.setIconSize(32);
        });
        label.setFont(notificationSymbolFont);
        return label;
    }
    public static Tooltip tooltip(String text){
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.seconds(0.2));
        return tooltip;
    }
}
