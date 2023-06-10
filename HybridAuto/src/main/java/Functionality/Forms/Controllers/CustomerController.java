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

public class CustomerController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public CustomerController() {
        HashMap<String, InputControlWrapper> comboBoxes = new HashMap<>(
                Map.of(
                        "make", BaseController.getComboBox(makeList),
                        "model", BaseController.getComboBox(modelList),
                        "year", BaseController.getComboBox(yearList),
                        //todo customer and license aren't actually used tho
                        "customer", BaseController.getComboBox(null),
                        "license", BaseController.getComboBox(null)
                )
        );
        HashMap<String, InputControlWrapper> textFields = new HashMap<>(
                Map.of(
                        "name", BaseController.getTextField("Customer Name", 150, 40),
                        "phone", BaseController.getTextField("Phone No.", 150, 40)
                )
        );

        inputs = new HashMap<>();
        inputs.putAll(comboBoxes);
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
