package Screens;

import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import io.github.palexdev.materialfx.css.themes.Themes;
import io.github.palexdev.materialfx.skins.MFXProgressSpinnerSkin;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InventoryForm {

    public static Parent inventoryForm(){



        MFXTextField searchField = Fields.textField("Search",300,40);

        MFXButton searchButton = Buttons.FunctionButton("Search",100,40);
        MFXButton createCategoryButton = Buttons.FunctionButton("Create New Category ",150,40);
        MFXButton addNewProductButton = Buttons.FunctionButton("Add Product",100,40);


        HBox fieldBox = new HBox(searchField,searchButton,createCategoryButton,addNewProductButton);
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);





        VBox productBox = ProductForm.productForm();
        VBox categoryBox = CategoryForm.categoryForm();

        TableView tableView = new TableView<>();
        tableView.setMinSize(500,200);

        BorderPane borderContainer = new BorderPane();
        borderContainer.setCenter(tableView);

        addNewProductButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(productBox))
                borderContainer.getChildren().remove(productBox);
            else borderContainer.setRight(productBox);
        });
        createCategoryButton.setOnAction(e->{
            if(borderContainer.getChildren().contains(categoryBox))
                borderContainer.getChildren().remove(categoryBox);
            else borderContainer.setRight(categoryBox);
        });


        VBox mainPane = new VBox(Labels.titleLabel("Inventory"),fieldBox,borderContainer);
        mainPane.setPadding(new Insets(10,0,0,0));
        mainPane.setSpacing(10);

        mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
//        mainPane.getStylesheets().add(Stylesheets.TABLE_VIEW.loadTheme());
        return mainPane;
    }
}
