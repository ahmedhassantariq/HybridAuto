package Styles;

import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;

public class Buttons {
    private static Font buttonFont = Font.font("Cooper",18);

    public static MFXButton DashboardButton(String buttonLabel, FontIcon icon){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
        button.setAlignment(Pos.CENTER_LEFT);
        FontIcon fontIcon = new FontIcon(icon.getIconCode());
        fontIcon.setIconColor(Color.WHITE);
        fontIcon.setIconSize(32);
        button.setGraphic(fontIcon);
        button.setTextFill(Color.WHITE);
        button.setFont(buttonFont);
        button.setPrefWidth(200);
        button.setPrefHeight(Constants.formHeight/6);
        button.setOnMouseEntered(e->{
            button.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            fontIcon.setIconColor(Color.web("#02557a"));
            button.setTextFill(Color.web("#02557a"));
        });
        button.setOnMouseExited(e->{
            button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            fontIcon.setIconColor(Color.WHITE);
            button.setTextFill(Color.WHITE);
        });
        return button;
    }

    public static MFXButton FunctionButton(String buttonLabel,double width,double height){
        MFXButton button = new MFXButton(buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(7,7,7,7,false),null)));
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        button.setAlignment(Pos.CENTER);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Cooper",12));

        return button;
    }
}
