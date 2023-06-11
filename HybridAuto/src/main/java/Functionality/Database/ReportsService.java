package Functionality.Database;

import Functionality.Database.DB.DbConnection;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Styles.Charts.*;

public class ReportsService {
    private static ResultSet resultSet;

    public static void getBillsData() throws SQLException {
        lineChartSeries.getData().clear();
        resultSet = null;
        String queryString = "select * from getMonthlyBills";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            lineChartSeries.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
        }
    }

    public static void getDailyOrders() throws SQLException {
        barChartSeries.getData().clear();
        resultSet = null;
        String queryString = "select * from getDailyOrders";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            barChartSeries.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
        }
    }
    public static void getStockVsSold() throws SQLException {
        resultSet = null;
        String queryString = "select * from getStockVsSold";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            pieChart.getData().addAll(
                    new PieChart.Data("Stock",resultSet.getInt(1)),
                    new PieChart.Data("Sold",resultSet.getInt(2))
            );

        }
    }
}
