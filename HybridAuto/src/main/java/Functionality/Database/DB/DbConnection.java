package Functionality.Database.DB;

import Entities.Product;
import Functionality.Forms.InventoryController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DbConnection {
    private static final String dbUrl="jdbc:sqlserver://DESKTOP-919RBUB:1433;database=hybrid_autotech;encrypt=false;integratedSecurity=true;";
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








}
