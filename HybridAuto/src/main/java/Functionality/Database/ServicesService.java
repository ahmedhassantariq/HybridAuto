package Functionality.Database;

import Entities.Services;
import Entities.Stock;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.ServicesController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesService {
    private static ResultSet resultSet;

    public static void getOrders() throws SQLException {
        ServicesController.servicesList.clear();
        resultSet = null;
        String queryString = "select od.order_ID,c.first_Name,c.middle_Name,c.last_Name,c.phone,od.created_datetime from [Order] od " +
                "inner join Customer c on " +
                "od.Customer_ID = c.Customer_ID";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            ServicesController.servicesList.add(new Services(
                    null,
                    resultSet.getString("first_Name"),
                    resultSet.getString("middle_Name"),
                    resultSet.getString("last_Name"),
                    resultSet.getString("phone"),
                    null,
                    null,
                    resultSet.getString("order_ID"),
                    resultSet.getString("created_datetime")
            ));
        }
    }


    public static void searchOrders(Services services) throws SQLException {
        ServicesController.servicesList.clear();
        resultSet = null;
        resultSet = DbConnection.searchOrder(services).executeQuery();
        while(resultSet.next()) {
            ServicesController.servicesList.add(new Services(
                    null,
                    resultSet.getString("first_Name"),
                    resultSet.getString("middle_Name"),
                    resultSet.getString("last_Name"),
                    resultSet.getString("phone"),
                    null,
                    null,
                    resultSet.getString("order_ID"),
                    resultSet.getString("created_datetime")
            ));
        }
    }

    public static void getOrderDetails(String orderID) throws SQLException {
        ServicesController.orderDetailList.clear();
        resultSet = null;
        String queryString = "exec showOrderDetails ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,orderID);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            ServicesController.orderDetailList.add(new Stock(
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

    public static String getOrderTotal(String orderID) throws SQLException {
        resultSet = null;
        String total = "";
        String queryString = "select total from [Order] where order_ID = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,orderID);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            total = resultSet.getString("total");
        }
        return total;
    }
}
