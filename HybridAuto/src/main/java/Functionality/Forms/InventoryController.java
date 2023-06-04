package Functionality.Forms;

import Entities.Car;
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
    public static ObservableList<Car> carList = FXCollections.observableArrayList();
    public static ObservableList<String> makeComboList = FXCollections.observableArrayList();
    public static ObservableList<String> modelComboList = FXCollections.observableArrayList();
    public static ObservableList<String> yearComboList = FXCollections.observableArrayList();
    public static ObservableList<String> productComboList = FXCollections.observableArrayList();

    //Populates InventoryTable ObservableList from DB
    public static void setInventoryList(){
        inventoryList.clear();
        try {
            InventoryService.getInventoryProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList getInventoryList() {
        inventoryList.clear();
        try {
            InventoryService.getInventoryProducts();
            return inventoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Populates makeComboBox ObservableList from DB
    public static void setMakeComboList() {
        makeComboList.clear();
        try {
            InventoryService.getMakeList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Populates modelComboBox ObservableList from DB
    public static void setModelComboList(String make){
        modelComboList.clear();
        try {
            InventoryService.getModelList(make);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Populates yearComboBox ObservableList from DB
    public static void setYearComboList(String model){
        yearComboList.clear();
        try {
            InventoryService.getYearList(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Populates productComboBox ObservableList from DB
    public static void setProductComboList() {
        try {
            InventoryService.getProductList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Insert product into DB from New Product Form
    public static void insertProduct(String make,String model,String year,String type,String condition,String cost, String serial,String description) {
        try{
        String carID = InventoryService.getCarID(make,model,year);
            String inventoryProductID = String.valueOf(InventoryService.getNewInventoryProductID());
            Product product = new Product(inventoryProductID, carID, type, serial, cost, description, condition);
            InventoryService.insertInventoryProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProduct(String inventoryProductID,String make,String model,String year,String type,String condition,String cost, String serial,String description){
        try{
        String carID = InventoryService.getCarID(make,model,year);
        Product product = new Product(inventoryProductID,carID,type,serial,cost,description,condition);

            InventoryService.updateInventoryProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteProduct(String inventoryProductID) throws SQLException {
        try {
            InventoryService.deleteInventoryProduct(inventoryProductID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car getCar(String carID) {
        try {
            return InventoryService.getCar(carID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchText(Object make,Object model,Object year,Object serial) {
        inventoryList.clear();
        try {
            String m="",mo="",y="",s="";
            if(make!=null)
                m=make.toString();
            if(model!=null)
                mo=model.toString();
            if(year!=null)
                y=year.toString();
            if(serial!=null)
                s=serial.toString();
            InventoryService.searchText(m,mo,y,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
