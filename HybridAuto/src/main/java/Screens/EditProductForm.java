package Screens;

import Entities.Stock;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.InventoryTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EditProductForm {

    public static VBox editProductForm(Stock stock, Pane borderContainer) {


        InventoryController.setMakeComboList();
        MFXComboBox makeComboBox = new MFXComboBox(InventoryController.makeComboList);
        makeComboBox.setFloatingText("Make");
        makeComboBox.setValue(stock.getMake());
        makeComboBox.setText(stock.getMake());
        makeComboBox.setOnAction(e-> {
            if(makeComboBox.getValue()!=null) {
                InventoryController.setModelComboList(makeComboBox.getValue().toString());
            }
        });

        MFXComboBox modelComboBox = new MFXComboBox(InventoryController.modelComboList);
        modelComboBox.setFloatingText("Model");
        modelComboBox.setValue(stock.getModel());
        modelComboBox.setText(stock.getModel());
        modelComboBox.setOnAction(e->{
            if(modelComboBox.getValue()!=null) {
                InventoryController.setYearComboList(modelComboBox.getValue().toString());
            }
        });


        MFXComboBox yearComboBox = new MFXComboBox(InventoryController.yearComboList);
        yearComboBox.setFloatingText("Year");
        yearComboBox.setValue(stock.getYear());
        yearComboBox.setText(stock.getYear());

        ObservableList<String> conditionList = FXCollections.observableArrayList("New","Used");
        MFXComboBox conditionComboBox = new MFXComboBox(conditionList);
        conditionComboBox.setFloatingText("Condition");
        conditionComboBox.setValue(stock.getCondition());
        conditionComboBox.setText(stock.getCondition());

        InventoryController.setProductComboList(stock.getMake(), stock.getModel(),stock.getYear());
        MFXComboBox typeComboBox = new MFXComboBox(InventoryController.productComboList);
        typeComboBox.setFloatingText("Product");
        typeComboBox.setValue(stock.getProductCategory());
        typeComboBox.setText(stock.getProductCategory());


        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXTextField costField = Fields.textField("Cost",100,40);
        costField.setText(stock.getCost());


        MFXTextField descriptionField = Fields.textField("Comments",300,40);
        descriptionField.setText(stock.getComments());
        HBox costCond = new HBox(typeComboBox,conditionComboBox,costField);
        costCond.setAlignment(Pos.CENTER);
        costCond.setPadding(new Insets(10));
        costCond.setSpacing(10);


        MFXTextField serialField = Fields.textField("SerialNo.",300,40);
        serialField.setText(stock.getSerialNumber());


        MFXButton updateButton = Buttons.FunctionButton("Update",100,40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Cancel",100,40);

        cancelButton.setOnAction(e->{
            borderContainer.getChildren().remove(borderContainer.getChildren().size()-1);
            InventoryTable.inventoryTable.getSelectionModel().clearSelection();
        });

        updateButton.setOnAction(e->{
            if(
                    stock.getStockID()!=null&&
                    makeComboBox.getValue()!=null&&
                            modelComboBox.getValue()!=null&&
                            yearComboBox.getValue()!=null&&
                            typeComboBox.getValue()!=null&&
                            conditionComboBox.getValue()!=null&&
                            costField.getText()!=null&&
                            !serialField.getText().isEmpty()&&
                            !serialField.getText().isBlank()
            ){
                    InventoryController.updateProduct(
                            stock.getStockID(),
                            makeComboBox.getValue().toString(),
                            modelComboBox.getValue().toString(),
                            yearComboBox.getValue().toString(),
                            typeComboBox.getValue().toString(),
                            conditionComboBox.getValue().toString(),
                            costField.getText(),
                            serialField.getText(),
                            descriptionField.getText());
            }

            serialField.clear();
            InventoryController.setInventoryList();
        });

        serialField.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.ENTER)
                serialField.clear();
        });



        HBox buttonBox = new HBox(updateButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox productBox = new VBox(Labels.titleLabel("Edit Product"),comboBoxContainer,costCond,descriptionField,serialField,buttonBox);
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.TOP_CENTER);
        productBox.setMaxSize(600,300);
        productBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));
        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,15,15,15,false), BorderStroke.THICK)));
//
//        productBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return productBox;

    }
}
