package Screens;

import Entities.Product;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
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

public class InventoryForm {
    private static Parent inventoryFormRoot;
    public static Parent inventoryForm(){
        if(inventoryFormRoot != null)
            return inventoryFormRoot; //don't recreate if already initialized

        MFXTextField searchField = Fields.textField("Search",300,40);
        MFXButton searchButton = Buttons.FunctionButton_Border("Search",100,40);
        MFXButton createCategoryButton = Buttons.FunctionButton("Create New Category ",150,40);
        MFXButton addNewProductButton = Buttons.FunctionButton("Add Product",100,40);

        HBox fieldBox = new HBox(searchField,searchButton,createCategoryButton,addNewProductButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(   15,15,15,15,false),null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);





        StackPane borderContainer = new StackPane();
        VBox productBox = ProductForm.productForm();
        VBox categoryBox = CategoryForm.categoryForm();
        Platform.runLater(() -> {
            MFXTableView<Product> tableView = new MFXTableView<>();
            tableView.autosizeColumnsOnInitialization();
            searchButton.setOnAction(e->{

                tableView.update();
            });
            tableView.setMaxSize(500,300);
            borderContainer.getChildren().add(tableView);

        });


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
