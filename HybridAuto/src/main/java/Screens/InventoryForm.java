package Screens;

import Entities.Product;
import Functionality.Database.DB.DbConnection;
import Functionality.Database.InventoryService;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.SaleTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
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
    private static Parent inventoryFormRoot;
    public static Parent inventoryForm() throws SQLException {
        if(inventoryFormRoot != null)
            return inventoryFormRoot; //don't recreate if already initialized

        MFXTextField searchField = Fields.textField("Search",300,40);
        MFXButton searchButton = Buttons.FunctionButton_Border("Search",100,40);
        MFXButton createCategoryButton = Buttons.FunctionButton("Create New Category ",150,40);
        MFXButton addNewProductButton = Buttons.FunctionButton("Add Product",100,40);
        MFXButton editProductButton = Buttons.FunctionButton("Edit Product",100,40);
        MFXButton deleteProductButton = Buttons.FunctionButton("Delete Product",100,40);



        HBox fieldBox = new HBox(searchField,searchButton,createCategoryButton,addNewProductButton,editProductButton,deleteProductButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);





        StackPane borderContainer = new StackPane();
        VBox productBox = ProductForm.productForm();
        VBox categoryBox = CategoryForm.categoryForm();
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
        borderContainer.getChildren().add(SaleTable.saleTable());


        addNewProductButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            else borderContainer.getChildren().add(productBox);
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
        });
        createCategoryButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
            else borderContainer.getChildren().add(categoryBox);
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
        });

        editProductButton.setOnAction(e->{

            if(borderContainer.getChildren().contains(editProductBox[0])){
                borderContainer.getChildren().remove(editProductBox[0]);
                SaleTable.tableView.getSelectionModel().clearSelection();}
            else {
                if (SaleTable.tableView.getSelectionModel().getSelectedItem() != null) {
                    Product product = SaleTable.tableView.getSelectionModel().getSelectedItem();
                    try {
                        editProductBox[0] = EditProductForm.editProductForm(product);
                        borderContainer.getChildren().add(editProductBox[0]);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
        });
        deleteProductButton.setOnAction(e->{
            System.out.println("Out");
            if (SaleTable.tableView.getSelectionModel().getSelectedItem() != null) {
                try {
                    System.out.println("Delete Button Pressed");
                    InventoryController.deleteProduct(SaleTable.tableView.getSelectionModel().getSelectedItem().getInventoryProductID());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                SaleTable.tableView.getSelectionModel().clearSelection();
            }
        });


        VBox mainPane = new VBox(Labels.titleLabel("Inventory"),fieldBox,borderContainer);
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);


        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        });

        inventoryFormRoot = mainPane;
        return mainPane;
    }
}
