package Screens;

import Entities.Stock;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Colors;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXStepper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ProductForm {
    private static MFXStepper stepper = new MFXStepper();
    public static VBox productForm(Pane borderContainer) {


        InventoryController.setMakeComboList();
        ObservableList<String> conditionList = FXCollections.observableArrayList("New","Used");

        MFXComboBox makeComboBox = new MFXComboBox(InventoryController.makeComboList);
        MFXComboBox modelComboBox = new MFXComboBox(InventoryController.modelComboList);
        MFXComboBox yearComboBox = new MFXComboBox(InventoryController.yearComboList);
        MFXComboBox typeComboBox = new MFXComboBox(InventoryController.productComboList);
        MFXComboBox conditionComboBox = new MFXComboBox(conditionList);


        makeComboBox.setFloatingText("Make");
        makeComboBox.setOnAction(e-> {
                if(makeComboBox.getValue()!=null) {
                    modelComboBox.clear();
                    yearComboBox.clear();
                    typeComboBox.clear();
                    InventoryController.setModelComboList(makeComboBox.getValue().toString());
                }
        });

        modelComboBox.setFloatingText("Model");
        modelComboBox.setOnAction(e->{
                if(modelComboBox.getValue()!=null) {
                    yearComboBox.clear();
                    typeComboBox.clear();
                    InventoryController.setYearComboList(modelComboBox.getValue().toString());
                }
        });

        yearComboBox.setFloatingText("Year");
        yearComboBox.setOnAction(e->{
            if(makeComboBox.getValue()!=null&&modelComboBox.getValue()!=null&&yearComboBox.getValue()!=null) {
                typeComboBox.clear();
                InventoryController.setProductComboList(makeComboBox.getValue().toString(),
                        modelComboBox.getValue().toString(),
                        yearComboBox.getValue().toString());
            }
        });

        conditionComboBox.setFloatingText("Condition");
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
            InventoryController.clearLists();
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
                serialField.clear();
                InventoryController.setInventoryList();
            }else {
                StatusScreen.setNotification("Fill Fields");
            }


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
                serialField.clear();
                InventoryController.setInventoryList();
            }else {
                StatusScreen.setNotification("Fill Fields");
            }
        });



        HBox buttonBox = new HBox(addButton,resetButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox productBox = new VBox(Labels.titleLabel("New Product"),comboBoxContainer,costCond,descriptionField,serialField,buttonBox);
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.TOP_CENTER);
        productBox.setMaxSize(600,300);
        productBox.setBackground(new Background(new BackgroundFill(Colors.productBoxColor,new CornerRadii(15,15,15,15,false),null)));
        productBox.setBorder(new Border(new BorderStroke(Colors.productBoxBorderColor,BorderStrokeStyle.SOLID,new CornerRadii(15,15,15,15,false), BorderStroke.THICK)));

        productBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return productBox;

    }
}
