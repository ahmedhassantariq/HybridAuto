package Screens;

import Entities.Product;
import Functionality.Forms.Controllers.ItemController;
import Functionality.Forms.Controllers.ProductController;
import Functionality.Forms.OrdersController;
import Styles.Buttons;
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
    private static final ItemController<Product> itemController = new ItemController<>();

    public static VBox newOrderForm() throws SQLException {

        MFXTextField serialField = itemController.getInputMap().get("serialno").getInputControl();



        HBox comboBoxContainer = new HBox(serialField);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton addButton = Buttons.FunctionButton("Add", 100, 40);
        MFXButton searchButton = Buttons.FunctionButton_Border("Search", 100, 40);

        addButton.setOnAction(e->{
            if(OrderTable.inventoryTable.getSelectionModel().getSelectedItem()!=null) {
                OrdersController.addOrderItem(OrderTable.inventoryTable.getSelectionModel().getSelectedItem());
            }
        });

        serialField.textProperty().addListener(e->{
            ItemController.search(serialField.getText());
        });

        searchButton.setOnAction(e->{
            ItemController.search(serialField.getText());
        });

        HBox buttonBox = new HBox(addButton, searchButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);


        VBox itemBox = new VBox(Labels.titleLabel("Inventory"), comboBoxContainer,OrderTable.saleTable(), buttonBox);
        itemBox.setSpacing(10);
        itemBox.setAlignment(Pos.TOP_CENTER);
        itemBox.setPrefSize(500, 400);
        itemBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//      productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        itemBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return itemBox;
    }
}
