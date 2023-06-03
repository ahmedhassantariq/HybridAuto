package Functionality.Forms.OldControllerStuff;

import Entities.Category;
import Entities.Product;
import Functionality.Database.CarService;
import Functionality.Database.InventoryService;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;

public class InventoryController {
    private BaseController<Category> categoryController;
    private BaseController<Product> productController;
    private static InventoryController inventoryControllerInstance;

    private InventoryController() {
    }

    public static InventoryController getInstance() {
        if (inventoryControllerInstance == null)
            inventoryControllerInstance = new InventoryController();
        return inventoryControllerInstance;
    }

    public InventoryController initProductController(Field[] prodFields, Node[] inputNodes) {
        if (productController == null) {
            productController = new BaseController<>(prodFields, inputNodes) {
            };
            populateProductOptions(inputNodes);
        }
        return this;
    }

    public InventoryController initCategoryController(Field[] categFields, Node[] inputNodes) {
        if (categoryController == null) {
            categoryController = new BaseController<>(categFields, inputNodes);
            populateCategoryOptions(inputNodes);
        }
        return this;
    }

    private void populateProductOptions(Node[] inputs) {
        for (Node n : inputs) {
            if (n instanceof MFXComboBox<?> cbx) {
                MFXComboBox<String> cb = (MFXComboBox<String>) cbx;
                String what = cb.getPromptText().toLowerCase();
                switch (what) {
                    case "make" -> {
                        cb.getItems().addAll("What", "Is", "A", "Make");
                    }
                    case "model" -> {
                        cb.getItems().addAll("What", "Color", "Is", "Your", "Bugatti");
                    }
                    case "year" -> {
                        cb.getItems().addAll("2023", "2022", "2021", "2020");
                    }
                    case "condition" -> {
                        cb.getItems().addAll("Sigma", "Alpha", "Beta", "Gamma", "Zeta", "Eta", "Theta", "Omega");
                    }
                    case "product" -> {
                        cb.getItems().addAll("What", "Goes", "Here?");
                    }
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
            if (n instanceof MFXComboBox<?> cbx) {
                MFXComboBox<String> cb = (MFXComboBox<String>) cbx;
                String what = cb.getPromptText().toLowerCase();
                switch (what) {
                    case "make" -> {
                        cb.getItems().addAll("What", "Is", "A", "Make");
                    }
                    case "model" -> {
                        cb.getItems().addAll("What", "Color", "Is", "Your", "Bugatti");
                    }
                    case "year" -> {
                        cb.getItems().addAll("2023", "2022", "2021", "2020");
                    }
                    case "condition" -> {
                        cb.getItems().addAll("Sigma", "Alpha", "Beta", "Gamma", "Zeta", "Eta", "Theta", "Omega");
                    }
                    case "product" -> {
                        cb.getItems().addAll("What", "Goes", "Here?");
                    }
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

    public boolean addNewProduct() {
//        String cid = CarService.getCar(makeComboBox.getText(), modelComboBox.getText(), yearComboBox.getText()).get(0).getCarID();
//        String pid = InventoryService.getCategory(typeComboBox.getText(), conditionComboBox.getText()).getProduct();
//
//        TextField ipidTF = new TextField();
//        TextField cidTF = new TextField();
//        TextField pidTF = new TextField();
//
//        ipidTF.setText(String.valueOf(InventoryService.getMaxInventoryProductID() + 1));
//        cidTF.setText(cid);
//        pidTF.setText(pid);
//
//        try {
//            Product newProduct = getProductController().createModel(
//                    Product.dummy(),
//                    new Field[]{
//                            Product.class.getDeclaredField("inventoryProductID"),
//                            Product.class.getDeclaredField("carID"),
//                            Product.class.getDeclaredField("productID")
//                    },
//                    new Node[]{cidTF, pidTF}
//            );
//            InventoryService.addProduct(newProduct);
//        } catch (NoSuchFieldException ex) {
//            throw new RuntimeException(ex);
//        }
        return false;
    }
    public boolean addNewCategory() {
        return false;
    }


    public static ObservableList<Product> inventoryList = FXCollections.observableArrayList();


}
