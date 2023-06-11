package Styles;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.ligaturesymbols.LigatureSymbols;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

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

    public static Label checkOutLabel(String title){
        Label label = new Label(title);
        label.setPadding(new Insets(10));
        label.setFont(Font.font("Impact",18));
        return label;
    }

    public static Label timeLabel(){
        Label label = new Label();
        label.setPadding(new Insets(10));
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Cooper",18));
        return label;
    }

    public static Label notificationLabel(String text){
        Label label = new Label(text);
        label.setPadding(new Insets(10));
        label.setTextFill(Color.RED);

        FontIcon fontIcon = new FontIcon(LigatureSymbols.BELL);
        fontIcon.setIconColor(Color.RED);
        fontIcon.setIconSize(32);
        label.setGraphic(fontIcon);
        label.setFont(Font.font("Impact",18));
        return label;

    }
}
