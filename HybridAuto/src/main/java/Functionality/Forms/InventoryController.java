package Functionality.Forms;

import Entities.Category;
import Entities.Product;
import Functionality.Database.InventoryService;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class InventoryController {

    public static ObservableList<Product> inventoryList = FXCollections.observableArrayList();
    public static ObservableList<String> makeComboList = FXCollections.observableArrayList();
    public static ObservableList<String> modelComboList = FXCollections.observableArrayList();
    public static ObservableList<String> yearComboList = FXCollections.observableArrayList();
    public static ObservableList<String> productComboList = FXCollections.observableArrayList();

    //Populates InventoryTable ObservableList from DB
    public static void setInventoryList() throws SQLException {
        inventoryList.clear();
        InventoryService.getInventoryProducts();
    }
    public static ObservableList getInventoryList() throws SQLException {
        inventoryList.clear();
        InventoryService.getInventoryProducts();
        return inventoryList;
    }

    //Populates makeComboBox ObservableList from DB
    public static void setMakeComboList() throws SQLException {
        makeComboList.clear();
        InventoryService.getMakeList();
    }
    //Populates modelComboBox ObservableList from DB
    public static void setModelComboList(String make) throws SQLException {
        modelComboList.clear();
        InventoryService.getModelList(make);
    }

    //Populates yearComboBox ObservableList from DB
    public static void setYearComboList(String model) throws SQLException {
        yearComboList.clear();
        InventoryService.getYearList(model);
    }

    //Populates productComboBox ObservableList from DB
    public static void setProductComboList() throws SQLException {
        InventoryService.getProductList();
    }


    public static void insertProduct(String make,String model,String year,String type,String condition,String cost, String serial,String description) throws SQLException {
        String carID = InventoryService.getCarID(make,model,year);
        String inventoryProductID = String.valueOf(InventoryService.getNewInventoryProductID());
        Product product = new Product(inventoryProductID,carID,type,serial,cost,description,condition);
        InventoryService.insertInventoryProduct(product);
    }


}
