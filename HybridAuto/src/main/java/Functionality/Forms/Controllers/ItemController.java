package Functionality.Forms.Controllers;

import Entities.Car;
import Entities.Product;
import Entities.Stock;
import Functionality.Database.Services.CarService;
import Functionality.Database.Services.ProductService;
import Functionality.Database.Services.StockService;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ItemController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public ItemController() {
        HashMap<String, InputControlWrapper> textFields = new HashMap<>(
                Map.of(
                        "serialno", BaseController.getTextField("SerialNo.", 300, 40)
                )
        );

        inputs = new HashMap<>();
        inputs.putAll(textFields);
    }

    @Override
    public void create() {
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

    static public Product search(String serialNumber) {
        return ProductService.searchProduct(serialNumber);
    }
}

