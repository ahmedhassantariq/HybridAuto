package Screens;

import Utils.Constants;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.ligaturesymbols.LigatureSymbols;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.sql.SQLException;

public class MainScreen {
    public static Parent mainScreen() throws SQLException, ClassNotFoundException {
        BorderPane viewPane = new BorderPane();
        viewPane.setPrefWidth(Constants.screenWidth);
        viewPane.setPrefHeight(Constants.screenHeight);
        //CSS viewPane
        viewPane.setStyle("-fx-background-color: WHITE");



        //SelectionPane
        VBox selectionPane = new VBox();
//        selectionPane.setStyle("-fx-background-color: #02557a");
        selectionPane.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(0,15,15,0,false),null)));

        selectionPane.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons
        MFXButton InventoryFormButton = new MFXButton("Inventory");
        InventoryFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
        InventoryFormButton.setAlignment(Pos.CENTER_LEFT);
        Font buttonFont = Font.font("Cooper",18);
        FontIcon fi = new FontIcon(PrestaShopIcons.BOOK);
        fi.setIconColor(Color.WHITE);
        fi.setIconSize(32);
        InventoryFormButton.setGraphic(fi);
        InventoryFormButton.setTextFill(Color.WHITE);
        InventoryFormButton.setFont(buttonFont);
        InventoryFormButton.setOnMouseEntered(e->{
            InventoryFormButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            fi.setIconColor(Color.web("#02557a"));
            InventoryFormButton.setTextFill(Color.web("#02557a"));
        });
        InventoryFormButton.setOnMouseExited(e->{
            InventoryFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            fi.setIconColor(Color.WHITE);
            InventoryFormButton.setTextFill(Color.WHITE);
        });


        MFXButton salesFormButton = new MFXButton("Sales");
        salesFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
        salesFormButton.setAlignment(Pos.CENTER_LEFT);
        FontIcon si = new FontIcon(PrestaShopIcons.MAGNIFYING_GLASS);
        si.setIconColor(Color.WHITE);
        si.setIconSize(32);
        salesFormButton.setGraphic(si);
        salesFormButton.setTextFill(Color.WHITE);
        salesFormButton.setFont(buttonFont);
        salesFormButton.setOnMouseEntered(e->{
            salesFormButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            si.setIconColor(Color.web("#02557a"));
            salesFormButton.setTextFill(Color.web("#02557a"));
        });
        salesFormButton.setOnMouseExited(e->{
            salesFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            si.setIconColor(Color.WHITE);
            salesFormButton.setTextFill(Color.WHITE);
        });

        MFXButton reportsFormButton = new MFXButton("Reports");
        reportsFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
        reportsFormButton.setAlignment(Pos.CENTER_LEFT);
        FontIcon ri = new FontIcon(PrestaShopIcons.PAPER_TABLET);
        ri.setIconColor(Color.WHITE);
        ri.setIconSize(32);
        reportsFormButton.setGraphic(ri);
        reportsFormButton.setTextFill(Color.WHITE);
        reportsFormButton.setFont(buttonFont);
        reportsFormButton.setOnMouseEntered(e->{
            reportsFormButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            ri.setIconColor(Color.web("#02557a"));
            reportsFormButton.setTextFill(Color.web("#02557a"));
        });
        reportsFormButton.setOnMouseExited(e->{
            reportsFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            ri.setIconColor(Color.WHITE);
            reportsFormButton.setTextFill(Color.WHITE);
        });

        MFXButton serviceFormButton = new MFXButton("Services");
        serviceFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
        serviceFormButton.setAlignment(Pos.CENTER_LEFT);
        FontIcon svi = new FontIcon(LigatureSymbols.STAR);
        svi.setIconColor(Color.WHITE);
        svi.setIconSize(32);
        serviceFormButton.setGraphic(svi);
        serviceFormButton.setTextFill(Color.WHITE);
        serviceFormButton.setFont(buttonFont);
        serviceFormButton.setOnMouseEntered(e->{
            serviceFormButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),null)));
            svi.setIconColor(Color.web("#02557a"));
            serviceFormButton.setTextFill(Color.web("#02557a"));
        });
        serviceFormButton.setOnMouseExited(e->{
            serviceFormButton.setBackground(new Background(new BackgroundFill(Color.web("#02557a"),new CornerRadii(15),null)));
            svi.setIconColor(Color.WHITE);
            serviceFormButton.setTextFill(Color.WHITE);
        });


//        MFXButton logoutButton = new MFXButton("");
        HBox logoutButton = new HBox();
        logoutButton.setBackground(new Background(new BackgroundFill(Color.web("#013349"),new CornerRadii(15,15,15,0,false),null)));
        FontIcon li = new FontIcon(LigatureSymbols.LOGOUT);
        logoutButton.setAlignment(Pos.CENTER_RIGHT);
        logoutButton.setPadding(new Insets(10));
        li.setIconColor(Color.WHITE);
        li.setIconSize(32);

//        logoutButton.setGraphic(li);
//        logoutButton.setTextFill(Color.WHITE);
//        logoutButton.setFont(buttonFont);
        logoutButton.getChildren().add(li);

        li.setOnMouseEntered(e->{
//            logoutButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,0,0,false),null)));
            li.setIconColor(Color.web("#001118"));
//            logoutButton.setTextFill(Color.web("#02557a"));
        });
        li.setOnMouseExited(e->{
//            logoutButton.setBackground(new Background(new BackgroundFill(Color.web("#013349"),new CornerRadii(15,15,0,0,false),null)));
            li.setIconColor(Color.WHITE);
//            logoutButton.setTextFill(Color.WHITE);
        });

        li.setOnMouseClicked(e->{
            try {
                Constants.logout();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });




        //Buttons Settings
        InventoryFormButton.setPrefWidth(200);
        InventoryFormButton.setPrefHeight(Constants.formHeight/6);
        salesFormButton.setPrefWidth(200);
        salesFormButton.setPrefHeight(Constants.formHeight/6);
        reportsFormButton.setPrefWidth(200);
        reportsFormButton.setPrefHeight(Constants.formHeight/6);
        logoutButton.setPrefWidth(200);
        serviceFormButton.setPrefWidth(200);
        serviceFormButton.setPrefHeight(Constants.formHeight/6);
        logoutButton.setPrefHeight(Constants.formHeight/8);
        //Adding nodes to SelectionPane
        selectionPane.getChildren().addAll(
                InventoryFormButton,
                salesFormButton,
                serviceFormButton,
                reportsFormButton,
                logoutButton
        );

        //Set ViewPane
        viewPane.setLeft(selectionPane);


        //Buttons Functions
        InventoryFormButton.setOnAction(e->{
            viewPane.setCenter(InventoryForm.inventoryForm());
        });




        return viewPane;
    }



}
