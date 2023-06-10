package Screens;

import Entities.Customer;
import Functionality.Forms.Controllers.CustomerController;
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
    private static final CustomerController<Customer> customerController = new CustomerController<>();

    public static VBox customerForm() {
        MFXComboBox<String> customerComboBox = customerController.getInputMap().get("customer").getInputControl();
        customerComboBox.setFloatingText("Customer");

        MFXTextField nameField = customerController.getInputMap().get("name").getInputControl();
        MFXTextField phoneField = customerController.getInputMap().get("phone").getInputControl();

        phoneField.delegateSetTextFormatter(Formatter.phoneFormatter());
        phoneField.setTextLimit(13);


        MFXComboBox<String> carComboBox = customerController.getInputMap().get("license").getInputControl();
        carComboBox.setFloatingText("Car License");
        MFXComboBox<String> makeComboBox = customerController.getInputMap().get("make").getInputControl();
        makeComboBox.setFloatingText("Make");
        MFXComboBox<String> modelComboBox = customerController.getInputMap().get("model").getInputControl();
        modelComboBox.setFloatingText("Model");
        MFXComboBox<String> yearComboBox = customerController.getInputMap().get("year").getInputControl();
        yearComboBox.setFloatingText("Year");

        HBox customerContainer = new HBox(nameField, phoneField);
        customerContainer.setAlignment(Pos.CENTER);
        customerContainer.setPadding(new Insets(10));
        customerContainer.setSpacing(10);


        HBox comboBoxContainer = new HBox(makeComboBox, modelComboBox, yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton confirmButton = Buttons.FunctionButton("Confirm", 100, 40);
        MFXButton removeProductButton = Buttons.FunctionButton_Border("Remove", 100, 40);

        removeProductButton.setOnAction(e->{
            OrdersController.removeOrderItem(CartTable.cartTable.getSelectionModel().getSelectedItem());
        });

        HBox buttonBox = new HBox(confirmButton, removeProductButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox cutomerBox = new VBox(Labels.titleLabel("Customer Cart"), customerContainer, CartTable.orderTable(), buttonBox);
        cutomerBox.setSpacing(10);
        cutomerBox.setAlignment(Pos.TOP_CENTER);
        cutomerBox.setPrefSize(300, 400);
        cutomerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        cutomerBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return cutomerBox;
    }
}
