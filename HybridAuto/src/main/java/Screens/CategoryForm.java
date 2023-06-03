package Screens;

import Entities.Category;
import Functionality.Forms.Controllers.CategoryController;
import Functionality.Forms.OldControllerStuff.InventoryController;
import Styles.Buttons;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;

public class CategoryForm {
    private static VBox categoryForm;
    private static final CategoryController<Category> categoryController = new CategoryController<>();

    public static VBox categoryForm(){
        if(categoryForm != null)
            return categoryForm;

        Label title = Labels.titleLabel("New Category");

        //Combo Boxes
        MFXFilterComboBox<String> makeComboBox = categoryController.getInputMap().get("make").getInputControl();
        makeComboBox.setText("Make");

        MFXFilterComboBox<String> modelComboBox = categoryController.getInputMap().get("model").getInputControl();
        modelComboBox.setText("Model");

        MFXFilterComboBox<String> yearComboBox = categoryController.getInputMap().get("year").getInputControl();
        yearComboBox.setText("Year");

        MFXFilterComboBox<String> conditionComboBox = categoryController.getInputMap().get("condition").getInputControl();
        conditionComboBox.setText("Condition");

        MFXFilterComboBox<String> typeComboBox = categoryController.getInputMap().get("type").getInputControl();
        typeComboBox.setText("Product");

        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);


        MFXButton addButton = Buttons.FunctionButton("Add",100,40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Reset",100,40);


        addButton.setOnAction((e) -> {
            new CategoryController<Category>().create();
        });


        HBox buttonBox = new HBox(addButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox categoryBox = new VBox(title,comboBoxContainer,typeComboBox,buttonBox);
        categoryBox.setSpacing(10);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setMaxSize(600,300);
        categoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));
        categoryBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,   15,15,15,false), BorderStroke.THICK)));
        categoryBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());

        categoryForm = categoryBox;
        return categoryBox;

    }
}
