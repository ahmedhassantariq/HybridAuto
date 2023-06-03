package Functionality.Forms.Controllers;

import Entities.Car;
import Entities.Category;
import Entities.Product;
import Functionality.Database.CarService;
import Functionality.Database.InventoryService;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
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
                            .getItems().setAll(CarService.getAllModelsOfMake(s));
                });
    }

    @Override
    public T create() {
        Car c = CarService
                .getCar(inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                        inputs.get("year").getStringInput())
                .get(0);
        Category prodType = InventoryService
                .getCategory(inputs.get("type").getStringInput(), inputs.get("condition").getStringInput());
        int ipid = InventoryService.getMaxInventoryProductID()+1;

        Product p = new Product(
                String.valueOf(ipid), c.getCarID(), prodType.getProduct(),
                inputs.get("serialno").getStringInput(), inputs.get("cost").getStringInput(),
                inputs.get("description").getStringInput(), inputs.get("condition").getStringInput()
        );
        InventoryService.addProduct(p);

        return (T) p;
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
}
