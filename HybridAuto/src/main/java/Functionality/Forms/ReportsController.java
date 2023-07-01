package Functionality.Forms;

import Functionality.Database.ReportsService;
import Utils.Notification;

import java.sql.SQLException;

public class ReportsController {


    public static void getBillsData(){
        try {
            ReportsService.getBillsData();
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static void getDailyOrders(){
        try {
            ReportsService.getDailyOrders();
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static void getStockVsSold(){
        try {
            ReportsService.getStockVsSold();
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
}
