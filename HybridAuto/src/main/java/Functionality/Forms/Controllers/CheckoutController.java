package Functionality.Forms.Controllers;

import Entities.Car;
import Entities.Stock;
import Functionality.Database.Services.CarService;
import Functionality.Database.Services.ProductService;
import Functionality.Database.Services.StockService;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CheckoutController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public CheckoutController() {
        HashMap<String, InputControlWrapper> textFields = new HashMap<>(
                Map.of(
                        "discount", BaseController.getTextField("Discount %", 150, 40),
                        "discount amount", BaseController.getTextField("Discount Amount", 150, 40)
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

}