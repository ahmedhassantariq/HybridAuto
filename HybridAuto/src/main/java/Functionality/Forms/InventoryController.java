package Functionality.Forms;

import Entities.Car;
import Entities.Stock;
import Functionality.Database.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class InventoryController {

    public static ObservableList<Stock> inventoryList = FXCollections.observableArrayList();
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
    public static void setProductComboList(String make,String model,String year ) {
        productComboList.clear();
        try {
            InventoryService.getProductList(make,model,year);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Insert product into DB from New Product Form
    public static void insertProduct(Stock stock) {
        try{
            InventoryService.insertInventoryProduct(stock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProduct(String stockID,String make,String model,String year,String type,String condition,String cost, String serial,String comments){
        try{
        Stock stock = new Stock(stockID,make,model,year,type,serial,cost,comments,condition);
            InventoryService.updateInventoryProduct(stock);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteProduct(String stockID) throws SQLException {
        try {
            InventoryService.deleteInventoryProduct(stockID);
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

    public static void insertCategory(Stock category){
        try{
            if(InventoryService.getCarID(category.getMake(),category.getModel(),category.getYear())==null) {
                InventoryService.insertCar(category.getMake(),category.getModel(),category.getYear());
                String carID = InventoryService.getCarID(category.getMake(),category.getModel(),category.getYear());
                InventoryService.insertCategory(carID,category);
            }else {
                String carID = InventoryService.getCarID(category.getMake(),category.getModel(),category.getYear());
                InventoryService.insertCategory(carID,category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
