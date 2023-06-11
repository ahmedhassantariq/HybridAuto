package Screens;

import Entities.Stock;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.InventoryTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class InventoryForm {
    public static Parent inventoryForm() throws SQLException {

        MFXTextField searchField = Fields.textField("Search by Serial",300,40);
        MFXButton createCategoryButton = Buttons.FunctionButton("Create New Category ",150,40);
        MFXButton addNewProductButton = Buttons.FunctionButton("Add Product",100,40);
        MFXButton editProductButton = Buttons.FunctionButton("Edit Product",100,40);
        MFXButton deleteProductButton = Buttons.FunctionButton("Delete Product",100,40);



        HBox fieldBox = new HBox(searchField,createCategoryButton,addNewProductButton,editProductButton,deleteProductButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);





        StackPane borderContainer = new StackPane();
        VBox productBox = ProductForm.productForm(borderContainer);
        VBox categoryBox = CategoryForm.categoryForm(borderContainer);
        final VBox[] editProductBox = {null};
//                = EditProductForm.editProductForm(SaleTable.tableView.getSelectionModel().getSelectedItem());
//        Platform.runLater(() -> {
//            MFXTableView<Product> tableView = new MFXTableView<>();
//            tableView.autosizeColumnsOnInitialization();
//            searchButton.setOnAction(e->{
//
//                tableView.update();
//            });
//            tableView.setMaxSize(500,300);
//            borderContainer.getChildren().add(tableView);
//
//        });
        borderContainer.getChildren().add(InventoryTable.inventoryTable());


        searchField.textProperty().addListener(e->{
            InventoryController.searchText("","","",searchField.getText());
        });

        addNewProductButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            else borderContainer.getChildren().add(productBox);
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
            if(borderContainer.getChildren().contains(editProductBox[0]))
                borderContainer.getChildren().remove(editProductBox[0]);
        });
        createCategoryButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
            else borderContainer.getChildren().add(categoryBox);
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            if(borderContainer.getChildren().contains(editProductBox[0]))
                borderContainer.getChildren().remove(editProductBox[0]);
        });

        editProductButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(editProductBox[0])){
                borderContainer.getChildren().remove(editProductBox[0]);
                InventoryTable.inventoryTable.getSelectionModel().clearSelection();}
            else {
                if (InventoryTable.inventoryTable.getSelectionModel().getSelectedItem() != null) {
                    Stock stock = InventoryTable.inventoryTable.getSelectionModel().getSelectedItem();
                        editProductBox[0] = EditProductForm.editProductForm(stock,borderContainer);
                        borderContainer.getChildren().add(editProductBox[0]);
                }
            }
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
        });
        deleteProductButton.setOnAction(e->{
            if (InventoryTable.inventoryTable.getSelectionModel().getSelectedItem() != null) {
                try {
                    InventoryController.deleteProduct(InventoryTable.inventoryTable.getSelectionModel().getSelectedItem().getStockID());
                    InventoryController.setInventoryList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                InventoryTable.inventoryTable.getSelectionModel().clearSelection();
            }
        });


        VBox mainPane = new VBox(Labels.titleLabel("Inventory"),fieldBox,borderContainer);
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);


        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        });

        return mainPane;
    }
}
