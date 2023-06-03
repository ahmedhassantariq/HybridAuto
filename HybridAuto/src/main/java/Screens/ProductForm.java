package Screens;

import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ProductForm {

    public static VBox productForm(){
        //Combo Boxes
        ObservableList<String> makeList = FXCollections.observableArrayList(
                "Toyota","Honda","Nissan","Hyundai","Daihatsu");
        ObservableList<String> modelList = FXCollections.observableArrayList(
                "Corolla","Prius","Aqua","Vigo","ZX","V8"
        );
        ObservableList<String> yearList = FXCollections.observableArrayList();
        for (int i = 1950;i<= LocalDate.now().getYear();i++) {
            yearList.add(String.valueOf(i));
        }
        ObservableList<String> conditionList = FXCollections.observableArrayList(
                "New","Used"
        );
        ObservableList<String> productList = FXCollections.observableArrayList(
                "ABS","Battery"
        );


        MFXFilterComboBox makeComboBox = new MFXFilterComboBox(makeList);
        makeComboBox.setOnAction(e-> System.out.println("Cliked"));

        makeComboBox.setFloatingText("Make");
        MFXFilterComboBox modelComboBox = new MFXFilterComboBox(modelList);
        modelComboBox.setFloatingText("Model");
        MFXFilterComboBox yearComboBox = new MFXFilterComboBox(yearList);
        yearComboBox.setFloatingText("Year");
        MFXFilterComboBox conditionComboBox = new MFXFilterComboBox(conditionList);
        conditionComboBox.setFloatingText("Condition");
        MFXFilterComboBox typeComboBox = new MFXFilterComboBox(productList);
        typeComboBox.setFloatingText("Product");

        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXTextField costField = Fields.textField("Cost",100,40);



        MFXTextField descriptionField = Fields.textField("Description",300,40);
        HBox costCond = new HBox(typeComboBox,conditionComboBox,costField);
        costCond.setAlignment(Pos.CENTER);
        costCond.setPadding(new Insets(10));
        costCond.setSpacing(10);


        MFXTextField serialField = Fields.textField("SerialNo.",300,40);

        MFXButton addButton = Buttons.FunctionButton("Add",100,40);
        MFXButton resetButton = Buttons.FunctionButton_Border("Reset",100,40);

        resetButton.setOnAction(e->{
            makeComboBox.clear();
            modelComboBox.clear();
            yearComboBox.clear();
            typeComboBox.clear();
            conditionComboBox.clear();
            costField.clear();
            descriptionField.clear();
        });

        addButton.setOnAction(e->{


            serialField.clear();
                });
        serialField.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.ENTER)

                serialField.clear();
        });



        HBox buttonBox = new HBox(addButton,resetButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox productBox = new VBox(Labels.titleLabel("New Product"),comboBoxContainer,costCond,descriptionField,serialField,buttonBox);
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.TOP_CENTER);
        productBox.setMaxSize(600,300);
        productBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));
        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,15,15,15,false), BorderStroke.THICK)));

        productBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return productBox;

    }
}
