package Screens;

import Entities.Bill;
import Entities.Stock;
import Functionality.Forms.BillsController;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.BillTable;
import Utils.Formatter;
import Utils.InventoryTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillsForm {
    public static Parent expensesForm() throws SQLException {



        MFXComboBox billTypeComboBox = new MFXComboBox(BillsController.billTypeList);
        billTypeComboBox.setFloatingText("Bill Type");
        MFXTextField amountField = Fields.textField("Amount",100,40);
        amountField.delegateSetTextFormatter(Formatter.digitFormatter());
        amountField.setTextLimit(10);

        MFXDatePicker datePicker = new MFXDatePicker();
        datePicker.setMaxSize(150,40);
        datePicker.setEditable(false);


        MFXButton addBillButton = Buttons.FunctionButton("Add Bill", 100, 40);
        MFXButton updateBillButton = Buttons.FunctionButton("Update Bill", 100, 40);
        MFXButton editBillButton = Buttons.FunctionButton("Edit Bill", 100, 40);
        MFXButton cancelEditButton = Buttons.FunctionButton_Border("Cancel Edit", 100, 40);
        MFXButton removeBillButton = Buttons.FunctionButton("Remove Bill", 100, 40);

        HBox fieldBox = new HBox(billTypeComboBox,amountField,datePicker,addBillButton, editBillButton, removeBillButton);
        fieldBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.setPadding(new Insets(10));
        fieldBox.setSpacing(10);

        addBillButton.setOnAction(e->{
            if(billTypeComboBox.getValue()!=null&&!amountField.getText().isEmpty()&&datePicker.getValue()!=null){
                BillsController.addBill(new Bill("", billTypeComboBox.getValue().toString(), amountField.getText(), datePicker.getValue().toString()));
                BillsController.getBills();
            }
        });
        editBillButton.setOnAction(e->{
            if(BillTable.billTable.getSelectionModel().getSelectedItem()!=null){
                fieldBox.getChildren().removeAll(addBillButton,editBillButton);
                fieldBox.getChildren().add(3,updateBillButton);
                fieldBox.getChildren().add(4,cancelEditButton);
                removeBillButton.setDisable(true);
                billTypeComboBox.setValue(BillTable.billTable.getSelectionModel().getSelectedItem().getBillType());
                Timestamp timestamp = Timestamp.valueOf(BillTable.billTable.getSelectionModel().getSelectedItem().getDateTime());
                LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
                datePicker.setValue(localDate);
            }
        });
        cancelEditButton.setOnAction(e->{
            fieldBox.getChildren().removeAll(updateBillButton,cancelEditButton);
            fieldBox.getChildren().add(3,addBillButton);
            fieldBox.getChildren().add(4,editBillButton);
            removeBillButton.setDisable(false);
            billTypeComboBox.clear();
            amountField.clear();
            datePicker.clear();
            BillsController.getBills();
            StatusScreen.setNotification("Canceled");
        });
        updateBillButton.setOnAction(e->{
            if(billTypeComboBox.getValue()!=null&&!amountField.getText().isEmpty()&&datePicker.getValue()!=null) {
                BillsController.updateBill(new Bill(
                        BillTable.billTable.getSelectionModel().getSelectedItem().getBillID(),
                        billTypeComboBox.getValue().toString(), amountField.getText(), datePicker.getValue().toString()
                ));
                fieldBox.getChildren().removeAll(updateBillButton, cancelEditButton);
                fieldBox.getChildren().add(3, addBillButton);
                fieldBox.getChildren().add(4, editBillButton);
                removeBillButton.setDisable(false);
                billTypeComboBox.clear();
                amountField.clear();
                datePicker.clear();
                BillsController.getBills();
            }else {
                StatusScreen.setNotification("Fill Fields");
            }
        });
        removeBillButton.setOnAction(e->{
            if(BillTable.billTable.getSelectionModel().getSelectedItem()!=null){
                BillsController.deleteBill(BillTable.billTable.getSelectionModel().getSelectedItem());
                BillsController.getBills();
            }else {
                StatusScreen.setNotification("Select Bill");
            }
        });






        VBox mainPane = new VBox(Labels.titleLabel("Bills"), fieldBox, BillTable.billTable());
        mainPane.setPadding(new Insets(10, 0, 0, 0));
        mainPane.setSpacing(10);


        Platform.runLater(() -> {
            mainPane.getStylesheets().add(Stylesheets.TEXT_FIELD.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.DATE_PICKER.loadTheme());
            mainPane.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());

        });

        return mainPane;
    }
}
