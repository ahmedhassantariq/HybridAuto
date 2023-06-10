package Functionality.Forms.Controllers;

import Entities.Category;
import Entities.Stock;
import Functionality.Database.Services.CarService;
import Functionality.Database.Services.CategoryService;
import Functionality.Database.Services.StockService;
import Styles.Fields;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CategoryController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public CategoryController() {
        HashMap<String, InputControlWrapper> textFields = new HashMap<>(
                Map.of(
                        "make", BaseController.getTextField("Make",100,40),
                        "model", BaseController.getTextField("Model",100,40),
                        "year", BaseController.getTextField("Year",100,40),
                        "category", BaseController.getTextField("Product",100,40)
                )
        );

        inputs = new HashMap<>();
        inputs.putAll(textFields);

        addUIFunctionality();
    }


    private void addUIFunctionality() {
        inputs.get("make").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, s, t1) -> {
                    inputs.get("model").<MFXFilterComboBox<String>>getInputControl()
                            .getItems().setAll(CarService.searchAllModelsWIthMake(s));
                });
    }

    @Override
    public void create() {
        Stock s = new Stock(null,
                inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                inputs.get("year").getStringInput(), inputs.get("category").getStringInput(),
                null,null,null,null);

        StockService.addProduct(s);
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

