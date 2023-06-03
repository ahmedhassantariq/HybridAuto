package Functionality.Forms.Controllers;

import Entities.Product;
import Functionality.Database.Services.CarService;
import Functionality.Database.Services.CategoryService;
import Functionality.Database.Services.ProductService;
import Styles.Fields;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

// TODO: 6/4/2023 auto fill at Make + use obs list
// TODO: 6/4/2023 static block that queries database to populate these obs lists here
public abstract class BaseController<T> {
    public static final ObservableList<Product> inventoryList = FXCollections.observableArrayList();
    public static final ObservableList<String> makeList = FXCollections.observableArrayList(
            "Toyota","Honda","Nissan","Hyundai","Daihatsu");
    public static final ObservableList<String> modelList = FXCollections.observableArrayList(
            "Corolla","Prius","Aqua","Vigo","ZX","V8"
    );
    public static final ObservableList<String> yearList = FXCollections.observableArrayList();

    public static final ObservableList<String> conditionList = FXCollections.observableArrayList(
            "New","Used"
    );
    public static final ObservableList<String> productList = FXCollections.observableArrayList(
            "ABS","Battery"
    );


    //populate lists from DB
    static {
        inventoryList.setAll(ProductService.searchAllProducts());
        makeList.setAll(CarService.searchAllMakes());
        modelList.setAll(CarService.searchAllModels());
        yearList.setAll(CarService.searchAllYears());
        productList.setAll(CategoryService.searchAllCategory());
    }


    public abstract void create();
    public abstract T read();
    public abstract boolean update();
    public abstract boolean delete();
    public abstract <U> Map<String, InputControlWrapper> getInputMap();


    public static  <U> InputControlWrapper getComboBox(ObservableList<U> list) {
        MFXFilterComboBox<U> cbx = new MFXFilterComboBox<>(list);
        return new InputControlWrapper(cbx);
    }
    public static InputControlWrapper getTextField(String fieldLabel, double width, double height) {
        MFXTextField mtf = Fields.textField(fieldLabel, width, height);
        return new InputControlWrapper(mtf);
    }

}
