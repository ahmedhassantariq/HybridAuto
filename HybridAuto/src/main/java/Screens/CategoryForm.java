package Screens;

import Entities.Category;
import Entities.Product;
import Functionality.Database.InventoryService;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;

public class CategoryForm {
    private static Node[] inputs;

    public static VBox categoryForm() {
        Label title = Labels.titleLabel("New Category");

        //Combo Boxes
        MFXComboBox makeComboBox = new MFXComboBox();
        makeComboBox.setText("Make");
        MFXComboBox modelComboBox = new MFXComboBox();
        modelComboBox.setText("Model");
        MFXComboBox yearComboBox = new MFXComboBox();
        yearComboBox.setText("Year");
        MFXComboBox conditionComboBox = new MFXComboBox();
        conditionComboBox.setText("Condition");
        MFXComboBox typeComboBox = new MFXComboBox();
        typeComboBox.setText("Product");
        //todo update node inputs[] for Category + combo boxes


        HBox comboBoxContainer = new HBox(makeComboBox, modelComboBox, yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);


        MFXButton addButton = Buttons.FunctionButton("Add", 100, 40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Cancel", 100, 40);


        inputs = new Node[] {makeComboBox, modelComboBox, yearComboBox, conditionComboBox, typeComboBox};
        InventoryController.getInstance()
                .initCategoryController(CategoryForm.getModelFields(), CategoryForm.getInputs());

        addButton.setOnAction((e) -> {
            InventoryService.addCategory(makeComboBox.getSelectedText(), modelComboBox.getSelectedText(),
                    yearComboBox.getSelectedText(), typeComboBox.getSelectedText(), conditionComboBox.getSelectedText()
            );
            Category c = InventoryController.getInstance().getCategoryController().createModel(Category.getDummy());
            for(Field f : getModelFields()) {
                try {
                    System.out.println(f.getName() + ":\t" + f.get(c));
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        HBox buttonBox = new HBox(addButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox categoryBox = new VBox(title, comboBoxContainer, typeComboBox, buttonBox);
        categoryBox.setSpacing(10);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setMaxSize(600, 300);
        categoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
        categoryBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));
        categoryBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return categoryBox;

    }

    public static Node[] getInputs() {
        return inputs;
    }

    public static Field[] getModelFields() {
        try {
            return new Field[] {
                    Category.class.getDeclaredField("make"), Category.class.getDeclaredField("model"),
                    Category.class.getDeclaredField("year"), Category.class.getDeclaredField("product")
            };
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
