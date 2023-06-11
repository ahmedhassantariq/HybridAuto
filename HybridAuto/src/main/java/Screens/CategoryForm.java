package Screens;

import Entities.Car;
import Entities.Stock;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CategoryForm {

    public static VBox categoryForm(Pane borderContainer){
        Label title = Labels.titleLabel("New Category");

        //Combo Boxes
        MFXTextField makeField = Fields.textField("Make",100,40);
        MFXTextField modelField = Fields.textField("Model",100,40);
        MFXTextField yearField = Fields.textField("Year",100,40);
        yearField.delegateSetTextFormatter(Formatter.digitFormatter());
        yearField.setTextLimit(4);

        MFXTextField categoryField = Fields.textField("Product",100,40);
        categoryField.setTextLimit(20);

        MFXButton addCategoryButton = Buttons.FunctionButton("Add",100,40);

        addCategoryButton.setOnAction(e->{
            if(!makeField.getText().isEmpty()&&
                    !modelField.getText().isEmpty()&&
                    !yearField.getText().isEmpty()&&
                    !categoryField.getText().isEmpty()
            ){
                InventoryController.insertCategory(new Stock(null,makeField.getText(),modelField.getText(),yearField.getText(),categoryField.getText(),null,null,null,null));

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
