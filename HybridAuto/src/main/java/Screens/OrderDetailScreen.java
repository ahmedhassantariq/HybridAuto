package Screens;

import Entities.Services;
import Functionality.Forms.InventoryController;
import Functionality.Forms.OrdersController;
import Functionality.Forms.ServicesController;
import Styles.Labels;
import Utils.InventoryTable;
import Utils.OrderDetailsTable;
import javafx.geometry.Insets;
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

    public static void showDetails(Services services){
        Stage stage = new Stage();
        stage.setTitle("Hybrid AutoTech - Service Details");
        VBox mainBox = new VBox();
        ServicesController.getOrderDetails(services.getOrderID());
        mainBox.getChildren().add(OrderDetailsTable.orderDetailsTable());
        Label orderID = Labels.checkOutLabel("Order-ID: "+ServicesController.getOrderTotal(services.getOrderID()));
        Label customerName = Labels.checkOutLabel("Customer Name: "+OrdersController.searchCustomer(services.getPhone()).getFullName());
        Label customerPhone = Labels.checkOutLabel("Phone: "+services.getPhone());
        Label orderTotal = Labels.checkOutLabel("Total Amount: "+ServicesController.getOrderTotal(services.getOrderID()));
        VBox detailBox = new VBox(orderID,customerName,customerPhone,orderTotal);
        detailBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0, 0, 0, 0, false), null)));
        detailBox.setPadding(new Insets(5));
        detailBox.setMinHeight(50);
        stage.setResizable(false);

        mainBox.getChildren().add(detailBox);
        Scene scene = new Scene(mainBox,600, 350);
        stage.setScene(scene);
        stage.show();
    }

}
