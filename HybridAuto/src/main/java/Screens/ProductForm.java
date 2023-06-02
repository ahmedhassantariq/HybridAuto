package Screens;

import Entities.Product;
import Functionality.Database.InventoryService;
import Functionality.Forms.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductForm {
    private static Node[] inputs;

    public static VBox productForm(){
        Label title = Labels.titleLabel("New Product");

        //Combo Boxes
        MFXComboBox<String> makeComboBox = new MFXComboBox<>();
        makeComboBox.setPromptText("Make");
        MFXComboBox<String> modelComboBox = new MFXComboBox<>();
        modelComboBox.setPromptText("Model");
        MFXComboBox<String> yearComboBox = new MFXComboBox<>();
        yearComboBox.setPromptText("Year");
        MFXComboBox<String> conditionComboBox = new MFXComboBox<>();
        conditionComboBox.setPromptText("Condition");
        MFXComboBox<String> typeComboBox = new MFXComboBox<>();
        typeComboBox.setPromptText("Product");

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
        MFXButton cancelButton = Buttons.FunctionButton_Border("Reset",100,40);


        inputs = new Node[]{
                makeComboBox, modelComboBox, yearComboBox, conditionComboBox, //typeComboBox,
                costField, descriptionField, serialField
        };
        InventoryController.getInstance()
                .initProductController(ProductForm.getModelFields(), ProductForm.getInputs());

        addButton.setOnAction((e) -> {
            // TODO: 6/2/2023 correctly pass a product to InventoryService.addProduct()
//            Product p1 = new Product(makeComboBox.getSelectedText(), modelComboBox.getSelectedText(),
//                    yearComboBox.getSelectedText(), typeComboBox.getSelectedText(),  conditionComboBox.getSelectedText(),
//                    descriptionField.getText(), serialField.getText());
//            Product p = new Product(inventoryProductID, carID, productID, serialField.getText(), cost, descriptionField.getText(), conditionComboBox.getSelectedText());
//            InventoryService.addProduct(p);
            Product p = InventoryController.getInstance().getProductController().createModel(Product.getDummy());
            InventoryService.addProduct(p);
        });
        cancelButton.setOnAction((e) -> {
            Arrays.stream(getInputs()).forEach(n -> {
                if(n instanceof MFXComboBox<?> mcb) {
                    mcb.setText("");
                } else if (n instanceof TextField tf) {
                    tf.setText("");
                }
            });
        });

        HBox buttonBox = new HBox(addButton,cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        VBox productBox = new VBox(title,comboBoxContainer,costCond,descriptionField,serialField,buttonBox);
        productBox.setSpacing(10);
        productBox.setAlignment(Pos.TOP_CENTER);
        productBox.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15,15,15,15,false),null)));
        productBox.setBorder(new Border(new BorderStroke(Color.web("02557a"),BorderStrokeStyle.SOLID,new CornerRadii(15,15,15,15,false), BorderStroke.THICK)));
        productBox.setMaxSize(600,300);
        productBox.getStylesheets().add(Stylesheets.COMBO_BOX.loadTheme());
        return productBox;

    }

    public static Node[] getInputs() {
        return inputs;
    }
    public static Field[] getModelFields() {
        try {
            return new Field[] {
                    Product.class.getDeclaredField("make"), Product.class.getDeclaredField("model"),
                    Product.class.getDeclaredField("year"), //Product.class.getDeclaredField("product"),
                    Product.class.getDeclaredField("condition"), Product.class.getDeclaredField("cost"),
                    Product.class.getDeclaredField("description"), Product.class.getDeclaredField("serialNumber")
            };
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
