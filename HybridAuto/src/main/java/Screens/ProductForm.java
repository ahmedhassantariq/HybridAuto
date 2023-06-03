package Screens;

import Entities.Product;
import Functionality.Forms.Controllers.ProductController;
import Functionality.Forms.OldControllerStuff.InventoryController;
import Styles.Buttons;
import Styles.Fields;
import Styles.Labels;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.Stylesheets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static Functionality.Forms.Controllers.BaseController.makeList;
import static Functionality.Forms.Controllers.BaseController.modelList;

public class ProductForm {
    private static VBox productForm;
    private static final ProductController<Product> productController = new ProductController<>();

    public static VBox productForm(){
        if(productForm != null)
            return productForm;

        MFXFilterComboBox<String> makeComboBox = productController.getInputMap().get("make").getInputControl();
        makeComboBox.setOnAction(e-> System.out.println("Cliked"));
        makeComboBox.setFloatingText("Make");

        MFXFilterComboBox<String> modelComboBox = productController.getInputMap().get("model").getInputControl();
        modelComboBox.setFloatingText("Model");

        MFXFilterComboBox<String> yearComboBox = productController.getInputMap().get("year").getInputControl();
        yearComboBox.setFloatingText("Year");

        MFXFilterComboBox<String> conditionComboBox = productController.getInputMap().get("condition").getInputControl();
        conditionComboBox.setFloatingText("Condition");

        MFXFilterComboBox<String> typeComboBox = productController.getInputMap().get("type").getInputControl();
        typeComboBox.setFloatingText("Product");

        HBox comboBoxContainer = new HBox(makeComboBox,modelComboBox,yearComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setPadding(new Insets(10));
        comboBoxContainer.setSpacing(10);



        MFXTextField costField = productController.getInputMap().get("cost").getInputControl();



        MFXTextField descriptionField = productController.getInputMap().get("description").getInputControl();
        HBox costCond = new HBox(typeComboBox,conditionComboBox,costField);
        costCond.setAlignment(Pos.CENTER);
        costCond.setPadding(new Insets(10));
        costCond.setSpacing(10);


        MFXTextField serialField = productController.getInputMap().get("serialno").getInputControl();

        MFXButton addButton = Buttons.FunctionButton("Add",100,40);
        MFXButton resetButton = Buttons.FunctionButton_Border("Reset",100,40);

        resetButton.setOnAction(e->{
            makeComboBox.clear();
            modelComboBox.clear();
            yearComboBox.clear();
            typeComboBox.clear();
            conditionComboBox.clear();
            costField.clear();
            descriptionField.clear();
        });

        addButton.setOnAction(e->{
            // TODO: 6/4/2023 have a global class which has all controllers, then statically call this method
            new ProductController<Product>().create();


            serialField.clear();
                });
        serialField.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.ENTER)

                serialField.clear();
        });



        HBox buttonBox = new HBox(addButton,resetButton);
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

        productForm = productBox;
        return productBox;

    }
}
