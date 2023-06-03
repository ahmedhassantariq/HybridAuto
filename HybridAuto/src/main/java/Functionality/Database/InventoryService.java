package Functionality.Database;

import Entities.Car;
import Entities.Category;
import Entities.Product;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.InventoryController;
import Utils.Constants;
import io.github.palexdev.mfxcore.filter.base.AbstractFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.kordamp.ikonli.prestashopicons.PrestaShopIcons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;

public class InventoryService {
    private static ResultSet resultSet;


    //Populates Inventory Main Table
    public static void getInventoryProducts() throws SQLException {
        resultSet = null;
        String queryString = "select * from inventory where display = 1";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            InventoryController.inventoryList.add(new Product(
                    resultSet.getString("inventory_product_ID"),
                    resultSet.getString("car_ID"),
                    resultSet.getString("product_ID"),
                    resultSet.getString("serial_number"),
                    resultSet.getString("cost"),
                    resultSet.getString("description"),
                    resultSet.getString("condition")
            ));
        }
    }

    public static void getMakeList() throws SQLException {
        resultSet = null;
        String queryString = "select * from manufacturer";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            InventoryController.makeComboList.add(resultSet.getString("manufacturer_ID"));
        }

    }
    public static void getModelList(String make)throws SQLException {
        resultSet = null;
        String queryString = "select car_model from car where car_make = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, make);

        resultSet = pSt.executeQuery();
        String model = null;
        while (resultSet.next()) {
            model = resultSet.getString("car_model");
            if (!InventoryController.modelComboList.contains(model)) {
                InventoryController.modelComboList.add(resultSet.getString("car_model"));
            }
        }
    }

        public static void getYearList(String model)throws SQLException{
            resultSet = null;
            String queryString = "select model_year from car where car_model = ?";
            PreparedStatement pSt = DbConnection.getPrepared(queryString);
            pSt.setString(1,model);

            resultSet = pSt.executeQuery();
            String year = null;
            while(resultSet.next()) {
                year = resultSet.getString("model_year");
                if(!InventoryController.yearComboList.contains(year)) {
                    InventoryController.yearComboList.add(resultSet.getString("model_year"));
                }
            }
    }

    public static void getProductList()throws SQLException{
        resultSet = null;
        String queryString = "select * from product";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        String product = null;
        while(resultSet.next()) {
            product = resultSet.getString("product_ID");
            if(!InventoryController.productComboList.contains(product))
                InventoryController.productComboList.add(resultSet.getString("product_ID"));
        }
    }
    public static String getCarID(String make,String model,String year) throws SQLException {
        resultSet = null;
        String carID = null;
        String queryString = "select car_ID from car where car_make = ? and car_model = ? and model_year = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,make);
        pSt.setString(2,model);
        pSt.setString(3,year);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            carID = resultSet.getString("car_ID");
        }
        return carID;
    }

    public static int getNewInventoryProductID() throws SQLException {
        resultSet = null;
        int inventoryProductID = 0;
        String queryString = "select count(*) from inventory";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            inventoryProductID = resultSet.getInt(1);
        }
        return ++inventoryProductID;
    }

    public static void insertInventoryProduct(Product product) throws SQLException {
        resultSet = null;
        String queryString = "insert into inventory([inventory_product_ID],[car_ID],[product_ID],[serial_number],[cost],[description],[created_datetime],[condition],[display])values" +
                "(?,?,?,?,?,?,GETDATE(),?,'1')";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        System.out.println(product.getInventoryProductID());
        pSt.setString(1, product.getInventoryProductID());
        pSt.setString(2, product.getCarID());
        pSt.setString(3, product.getProductID());
        pSt.setString(4, product.getSerialNumber());
        pSt.setString(5, product.getCost());
        pSt.setString(6, product.getDescription());
        pSt.setString(7, product.getCondition());
        pSt.execute();
        System.out.println("Product Inserted");
    }

    public static void updateInventoryProduct(Product product) throws SQLException {
        resultSet = null;
        String queryString = "UPDATE [dbo].[inventory] SET [car_ID] = ? ,[product_ID] = ?,[serial_number] = ?,[cost] = ?,[description] = ?,[condition] = ? WHERE [inventory_product_ID]=?";

        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, product.getCarID());
        pSt.setString(2, product.getProductID());
        pSt.setString(3, product.getSerialNumber());
        pSt.setString(4, product.getCost());
        pSt.setString(5, product.getDescription());
        pSt.setString(6, product.getCondition());
        pSt.setString(7, product.getInventoryProductID());
        pSt.execute();
        System.out.println("Product Updated");

    }

    public static void deleteInventoryProduct(String inventoryProductID) throws SQLException {
        String queryString = "update inventory set display = 0 where inventory_product_ID = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, inventoryProductID);
        pSt.execute();
        System.out.println("Product Deleted");

    }


    public static Car getCar(String carID) throws SQLException {
        resultSet = null;
        Car car = null;
        String queryString = "select * from car where car_ID = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,carID);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            car = new Car(
                    resultSet.getInt("car_ID"),
                    resultSet.getString("car_make"),
                    resultSet.getString("car_model"),
                    resultSet.getString("model_year")
            );
        }
        return car;
    }






}
