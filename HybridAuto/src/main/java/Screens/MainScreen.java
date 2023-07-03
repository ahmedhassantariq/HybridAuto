package Screens;

import Functionality.Database.DB.DbConnection;
import Styles.Buttons;
import Styles.Colors;
import Styles.Labels;
import Utils.Constants;
import Utils.TaskManager;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.sql.SQLException;

public class MainScreen {
    public static Parent mainScreen() throws SQLException, ClassNotFoundException {
        DbConnection.connectDB();
        TaskManager.checkUpdate();
//        TaskManager.setUpdate();

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
        FontIcon avatarIcon = FontIcon.of(PrestaShopIcons.CAR);
        avatarIcon.setIconSize(48);
        avatarIcon.setIconColor(Color.WHITE);

        Label avatarLabel = Labels.titleLabel("ADMIN");
        avatarLabel.setTextFill(Color.WHITE);

        VBox avatarBox = new VBox(avatarIcon,avatarLabel);
        avatarBox.setAlignment(Pos.CENTER);
        avatarBox.setBackground(new Background(new BackgroundFill(Colors.avatarBoxColor,new CornerRadii(0,0,0,0,false),null)));
        //Buttons
        MFXButton dashboardFormButton = Buttons.DashboardButton("Dashboard", FontIcon.of(PrestaShopIcons.DASHBOARD));
        MFXButton inventoryFormButton = Buttons.DashboardButton("Inventory", FontIcon.of(PrestaShopIcons.BOOK));
        MFXButton ordersFormButton = Buttons.DashboardButton("Orders", FontIcon.of(PrestaShopIcons.MAGNIFYING_GLASS));
        MFXButton reportsFormButton = Buttons.DashboardButton("Reports", FontIcon.of(PrestaShopIcons.PAPER_TABLET));
        MFXButton serviceFormButton = Buttons.DashboardButton("Services", FontIcon.of(PrestaShopIcons.SALE_TAG));
        MFXButton billsFormButton = Buttons.DashboardButton("Bills", FontIcon.of(PrestaShopIcons.BOOK));
        MFXButton logoutButton = Buttons.DashboardButton("Logout", FontIcon.of(PrestaShopIcons.FLAG_SCOUT));

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
