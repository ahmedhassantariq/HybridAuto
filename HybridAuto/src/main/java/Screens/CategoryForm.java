package Screens;

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

    public static VBox categoryForm(Pane borderContainer){
        Label title = Labels.titleLabel("New Category");

        //Combo Boxes
        MFXTextField makeField = Fields.textField("Make",100,40);
        MFXTextField modelField = Fields.textField("Model",100,40);
        MFXTextField yearField = Fields.textField("Year",100,40);
        MFXButton addCarButton = Buttons.FunctionButton("Add",100,40);

        MFXTextField productField = Fields.textField("Product",100,40);
        MFXButton addProductButton = Buttons.FunctionButton("Add",100,40);

        addCarButton.setOnAction(e->{
            if(!makeField.getText().isEmpty()&&
                    !modelField.getText().isEmpty()&&
                    !yearField.getText().isEmpty()
            ){

            }
        });
        addProductButton.setOnAction(e->{
            if(!productField.getText().isEmpty()){

            }
        });

        HBox carContainer = new HBox(makeField,modelField,yearField,addCarButton);
        carContainer.setAlignment(Pos.CENTER);
        carContainer.setPadding(new Insets(10));
        carContainer.setSpacing(10);
        HBox productContainer = new HBox(productField,addProductButton);
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
            productField.clear();
        });
        HBox buttonBox = new HBox(resetButton,cancelButton);
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
