package Screens;

import Entities.Category;
import Entities.Stock;
import Functionality.Forms.Controllers.CategoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CategoryForm {
    private static final CategoryController<Stock> categoryController = new CategoryController<>();

    public static VBox categoryForm(Pane borderContainer){
        Label title = Labels.titleLabel("New Category");

        //Combo Boxes
        MFXTextField makeField = categoryController.getInputMap().get("make").getInputControl();
        MFXTextField modelField = categoryController.getInputMap().get("model").getInputControl();
        MFXTextField yearField = categoryController.getInputMap().get("year").getInputControl();
        MFXTextField categoryField = categoryController.getInputMap().get("category").getInputControl();
        MFXButton addCategoryButton = Buttons.FunctionButton("Add",100,40);

        addCategoryButton.setOnAction(e->{
            if(!makeField.getText().isEmpty()&&
                    !modelField.getText().isEmpty()&&
                    !yearField.getText().isEmpty()&&
                    !categoryField.getText().isEmpty()
            ){
                categoryController.create();
            }
        });

        HBox carContainer = new HBox(makeField,modelField,yearField);
        carContainer.setAlignment(Pos.CENTER);
        carContainer.setPadding(new Insets(10));
        carContainer.setSpacing(10);
        HBox productContainer = new HBox(categoryField);
        productContainer.setAlignment(Pos.CENTER);
        productContainer.setPadding(new Insets(10));
        productContainer.setSpacing(10);


        MFXButton resetButton = Buttons.FunctionButton_Border("Reset",100,40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Cancel",100,40);

        cancelButton.setOnAction(e->{
            borderContainer.getChildren().remove(borderContainer.getChildren().size()-1);
        });
        resetButton.setOnAction(e->{
            makeField.clear();
            modelField.clear();
            yearField.clear();
            categoryField.clear();
        });
        HBox buttonBox = new HBox(addCategoryButton,resetButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox categoryBox = new VBox(title,carContainer,productContainer,buttonBox);
        categoryBox.setSpacing(10);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setMaxSize(600,300);
        categoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));
        categoryBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,   15,15,15,false), BorderStroke.THICK)));
        categoryBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return categoryBox;

    }
}
