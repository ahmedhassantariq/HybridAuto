package Styles;

import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

public class Buttons {
    private static Font buttonFont = Font.font("Cooper",18);

    public static MFXButton DashboardButton(String buttonLabel, FontIcon icon){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Colors.buttonColor,new CornerRadii(15),null)));
        button.setAlignment(Pos.CENTER_LEFT);
        FontIcon fontIcon = new FontIcon(icon.getIconCode());
        fontIcon.setIconColor(Colors.fontIconColor);
        fontIcon.setIconSize(32);
        button.setGraphic(fontIcon);
        button.setTextFill(Colors.buttonTextColor);
        button.setFont(buttonFont);
        button.setPrefWidth(200);
        button.setPrefHeight(Constants.formHeight/6);
        button.setOnMouseEntered(e->{
            button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            fontIcon.setIconColor(Colors.mouseFontIconColor);
            button.setTextFill(Colors.mouseButtonTextColor);
        });
        button.setOnMouseExited(e->{
            button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            fontIcon.setIconColor(Colors.fontIconColor);
            button.setTextFill(Colors.buttonTextColor);
        });
        return button;
    }

    public static MFXButton FunctionButton(String buttonLabel,double width,double height){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(7,7,7,7,false),null)));
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setAlignment(Pos.CENTER);
        button.setTextFill(Colors.buttonTextColor);
        button.setFont(Font.font("Cooper",12));
        return button;
    }
    public static MFXButton FunctionButton_Border(String buttonLabel,double width,double height){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7,7,7,7,false),null)));
        button.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(7,7,7,7,false), BorderStroke.THIN)));
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setAlignment(Pos.CENTER);
        button.setTextFill(Colors.darkButtonColor);
        button.setFont(Font.font("Cooper",12));
        return button;
    }
}
