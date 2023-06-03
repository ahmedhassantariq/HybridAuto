package Functionality.Database.DB_Backup;

import Entities.Product;
import Functionality.Forms.OldControllerStuff.InventoryController;

import java.sql.*;

public class DbConnection {
    private static final String dbUrl="jdbc:sqlserver:/DESKTOP-919RBUB:1433;database=hybrid_autotech;encrypt=false;integratedSecurity=true;";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final ConnectionWrapper connWrapper;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName(driver);
            connWrapper = new ConnectionWrapper(dbUrl);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private DbConnection() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        connection = DriverManager.getConnection(dbUrl);
    }

    public static void connectDB() {
        try {
            if(!connWrapper.isConnected())
                connWrapper.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeDB() {
        if (connWrapper.isConnected()) {
            try {
                connWrapper.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static PreparedStatement getPrepared(String query) {
        PreparedStatement st;
        try {
            st = connWrapper.getPreparedStatementFrom(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return st;
    }




    public static void getInventoryProducts() throws SQLException {
        resultSet = null;
        String queryString = "select * from inventory";
        PreparedStatement preparedStatement = connWrapper.getPreparedStatementFrom(queryString);
//        Constants.tableData.clear();
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            InventoryController.inventoryList.add(new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
    }



}
