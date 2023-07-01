package Functionality.Forms;

import Entities.Services;
import Entities.Stock;
import Functionality.Database.ServicesService;
import Screens.OrderDetailScreen;
import Screens.ServicesForm;
import Utils.Formatter;
import Utils.Notification;
import Utils.OrderDetailsTable;
import Utils.ServiceTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ServicesController {
    public static ObservableList<Services> servicesList = FXCollections.observableArrayList();
    public static ObservableList<Stock> orderDetailList = FXCollections.observableArrayList();


    public static void getServicesList(){
        try{
            ServicesService.getOrders();
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static void searchOrder(Services services){
        try{
            ServicesService.searchOrders(services);
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<Stock> getOrderDetails(String orderID){
        try {
            orderDetailList.clear();
            ServicesService.getOrderDetails(orderID);
            return orderDetailList;
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static String getOrderTotal(String orderID){
        try {
            return ServicesService.getOrderTotal(orderID);
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void returnOrder(Services services){

        try {
            for (int i=0;i<orderDetailList.size();i++){
                ServicesService.returnProduct(Integer.parseInt(orderDetailList.get(i).getStockID()));
            }
            ServicesService.returnOrder(services);
            OrderDetailScreen.stage.close();
            getServicesList();
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }

    }

}
