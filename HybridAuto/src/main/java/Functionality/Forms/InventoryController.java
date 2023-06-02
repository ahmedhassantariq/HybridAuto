package Functionality.Forms;

import Entities.Category;
import Entities.Product;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Field;

public class InventoryController {
    private BaseController<Category> categoryController;
    private BaseController<Product> productController;
    private static InventoryController inventoryControllerInstance;

    private InventoryController() {
    }

    public static InventoryController getInstance() {
        if(inventoryControllerInstance == null)
            inventoryControllerInstance = new InventoryController();
        return inventoryControllerInstance;
    }

    public InventoryController initProductController(Field[] prodFields, Node[] inputNodes) {
            if(productController == null) {
                productController = new BaseController<>(prodFields, inputNodes);
                populateProductOptions(inputNodes);
            }
        return this;
    }

    public InventoryController initCategoryController(Field[] categFields, Node[] inputNodes) {
        if(categoryController == null) {
            categoryController = new BaseController<>(categFields, inputNodes);
            populateCategoryOptions(inputNodes);
        }
        return this;
    }

    private void populateProductOptions(Node[] inputs) {
        for (Node n : inputs) {
            if(n instanceof MFXComboBox<?> cbx) {
                MFXComboBox<String> cb = (MFXComboBox<String>) cbx;
                String what = cb.getPromptText().toLowerCase();
                switch (what) {
                    case "make" -> { cb.getItems().addAll("What", "Is", "A", "Make"); }
                    case "model" -> { cb.getItems().addAll("What", "Color", "Is", "Your", "Bugatti"); }
                    case "year" -> { cb.getItems().addAll("2023", "2022", "2021", "2020"); }
                    case "condition" -> { cb.getItems().addAll("Sigma", "Alpha", "Beta", "Gamma", "Zeta", "Eta", "Theta", "Omega"); }
                    case "product" -> { cb.getItems().addAll("What", "Goes", "Here?"); }
                }
//                switch (what) {
//                    case "make" -> { cb.getItems().addAll(CarService.getAllMakeDistinct()); }
//                    case "model" -> { cb.getItems().addAll(CarService.getAllModelsDistinct()); }
//                    case "year" -> { cb.getItems().addAll("2023", "2022", "2021", "2020"); }
//                    case "condition" -> { cb.getItems().addAll(InventoryService.getAllConditionsDistinct()); }
//                    case "product" -> { cb.getItems().addAll(InventoryService.getAllProductTypesDistinct()); }
//                }
            }
        }
    }
    private void populateCategoryOptions(Node[] inputs) {
        for (Node n : inputs) {
            if(n instanceof MFXComboBox<?> cbx) {
                MFXComboBox<String> cb = (MFXComboBox<String>) cbx;
                String what = cb.getPromptText().toLowerCase();
                switch (what) {
                    case "make" -> { cb.getItems().addAll("What", "Is", "A", "Make"); }
                    case "model" -> { cb.getItems().addAll("What", "Color", "Is", "Your", "Bugatti"); }
                    case "year" -> { cb.getItems().addAll("2023", "2022", "2021", "2020"); }
                    case "condition" -> { cb.getItems().addAll("Sigma", "Alpha", "Beta", "Gamma", "Zeta", "Eta", "Theta", "Omega"); }
                    case "product" -> { cb.getItems().addAll("What", "Goes", "Here?"); }
                }
//                switch (what) {
//                    case "make" -> { cb.getItems().addAll(CarService.getAllMakeDistinct()); }
//                    case "model" -> { cb.getItems().addAll(CarService.getAllModelsDistinct()); }
//                    case "year" -> { cb.getItems().addAll("2023", "2022", "2021", "2020"); }
//                    case "condition" -> { cb.getItems().addAll(InventoryService.getAllConditionsDistinct()); }
//                    case "product" -> { cb.getItems().addAll(InventoryService.getAllProductTypesDistinct()); }
//                }
            }
        }
    }

    public BaseController<Category> getCategoryController() {
        return categoryController;
    }

    public BaseController<Product> getProductController() {
        return productController;
    }
}
