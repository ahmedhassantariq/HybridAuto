package Screens;

import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CategoryForm {

    public static VBox categoryForm(){
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

        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton addButton = Buttons.FunctionButton("Add",100,40);
        MFXButton cancelButton = Buttons.FunctionButton("Cancel",100,40);

        HBox buttonBox = new HBox(addButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox categoryBox = new VBox(title,comboBoxContainer,typeComboBox,buttonBox);
        categoryBox.setSpacing(10);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setMinSize(300,400);
        categoryBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,0,0,15,false),null)));
        categoryBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,0,0,15,false), BorderStroke.THICK)));
        categoryBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return categoryBox;

    }
}
