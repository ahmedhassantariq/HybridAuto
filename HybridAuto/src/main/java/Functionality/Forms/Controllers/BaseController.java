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

import java.sql.SQLException;
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


    // TODO: 6/5/2023 Adapted From Ahmed Branch

    //Populates InventoryTable ObservableList from DB
    public static void setInventoryList() throws SQLException {
        inventoryList.clear();
        ProductService.searchAllProducts();
    }
    public static ObservableList getInventoryList() throws SQLException {
        inventoryList.clear();
        ProductService.searchAllProducts();
        return inventoryList;
    }

    //Populates makeComboBox ObservableList from DB
    public static void setMakeComboList() throws SQLException {
        makeList.clear();
        CarService.searchAllMakes();
    }
    //Populates modelComboBox ObservableList from DB
    public static void setModelComboList(String make) throws SQLException {
        modelList.clear();
        CarService.searchAllModelsWIthMake(make);
    }

    //Populates yearComboBox ObservableList from DB
    public static void setYearComboList(String model) throws SQLException {
        yearList.clear();
        CarService.searchAllYearsWithModel(model);
    }

    //Populates productComboBox ObservableList from DB
    public static void setProductComboList() throws SQLException {
        ProductService.searchAllProducts();
    }
}
