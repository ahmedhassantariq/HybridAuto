package Screens;

import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class OrderForm {

    public static Parent orderForm(){


        MFXTextField searchField = Fields.textField("Search",300,40);
        MFXButton searchButton = Buttons.FunctionButton_Border("Search",100,40);
        MFXButton newSale = Buttons.FunctionButton("New Order ",150,40);

        HBox fieldBox = new HBox(searchField,searchButton,newSale);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);



        HBox customerOrderBox = new HBox(CustomerForm.customerForm(), ItemForm.newOrderForm());
        customerOrderBox.setPadding(new Insets(10));
        customerOrderBox.setSpacing(10);


        VBox mainPane = new VBox(Labels.titleLabel("Sales"),fieldBox,customerOrderBox);
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);
        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#e8e8e8"),new CornerRadii(0,0,0,0,false),null)));

        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        });
        return mainPane;

    }
}
