package Functionality.Database;

import Entities.Customer;
import Entities.Order;
import Entities.OrderDetail;
import Entities.Stock;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.InventoryController;
import Screens.CheckOutForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersService {
    private static ResultSet resultSet;


    public static void insertCustomer(Customer customer) throws SQLException {
        resultSet = null;
        String queryString = "insert into Customer values (?,?,?,?,?,?)";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, customer.getPhone());
        pSt.setString(2, customer.getFirstName());
        pSt.setString(3, customer.getMiddleName());
        pSt.setString(4, customer.getLastName());
        pSt.setString(5, customer.getAreaCode());
        pSt.setString(6,customer.getAddress());
        pSt.execute();
    }

    public static Customer getCustomer(String phone) throws SQLException {
            resultSet = null;
            Customer customer = null;
            String queryString = "select * from Customer where phone = ?";
            PreparedStatement pSt = DbConnection.getPrepared(queryString);
            pSt.setString(1,phone);
            resultSet = pSt.executeQuery();
            while(resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("customer_ID"),resultSet.getString("first_Name"),resultSet.getString("middle_Name"),
                        resultSet.getString("last_Name"),resultSet.getString("phone"),resultSet.getString("areaCode"),
                        resultSet.getString("address")
                );
            }
            return customer;
        }


    public static void insertOrder(Order order) throws SQLException {
        resultSet = null;
        String queryString = "insert into [Order] values (?,?,?,GETDATE())";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1,order.getOrderID());
        pSt.setString(2, order.getCustomerID());
        pSt.setString(3, String.valueOf(CheckOutForm.totalAmount));
        pSt.execute();
    }

    public static Order getOrder(int customerID) throws SQLException {
        resultSet = null;
        Order order = null;
        String queryString = "select * from Customer where phone = ?";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setInt(1,customerID);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            order = new Order(resultSet.getString("orderID"),
                    resultSet.getString("customer_ID"),
                    resultSet.getDate("created_datetime")
            );
        }
        return order;
    }


    public static int getNewOrderID() throws SQLException {
        resultSet = null;
        int newID = 0;
        String queryString = "select COUNT(*) as new_ID from [Order]";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
           newID = resultSet.getInt("new_ID");
        }
        return ++newID;
    }

    public static void insertOrderDetails(OrderDetail orderDetail) throws SQLException {
        resultSet = null;
        String queryString = "insert into Order_Details values(?,?)";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setInt(1,orderDetail.getOrderID());
        pSt.setInt(2, orderDetail.getStockID());
        pSt.execute();
    }




}
