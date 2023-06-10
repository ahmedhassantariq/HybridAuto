package Functionality.Forms.Controllers;

import Entities.Category;
import Functionality.Database.InventoryService;
import Functionality.Database.Services.CarService;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CategoryController<T> extends BaseController<T> {
    private final HashMap<String, InputControlWrapper> inputs;

    public CategoryController() {
        MFXFilterComboBox<String> makeComboBox = new MFXFilterComboBox<>();
        makeComboBox.setText("Make");

        MFXFilterComboBox<String> modelComboBox = new MFXFilterComboBox<>();
        modelComboBox.setText("Model");

        MFXFilterComboBox<String> yearComboBox = new MFXFilterComboBox<>();
        yearComboBox.setText("Year");

        MFXFilterComboBox<String> conditionComboBox = new MFXFilterComboBox<>();
        conditionComboBox.setText("Condition");

        MFXFilterComboBox<String> typeComboBox = new MFXFilterComboBox<>();
        typeComboBox.setText("Product");
        HashMap<String, InputControlWrapper> comboBoxes = new HashMap<>(
                Map.of(
                        "make", BaseController.getComboBox(makeList),
                        "model", BaseController.getComboBox(modelList),
                        "year", BaseController.getComboBox(yearList),
                        "condition", BaseController.getComboBox(conditionList),
                        "type", BaseController.getComboBox(productList)
                )
        );

        inputs = new HashMap<>();
        inputs.putAll(comboBoxes);

        addUIFunctionality();
    }


    private void addUIFunctionality() {
        inputs.get("make").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    inputs.get("model").<MFXFilterComboBox<String>>getInputControl()
                            .getItems().setAll(Functionality.Database.Services.CarService.searchAllModelsWIthMake(newValue));
                });
        inputs.get("model").<MFXFilterComboBox<String>>getInputControl().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    inputs.get("year").<MFXFilterComboBox<String>>getInputControl()
                            .getItems().setAll(CarService.searchAllYearsWithMakeModel(
                                    inputs.get("make").getStringInput(),
                                    newValue
                            ));
                });
    }

    @Override
    public void create() {
        Category c = new Category(
                inputs.get("make").getStringInput(), inputs.get("model").getStringInput(),
                inputs.get("year").getStringInput(), inputs.get("type").getStringInput()
        );
        InventoryService.addCategory(c);
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

