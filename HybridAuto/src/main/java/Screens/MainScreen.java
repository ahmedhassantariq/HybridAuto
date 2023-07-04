package Screens;

import Functionality.Database.DB.DbConnection;
import Styles.Buttons;
import Styles.Colors;
import Styles.Icons;
import Styles.Labels;
import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class MainScreen {
    public static Parent mainScreen() throws SQLException, ClassNotFoundException {
        DbConnection.connectDB();

        BorderPane viewPane = new BorderPane();
        viewPane.setPrefWidth(Constants.screenWidth);
        viewPane.setPrefHeight(Constants.screenHeight);
        //CSS viewPane
//        viewPane.setStyle("-fx-background-color: #e8e8e8");
        viewPane.setBackground(new Background(new BackgroundFill(Colors.mainPaneColor,new CornerRadii(0,0,0,0,false),null)));

        //SelectionPane
        VBox selectionPane = new VBox();
//        selectionPane.setStyle("-fx-background-color: #02557a");
        selectionPane.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(0,15,15,0,false),null)));
        selectionPane.setAlignment(Pos.BOTTOM_CENTER);
        //Avatar


        Label avatarLabel = Labels.titleLabel("ADMIN");
        if(Constants.logInUsername!=null){
            avatarLabel.setText(Constants.logInUsername);
        }
        avatarLabel.setTextFill(Color.WHITE);

        VBox avatarBox = new VBox(Icons.avatar,avatarLabel);
        avatarBox.setAlignment(Pos.CENTER);
        avatarBox.setBackground(new Background(new BackgroundFill(Colors.avatarBoxColor,new CornerRadii(0,0,0,0,false),null)));
        //Buttons
        MFXButton dashboardFormButton = Buttons.DashboardButton("Dashboard", Icons.dashboard);
        MFXButton inventoryFormButton = Buttons.DashboardButton("Inventory", Icons.inventory);
        MFXButton ordersFormButton = Buttons.DashboardButton("Orders", Icons.orders);
        MFXButton serviceFormButton = Buttons.DashboardButton("Services", Icons.services);
        MFXButton billsFormButton = Buttons.DashboardButton("Bills", Icons.bills);
        MFXButton reportsFormButton = Buttons.DashboardButton("Reports", Icons.reports);
        MFXButton logoutButton = Buttons.DashboardButton("Logout", Icons.logOut);

        //Adding nodes to SelectionPane
        selectionPane.getChildren().addAll(
                avatarBox,
                dashboardFormButton,
                inventoryFormButton,
                ordersFormButton,
                serviceFormButton,
                billsFormButton,
                reportsFormButton,
                logoutButton
        );

        //Set ViewPane
        viewPane.setLeft(selectionPane);
        viewPane.setBottom(StatusScreen.statusScreen());


        //Buttons Functions
        dashboardFormButton.setOnAction(e->{
            viewPane.setCenter(DashboardForm.dashboardForm());
        });

        inventoryFormButton.setOnAction(e->{
            try {
                viewPane.setCenter(InventoryForm.inventoryForm());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        ordersFormButton.setOnAction(e->{
            try {
                viewPane.setCenter(OrderForm.orderForm());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        serviceFormButton.setOnAction(e->{
            try {
                viewPane.setCenter(ServicesForm.servicesForm());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        billsFormButton.setOnAction(e->{
            try{
                viewPane.setCenter(BillsForm.expensesForm());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        reportsFormButton.setOnAction(e->{
            try{
                viewPane.setCenter(ReportsForm.reportsForm());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        logoutButton.setOnMouseClicked(e->{
            try {
                Constants.logout();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        StatusScreen.settingsSymbol.setOnMouseClicked(e->{
            try {
                viewPane.setCenter(SettingsForm.settingsForm());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return viewPane;
    }



}
