package Functionality.Database;

import Entities.Services;
import Entities.Stock;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.InventoryController;
import Functionality.Forms.ServicesController;

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
}
