package Screens;

import Functionality.Forms.InventoryController;
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

import java.sql.SQLException;

public class ProductForm {

    public static VBox productForm() throws SQLException {



        InventoryController.setMakeComboList();
        MFXComboBox makeComboBox = new MFXComboBox(InventoryController.makeComboList);
        makeComboBox.setFloatingText("Make");
        makeComboBox.setOnAction(e-> {
            try {
                if(makeComboBox.getValue()!=null) {
                    InventoryController.setModelComboList(makeComboBox.getValue().toString());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        MFXComboBox modelComboBox = new MFXComboBox(InventoryController.modelComboList);
        modelComboBox.setFloatingText("Model");
        modelComboBox.setOnAction(e->{
            try {
                if(modelComboBox.getValue()!=null) {
                    InventoryController.setYearComboList(modelComboBox.getValue().toString());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        MFXComboBox yearComboBox = new MFXComboBox(InventoryController.yearComboList);
        yearComboBox.setFloatingText("Year");

        ObservableList<String> conditionList = FXCollections.observableArrayList("New","Used");
        MFXComboBox conditionComboBox = new MFXComboBox(conditionList);
        conditionComboBox.setFloatingText("Condition");


        InventoryController.setProductComboList();
        MFXComboBox typeComboBox = new MFXComboBox(InventoryController.productComboList);
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
            serialField.clear();
        });

        addButton.setOnAction(e->{
            if(
                    makeComboBox.getValue()!=null&
                            modelComboBox.getValue()!=null&
                            yearComboBox.getValue()!=null&
                            typeComboBox.getValue()!=null&
                            conditionComboBox.getValue()!=null&
                            costField.getText()!=null&
                            !serialField.getText().isEmpty()&
                            !serialField.getText().isBlank()
            ){
                try {
                            InventoryController.insertProduct(
                                    makeComboBox.getValue().toString(),
                                    modelComboBox.getValue().toString(),
                                    yearComboBox.getValue().toString(),
                                    typeComboBox.getValue().toString(),
                                    conditionComboBox.getValue().toString(),
                                    costField.getText(),
                                    serialField.getText(),
                                    descriptionField.getText());
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            serialField.clear();
            try {
                InventoryController.setInventoryList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
