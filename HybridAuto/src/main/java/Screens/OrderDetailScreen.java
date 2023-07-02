package Screens;

import Entities.Services;
import Functionality.Forms.InventoryController;
import Functionality.Forms.OrdersController;
import Functionality.Forms.ServicesController;
import Styles.Buttons;
import Styles.Colors;
import Styles.Labels;
import Utils.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.SQLException;

import static Utils.InventoryTable.inventoryTable;

public class OrderDetailScreen {
    public static Stage stage = new Stage();

    public static void showDetails(Services services){
        stage.setTitle("Hybrid AutoTech - Service Details");
        VBox mainBox = new VBox();
        ServicesController.getOrderDetails(services.getOrderID());
        mainBox.getChildren().add(OrderDetailsTable.orderDetailsTable());
        Label orderID = Labels.checkOutLabel("Order-ID: "+services.getOrderID());
        Label customerName = Labels.checkOutLabel("Customer Name: "+OrdersController.searchCustomer(services.getPhone()).getFullName());
        Label customerPhone = Labels.checkOutLabel("Phone: "+services.getPhone());
        Label orderTotal = Labels.checkOutLabel("Total Amount: "+ Formatter.doublePrefix(Double.parseDouble(ServicesController.getOrderTotal(services.getOrderID()))));

        MFXButton returnOrderButton = Buttons.FunctionButton("Return Order",100,40);
        MFXButton viewReceiptButton = Buttons.FunctionButton_Border("View Receipt",100,40);

        returnOrderButton.setOnAction(e->{
                ServicesController.returnOrder(services);
        });

        viewReceiptButton.setOnAction(e->{
            PDFDocument.show(services.getOrderID()+".pdf");
        });






        HBox fieldBox = new HBox(returnOrderButton, viewReceiptButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Colors.fieldBoxColor,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setSpacing(10);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setPadding(new Insets(5));

        VBox detailBox = new VBox(orderID,customerName,customerPhone,orderTotal);
        detailBox.setBackground(new Background(new BackgroundFill(Colors.mainPaneColor, new CornerRadii(0, 0, 0, 0, false), null)));
        detailBox.setPadding(new Insets(5));
        detailBox.setMinHeight(50);
        stage.setResizable(false);

        mainBox.getChildren().addAll(detailBox, fieldBox);
        Scene scene = new Scene(mainBox,600, 400);
        stage.setScene(scene);
        stage.show();
    }

}
