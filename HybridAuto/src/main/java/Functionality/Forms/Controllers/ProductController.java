package Functionality.Forms.Controllers;

import Entities.Car;
import Entities.Product;
import Entities.Stock;
import Functionality.Database.Services.CarService;
import Functionality.Database.Services.CategoryService;
import Functionality.Database.Services.ProductService;
import Functionality.Database.Services.StockService;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.util.*;

public class ProductController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public ProductController() {
        HashMap<String, InputControlWrapper> comboBoxes = new HashMap<>(
                Map.of(
                        "make", BaseController.getComboBox(makeList),
                        "model", BaseController.getComboBox(modelList),
                        "year", BaseController.getComboBox(yearList),
                        "condition", BaseController.getComboBox(conditionList),
                        "type", BaseController.getComboBox(productList)
                )
        );
        HashMap<String, InputControlWrapper> textFields = new HashMap<>(
                Map.of(
                        "cost", BaseController.getTextField("Cost", 100, 40),
                        "description", BaseController.getTextField("Description", 300, 40),
                        "serialno", BaseController.getTextField("SerialNo.", 300, 40)
                )
        );

        inputs = new HashMap<>();
        inputs.putAll(comboBoxes);
        inputs.putAll(textFields);

        addUIFunctionality();
    }

    private void addUIFunctionality() {
        inputs.get("make").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, s, t1) -> {
                    inputs.get("model").<MFXFilterComboBox<String>>getInputControl()
                            .getItems().setAll(CarService.searchAllModelsWIthMake(s));
                });
        inputs.get("model").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, s, t1) -> {
                    inputs.get("year").<MFXFilterComboBox<String>>getInputControl()
                            .getItems().setAll(CarService.searchAllYearsWithModel(s));
                });
        inputs.get("year").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, s, t1) -> {
                    BaseController.productList.setAll(ProductService.searchAllProductsOfMakeModelYear(
                            inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                            inputs.get("year").getStringInput()
                            //todo convert product to string/get name
                    ).stream().map(p -> p.toString()).toList());
                });
    }

    @Override
    public void create() {
        Car c = CarService.searchCar(inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                inputs.get("year").getStringInput());
        int ipid = ProductService.searchMaxInventoryProductId()+1;

//        Product p = new Product(
//                String.valueOf(ipid), c.getCarID(), inputs.get("type").getStringInput(), inputs.get("serialno").getStringInput(),
//                inputs.get("cost").getStringInput(), inputs.get("description").getStringInput(), inputs.get("condition").getStringInput()
//        );
        Stock s = new Stock(
                null,
                inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                inputs.get("year").getStringInput(), inputs.get("type").getStringInput(),
                inputs.get("serialno").getStringInput(), inputs.get("cost").getStringInput(),
                inputs.get("description").getStringInput(), inputs.get("condition").getStringInput()
        );
        StockService.addProduct(s);

        //can add it here, instead of Product Form
//        inventoryList.add(s);
    }

    @Override
    public T read() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public AbstractMap<String, InputControlWrapper> getInputMap() {
        return inputs;
    }

    public void clearLists() {
        makeList.clear();
        modelList.clear();
        yearList.clear();
        conditionList.clear();
        productList.clear();
    }

    public void setInventoryList() {
        BaseController.inventoryList.setAll(ProductService.searchAllProducts());
    }

    static public Product search(String serialNumber) {
        return ProductService.searchProduct(serialNumber);
    }
}
