package Screens;

import Entities.Customer;
import Functionality.Forms.OrdersController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.Formatter;
import Utils.CartTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CustomerForm {
    public static MFXTextField nameField = Fields.textField("Customer Name", 150, 40);
    public static MFXTextField phoneField = Fields.textField("PhoneNo.", 150, 40);
    public static VBox customerForm() {
        MFXComboBox customerComboBox = new MFXComboBox();
        customerComboBox.setFloatingText("Customer");

        nameField.setTextLimit(10);
        phoneField.delegateSetTextFormatter(Formatter.phoneFormatter());
        phoneField.setTextLimit(13);


        MFXComboBox carComboBox = new MFXComboBox();
        carComboBox.setFloatingText("Car License");
        MFXComboBox makeComboBox = new MFXComboBox();
        makeComboBox.setFloatingText("Make");
        MFXComboBox modelComboBox = new MFXComboBox();
        modelComboBox.setFloatingText("Model");
        MFXComboBox yearComboBox = new MFXComboBox();
        yearComboBox.setFloatingText("Year");

        HBox customerContainer = new HBox(phoneField, nameField);
        customerContainer.setAlignment(Pos.CENTER);
        customerContainer.setPadding(new Insets(10));
        customerContainer.setSpacing(10);


        HBox comboBoxContainer = new HBox(makeComboBox, modelComboBox, yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);


        MFXButton removeProductButton = Buttons.FunctionButton_Border("Remove", 100, 40);
        MFXButton clearAllButton = Buttons.FunctionButton("Clear Cart",70,40);

        removeProductButton.setOnAction(e->{
            OrdersController.removeOrderItem(CartTable.cartTable.getSelectionModel().getSelectedItem());
        });
        CheckOutForm.checkoutButton.setOnAction(e->{
            if(!nameField.getText().isEmpty()&&!phoneField.getText().isEmpty()&&!OrdersController.orderList.isEmpty()) {
                OrdersController.orderCheckout(new Customer(null, nameField.getText(), null, null, phoneField.getText(), null, null));
            }
            });

        phoneField.textProperty().addListener(e->{
            nameField.clear();
            if(OrdersController.searchCustomer(phoneField.getText())!=null){
                Customer customer = OrdersController.searchCustomer(phoneField.getText());
                String name="";
                if(customer.getFirstName()!=null)
                    name+=customer.getFirstName();
                if(customer.getMiddleName()!=null)
                    name+=" "+customer.getMiddleName();
                if(customer.getLastName()!=null)
                    name+=" "+customer.getLastName();
                nameField.setText(name);
        }});
        clearAllButton.setOnAction(e->{
            OrdersController.clearCart();
        });

        HBox buttonBox = new HBox(removeProductButton,clearAllButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox customerBox = new VBox(Labels.titleLabel("Customer Cart"), customerContainer, CartTable.orderTable(), buttonBox);
        customerBox.setSpacing(10);
        customerBox.setAlignment(Pos.TOP_CENTER);
        customerBox.setPrefSize(300, 350);
        customerBox.setMaxSize(300,400);
        customerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        customerBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return customerBox;
    }
}
