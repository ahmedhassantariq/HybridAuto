package Functionality.Database;

import Entities.Car;
import Entities.Stock;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.InventoryController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryService {
    private static ResultSet resultSet;

    //Populates Inventory Main Table
    public static void getInventoryProducts() throws SQLException {
        resultSet = null;
        String queryString = "select * from stock_view";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            InventoryController.inventoryList.add(new Stock(
                    resultSet.getString("stock_ID"),
                    resultSet.getString("car_make"),
                    resultSet.getString("car_model"),
                    resultSet.getString("car_year"),
                    resultSet.getString("product_Category"),
                    resultSet.getString("serial_number"),
                    resultSet.getString("cost"),
                    resultSet.getString("comments"),
                    resultSet.getString("condition")
            ));
        }
    }

    public static void getMakeList() throws SQLException {
        resultSet = null;
        String queryString = "select car_make from Car";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while (resultSet.next()) {
            if (!InventoryController.makeComboList.contains(resultSet.getString("car_make"))) {
                InventoryController.makeComboList.add(resultSet.getString("car_make"));
            }
        }

    }
    public static void getModelList(String make)throws SQLException {
        resultSet = null;
        String queryString = "select car_model from car where car_make = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, make);

        resultSet = pSt.executeQuery();
        while (resultSet.next()) {
            if (!InventoryController.modelComboList.contains(resultSet.getString("car_model"))) {
                InventoryController.modelComboList.add(resultSet.getString("car_model"));
            }
        }
    }

        public static void getYearList(String model)throws SQLException{
            resultSet = null;
            String queryString = "select car_year from car where car_model = ?";
            PreparedStatement pSt = DbConnection.getPrepared(queryString);
            pSt.setString(1,model);

            resultSet = pSt.executeQuery();
            while(resultSet.next()) {
                if(!InventoryController.yearComboList.contains(resultSet.getString("car_year"))) {
                    InventoryController.yearComboList.add(resultSet.getString("car_year"));
                }
            }
    }

    public static void getProductList(String make,String model,String year)throws SQLException{
        resultSet = null;
        String queryString = "select c.car_make,c.car_model,c.car_year,p.product_Category from Product p " +
                "inner join Car c on " +
                "p.car_ID = c.car_ID where c.car_make = ? AND c.car_model = ? AND c.car_year = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,make);
        pSt.setString(2,model);
        pSt.setString(3,year);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            if(!InventoryController.productComboList.contains(resultSet.getString("product_Category")))
                InventoryController.productComboList.add(resultSet.getString("product_Category"));
        }
    }
    public static String getCarID(String make,String model,String year) throws SQLException {
        resultSet = null;
        String carID = null;
        String queryString = "select car_ID from Car where car_make = ? and car_model = ? and car_year = ?";
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

    public static String getProductID(String make,String model,String year,String type) throws SQLException {
        resultSet = null;
        String carID = null;
        String queryString = "select * from Product p " +
                "inner join Car c on " +
                "p.car_ID = c.car_ID where c.car_make = ? AND c.car_model = ? AND " +
                "c.car_year = ? AND p.product_Category = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,make);
        pSt.setString(2,model);
        pSt.setString(3,year);
        pSt.setString(4,type);
        resultSet = pSt.executeQuery();
        while(resultSet.next())
        {
            carID = resultSet.getString("product_ID");
        }
        return carID;
    }






    public static void insertInventoryProduct(Stock stock) throws SQLException {
        resultSet = null;
        String productID = getProductID(stock.getMake(),stock.getModel(),stock.getYear(), stock.getProductCategory());
        String queryString = "insert into stock values(?, ? , ? , ? ,GetDate(), ? ,'1')";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, productID);
        pSt.setString(2, stock.getSerialNumber());
        pSt.setString(3, stock.getCost());
        pSt.setString(4, stock.getComments());
        pSt.setString(5, stock.getCondition());
        pSt.execute();
    }

    public static void updateInventoryProduct(Stock stock) throws SQLException {
        resultSet = null;
        String queryString = "UPDATE [dbo].[stock] SET [product_ID] = ? , [serial_number] = ? ,[cost] = ? ,[comments] = ? ,[condition] = ? WHERE stock_ID = ? ";
        String productID = getProductID(stock.getMake(),stock.getModel(),stock.getYear(), stock.getProductCategory());
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, productID);
        pSt.setString(2, stock.getSerialNumber());
        pSt.setString(3, stock.getCost());
        pSt.setString(4, stock.getComments());
        pSt.setString(5, stock.getCondition());
        pSt.setString(6, stock.getStockID());
        pSt.execute();
    }

    public static void deleteInventoryProduct(String stockID) throws SQLException {
        String queryString = "update stock set display = 0 where stock_ID = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, stockID);
        pSt.execute();
    }



    public static void searchText(String make,String model,String year,String serial) throws SQLException {
        resultSet = null;
        resultSet = DbConnection.search(make,model,year,serial).executeQuery();
        while(resultSet.next()) {
            InventoryController.inventoryList.add(new Stock(
                    resultSet.getString("stock_ID"),
                    resultSet.getString("car_make"),
                    resultSet.getString("car_model"),
                    resultSet.getString("car_year"),
                    resultSet.getString("product_Category"),
                    resultSet.getString("serial_number"),
                    resultSet.getString("cost"),
                    resultSet.getString("comments"),
                    resultSet.getString("condition")
            ));
        }
    }

    public static void insertCar(String make,String model,String year) throws SQLException {
        resultSet = null;
        String queryString = "insert into Car values(?,?,?)";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, make);
        pSt.setString(2, model);
        pSt.setString(3, year);
        pSt.execute();
    }
    public static void insertCategory(String carID, Stock category) throws SQLException {
        resultSet = null;
        String queryString = "insert into Product values(?,?)";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, category.getProductCategory());
        pSt.setString(2, carID);
        pSt.execute();
    }






}
