package Functionality.Forms;

import Functionality.Database.ReportsService;
import Styles.Charts;
import Utils.Notification;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class ReportsController {

    public static void refresh(){
        Charts.lineChart();
        Charts.barChart();
        Charts.pieChart();
    }


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
