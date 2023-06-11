package Styles;

import Functionality.Forms.ReportsController;
import javafx.scene.chart.*;

public class Charts {
    public static XYChart.Series<String, Number> lineChartSeries = new XYChart.Series<>();
    public static XYChart.Series<String, Number> barChartSeries = new XYChart.Series<>();
    public static PieChart pieChart = new PieChart();

    public static BarChart barChart() {
        ReportsController.getDailyOrders();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Daily Orders");
        barChart.getData().add(barChartSeries);
        barChart.setMaxSize(300,300);
        return barChart;
    }


    public static PieChart pieChart() {
        ReportsController.getStockVsSold();
        pieChart.setTitle("Products");
        return pieChart;
    }

    public static LineChart lineChart() {
        ReportsController.getBillsData();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Monthly Bills");
        lineChartSeries.setName("");
        lineChart.getData().add(lineChartSeries);
        lineChart.setMaxSize(300,300);
        return lineChart;
    }



}
