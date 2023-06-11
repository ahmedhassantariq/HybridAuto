package Screens;

import Functionality.Forms.OrdersController;
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

import java.sql.SQLException;

public class OrderForm {
    public static Parent orderForm() throws SQLException {


        MFXButton newOrderButton = Buttons.FunctionButton("New Order ",150,40);
        MFXButton previousOrderButton = Buttons.FunctionButton("Previous Order ",150,40);


        newOrderButton.setOnAction(e->{
            OrdersController.pushOrder();
        });
        previousOrderButton.setOnAction(e->{
            OrdersController.popOrder();
        });


        HBox fieldBox = new HBox(newOrderButton,previousOrderButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);




        HBox customerOrderBox = new HBox(ItemForm.newOrderForm(),CustomerForm.customerForm(),CheckOutForm.checkOutForm());
        customerOrderBox.setPadding(new Insets(10));
        customerOrderBox.setSpacing(10);


        VBox mainPane = new VBox(Labels.titleLabel("Orders"),fieldBox,customerOrderBox);
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);
        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#e8e8e8"),new CornerRadii(0,0,0,0,false),null)));
        return mainPane;

    }
}
