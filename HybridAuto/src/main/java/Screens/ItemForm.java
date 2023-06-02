package Screens;

import Entities.Product;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import Utils.SaleTable;
import Utils.TableView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;

public class ItemForm {

    public static VBox newOrderForm() {

        MFXComboBox makeComboBox = new MFXComboBox();
        makeComboBox.setText("Make");
        MFXComboBox modelComboBox = new MFXComboBox();
        modelComboBox.setText("Model");
        MFXComboBox yearComboBox = new MFXComboBox();
        yearComboBox.setText("Year");
        MFXTextField serialField = Fields.textField("SerialNo.", 150, 40);


        HBox comboBoxContainer = new HBox(makeComboBox, modelComboBox, yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXButton addButton = Buttons.FunctionButton("Add", 100, 40);
        MFXButton cancelButton = Buttons.FunctionButton_Border("Reset", 100, 40);

        HBox buttonBox = new HBox(addButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);


        ScrollPane scrollPane = new ScrollPane(SaleTable.saleTable());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox itemBox = new VBox(Labels.titleLabel("Items"), comboBoxContainer,serialField,scrollPane, buttonBox);
        itemBox.setSpacing(10);
        itemBox.setAlignment(Pos.TOP_CENTER);
        itemBox.setMaxSize(300, 400);
        itemBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(15, 15, 15, 15, false), null)));
//        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"), BorderStrokeStyle.SOLID, new CornerRadii(15, 15, 15, 15, false), BorderStroke.THICK)));

        itemBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return itemBox;
    }
}
