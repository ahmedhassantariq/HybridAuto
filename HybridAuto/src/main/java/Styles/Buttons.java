package Styles;

import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

public class Buttons {
    private static Font dashboardButtonFont = Font.font("Cooper",18);
    private static Font functionButtonFont = Font.font("Cooper",12);

    private static Color buttonColor = Color.web("#02557a");
    private static Color fontIconColor = Color.WHITE;
    private static Color buttonTextColor = Color.WHITE;

    private static Color mouseFontIconColor = Color.web("#02557a");
    private static Color mouseButtonTextColor = Color.web("#02557a");
    private static Color darkButtonTextColor = Color.web("#02557a");



    public static MFXButton DashboardButton(String buttonLabel, FontIcon icon){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(buttonColor,new CornerRadii(15),null)));
        button.setAlignment(Pos.CENTER_LEFT);
        FontIcon fontIcon = new FontIcon(icon.getIconCode());
        fontIcon.setIconColor(fontIconColor);
        fontIcon.setIconSize(32);
        button.setGraphic(fontIcon);
        button.setTextFill(buttonTextColor);
        button.setFont(dashboardButtonFont);
        button.setPrefWidth(200);
        button.setPrefHeight(Constants.formHeight/6);
        button.setOnMouseEntered(e->{
            button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            fontIcon.setIconColor(mouseFontIconColor);
            button.setTextFill(mouseButtonTextColor);
        });
        button.setOnMouseExited(e->{
            button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            fontIcon.setIconColor(fontIconColor);
            button.setTextFill(buttonTextColor);
        });
        return button;
    }

    public static MFXButton FunctionButton(String buttonLabel,double width,double height){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(7,7,7,7,false),null)));
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setAlignment(Pos.CENTER);
        button.setTextFill(buttonTextColor);
        button.setFont(functionButtonFont);
        return button;
    }
    public static MFXButton FunctionButton_Border(String buttonLabel,double width,double height){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7,7,7,7,false),null)));
        button.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(7,7,7,7,false), BorderStroke.THIN)));
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setAlignment(Pos.CENTER);
        button.setTextFill(darkButtonTextColor);
        button.setFont(functionButtonFont);
        return button;
    }


    public static MFXButton iconButton(String buttonLabel,double width,double height, FontIcon icon){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(buttonColor,new CornerRadii(15),null)));
        button.setAlignment(Pos.CENTER);
        FontIcon fontIcon = new FontIcon(icon.getIconCode());
        fontIcon.setIconColor(fontIconColor);
        fontIcon.setIconSize(32);
        button.setGraphic(fontIcon);
        button.setTextFill(buttonTextColor);
        button.setFont(dashboardButtonFont);
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setOnMouseEntered(e->{
            button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            fontIcon.setIconColor(mouseFontIconColor);
            button.setTextFill(mouseButtonTextColor);
        });
        button.setOnMouseExited(e->{
            button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            fontIcon.setIconColor(fontIconColor);
            button.setTextFill(buttonTextColor);
        });
        return button;
    }
}
