package Screens;

import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CustomerForm {

    public static VBox customerForm() {
        MFXComboBox customerComboBox = new MFXComboBox();
        customerComboBox.setText("Customer");
        MFXTextField phoneField = Fields.textField("PhoneNo.", 150, 40);
        MFXTextField nameField = Fields.textField("Name", 150, 40);

        MFXComboBox carComboBox = new MFXComboBox();
        carComboBox.setText("Car License");

        MFXComboBox makeComboBox = new MFXComboBox();
        makeComboBox.setText("Make");
        MFXComboBox modelComboBox = new MFXComboBox();
        modelComboBox.setText("Model");
        MFXComboBox yearComboBox = new MFXComboBox();
        yearComboBox.setText("Year");

        HBox customerContainer = new HBox(customerComboBox, phoneField, nameField);
        customerContainer.setAlignment(Pos.CENTER);
        customerContainer.setPadding(new Insets(10));
        customerContainer.setSpacing(10);


        HBox comboBoxContainer = new HBox(makeComboBox, modelComboBox, yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton addButton = Buttons.FunctionButton("Add", 100, 40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Reset", 100, 40);

        HBox buttonBox = new HBox(addButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox cutomerBox = new VBox(Labels.titleLabel("Customer"), customerContainer, buttonBox);
        cutomerBox.setSpacing(10);
        cutomerBox.setAlignment(Pos.TOP_CENTER);
        cutomerBox.setMaxSize(300, 400);
        cutomerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        cutomerBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return cutomerBox;
    }
}
