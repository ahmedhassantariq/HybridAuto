package Screens;

import Functionality.Database.DbConnection;
import Styles.Buttons;
import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.ligaturesymbols.LigatureSymbols;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.sql.SQLException;

public class MainScreen {
    public static Parent mainScreen() throws SQLException, ClassNotFoundException {
        DbConnection dbConnection = new DbConnection();

        BorderPane viewPane = new BorderPane();
        viewPane.setPrefWidth(Constants.screenWidth);
        viewPane.setPrefHeight(Constants.screenHeight);
        //CSS viewPane
//        viewPane.setStyle("-fx-background-color: #e8e8e8");
        viewPane.setBackground(new Background(new BackgroundFill(Color.web("#e8e8e8"),new CornerRadii(0,0,0,0,false),null)));

        //SelectionPane
        VBox selectionPane = new VBox();
//        selectionPane.setStyle("-fx-background-color: #02557a");
        selectionPane.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(0,15,15,0,false),null)));
        selectionPane.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons
        MFXButton dashboardFormButton = Buttons.DashboardButton("Dashboard", FontIcon.of(PrestaShopIcons.DASHBOARD));
        MFXButton inventoryFormButton = Buttons.DashboardButton("Inventory", FontIcon.of(PrestaShopIcons.BOOK));
        MFXButton ordersFormButton = Buttons.DashboardButton("Orders", FontIcon.of(PrestaShopIcons.MAGNIFYING_GLASS));
        MFXButton reportsFormButton = Buttons.DashboardButton("Reports", FontIcon.of(PrestaShopIcons.PAPER_TABLET));
        MFXButton serviceFormButton = Buttons.DashboardButton("Services", FontIcon.of(PrestaShopIcons.SALE_TAG));
        MFXButton utilitiesFormButton = Buttons.DashboardButton("Utilities", FontIcon.of(LigatureSymbols.EXTERNAL));
        MFXButton logoutButton = Buttons.DashboardButton("Logout", FontIcon.of(LigatureSymbols.LOGOUT));

        //Adding nodes to SelectionPane
        selectionPane.getChildren().addAll(
                dashboardFormButton,
                inventoryFormButton,
                ordersFormButton,
                serviceFormButton,
                utilitiesFormButton,
                reportsFormButton,
                logoutButton
        );

        //Set ViewPane
        viewPane.setLeft(selectionPane);


        //Buttons Functions
        dashboardFormButton.setOnAction(e->{
            viewPane.setCenter(DashboardForm.dashboardForm());
        });

        inventoryFormButton.setOnAction(e->{
            viewPane.setCenter(InventoryForm.inventoryForm());
        });
        ordersFormButton.setOnAction(e->{
            viewPane.setCenter(OrderForm.orderForm());
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




        return viewPane;
    }



}
