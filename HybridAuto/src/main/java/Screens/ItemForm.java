package Screens;

import Functionality.Forms.InventoryController;
import Functionality.Forms.OrdersController;
import Styles.Buttons;
import Styles.Colors;
import Styles.Fields;
import Styles.Labels;
import Utils.OrderTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class ItemForm {

    public static VBox newOrderForm() throws SQLException {

        MFXTextField serialField = Fields.textField("Search by Serial", 300, 40);



        HBox comboBoxContainer = new HBox(serialField);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton addButton = Buttons.FunctionButton("Add", 100, 40);

        addButton.setOnAction(e->{
            if(OrderTable.inventoryTable.getSelectionModel().getSelectedItem()!=null) {
                OrdersController.addOrderItem(OrderTable.inventoryTable.getSelectionModel().getSelectedItem());
            }
        });

        serialField.textProperty().addListener(e->{
            InventoryController.searchText("","","",serialField.getText());
        });


        HBox buttonBox = new HBox(addButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);


        VBox itemBox = new VBox(Labels.titleLabel("Inventory"), comboBoxContainer,OrderTable.orderTable(), buttonBox);
        itemBox.setSpacing(10);
        itemBox.setAlignment(Pos.TOP_CENTER);
        itemBox.setPrefSize(400, 350);
        itemBox.setMaxSize(400,400);
        itemBox.setBackground(new Background(new BackgroundFill(Colors.itemBoxColor, new CornerRadii(15, 15, 15, 15, false), null)));

        itemBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return itemBox;
    }
}
