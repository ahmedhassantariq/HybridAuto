package Functionality.Forms;

import Functionality.Database.DashboardService;
import Utils.Notification;

import java.sql.SQLException;

public class DashboardController {

    public static double totalSales(){
        try {
            return Double.parseDouble(DashboardService.dashBoardQueries("select * from total_order_sum"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static int totalCustomers(){
        try {
            return Integer.parseInt(DashboardService.dashBoardQueries("select COUNT(*) from Customer"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static int totalStock(){
        try {
            return Integer.parseInt(DashboardService.dashBoardQueries("select COUNT(*) from stock where display = 1"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static int totalOrders(){
        try {
            return Integer.parseInt(DashboardService.dashBoardQueries("select COUNT(*) from [Order]"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static double totalBillsAmount(){
        try {
            return Double.parseDouble(DashboardService.dashBoardQueries("select SUM(amount) from Bills"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static double totalInventoryCost(){
        try {
            return Double.parseDouble(DashboardService.dashBoardQueries("select SUM(cost) from stock where display = 1"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static double profitLoss(){
        try {
            return Double.parseDouble(DashboardService.dashBoardQueries(
                    "select * from total_order_sum"
            ))-Double.parseDouble(DashboardService.dashBoardQueries(
                    "select SUM(amount) from Bills"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }


    public static int totalItemsSold(){
        try {
            return Integer.parseInt(DashboardService.dashBoardQueries("select count(*) from Order_Details"));
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
}
