package Screens;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import io.github.palexdev.materialfx.skins.MFXProgressSpinnerSkin;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InventoryForm {

    public static Parent inventoryForm(){

        Label titleLabel = new Label("Inventory");

        MFXTextField searchField = new MFXTextField();
        searchField.setAlignment(Pos.CENTER);
        searchField.setMinSize(300,50);
        searchField.setFloatingText("Search");


        MFXButton searchButton = new MFXButton("Search");
        searchButton.setBackground(new Background(new BackgroundFill(Color.web("#012231"),new CornerRadii(15,15,15,15,false),null)));
        searchButton.setMinSize(100,50);
        searchButton.setPrefSize(100,50);
        searchButton.setAlignment(Pos.CENTER);
        searchButton.setTextFill(Color.WHITE);
        Font buttonFont = Font.font("Cooper",18);
        searchButton.setFont(buttonFont);

        HBox fieldBox = new HBox(searchField,searchButton);
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);


        VBox mainPane = new VBox(titleLabel,fieldBox);
        mainPane.setPadding(new Insets(10));
        mainPane.setSpacing(10);

        mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
        return mainPane;
    }
}
