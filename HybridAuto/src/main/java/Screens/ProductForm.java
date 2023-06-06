package Screens;

import Entities.Stock;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ProductForm {

    public static VBox productForm(Pane borderContainer) {



        InventoryController.setMakeComboList();
        MFXComboBox makeComboBox = new MFXComboBox(InventoryController.makeComboList);
        makeComboBox.setFloatingText("Make");
        makeComboBox.setOnAction(e-> {
                if(makeComboBox.getValue()!=null) {
                    InventoryController.setModelComboList(makeComboBox.getValue().toString());
                }
        });



        MFXComboBox modelComboBox = new MFXComboBox(InventoryController.modelComboList);
        modelComboBox.setFloatingText("Model");
        modelComboBox.setOnAction(e->{
                if(modelComboBox.getValue()!=null) {
                    InventoryController.setYearComboList(modelComboBox.getValue().toString());
                }
        });


        MFXComboBox yearComboBox = new MFXComboBox(InventoryController.yearComboList);
        yearComboBox.setFloatingText("Year");
        yearComboBox.setOnAction(e->{
            if(makeComboBox.getValue()!=null&&modelComboBox.getValue()!=null&&yearComboBox.getValue()!=null) {
                InventoryController.setProductComboList(makeComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(),
                        yearComboBox.getValue().toString());
            }
        });


        ObservableList<String> conditionList = FXCollections.observableArrayList("New","Used");
        MFXComboBox conditionComboBox = new MFXComboBox(conditionList);
        conditionComboBox.setFloatingText("Condition");



        MFXComboBox typeComboBox = new MFXComboBox(InventoryController.productComboList);
        typeComboBox.setFloatingText("Product");

        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXTextField costField = Fields.textField("Cost",100,40);
        costField.delegateSetTextFormatter(Formatter.digitFormatter());
        costField.setTextLimit(10);

        MFXTextField descriptionField = Fields.textField("Comments",300,40);
        descriptionField.setTextLimit(200);

        HBox costCond = new HBox(typeComboBox,conditionComboBox,costField);
        costCond.setAlignment(Pos.CENTER);
        costCond.setPadding(new Insets(10));
        costCond.setSpacing(10);


        MFXTextField serialField = Fields.textField("SerialNo.",300,40);
        serialField.setTextLimit(50);

        MFXButton addButton = Buttons.FunctionButton("Add",100,40);
        MFXButton resetButton = Buttons.FunctionButton_Border("Reset",100,40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Cancel",100,40);

        cancelButton.setOnAction(e->{
            borderContainer.getChildren().remove(borderContainer.getChildren().size()-1);
        });

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
                InventoryController.insertProduct(new Stock(
                        null,
                        makeComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(),
                        yearComboBox.getValue().toString(),
                        typeComboBox.getValue().toString(),
                        serialField.getText(),
                        costField.getText(),
                        descriptionField.getText(),
                        conditionComboBox.getValue().toString()));
            }

            serialField.clear();
            InventoryController.setInventoryList();
        });

        serialField.setOnKeyPressed(e->{
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
                InventoryController.insertProduct(new Stock(
                        null,
                        makeComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(),
                        yearComboBox.getValue().toString(),
                        typeComboBox.getValue().toString(),
                        serialField.getText(),
                        costField.getText(),
                        descriptionField.getText(),
                        conditionComboBox.getValue().toString()));
            }

            serialField.clear();
                InventoryController.setInventoryList();

        });



        HBox buttonBox = new HBox(addButton,resetButton,cancelButton);
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
