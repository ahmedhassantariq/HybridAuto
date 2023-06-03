package Functionality.Database.DB;

import Functionality.Database.DB_Backup.ConnectionWrapper;

import java.sql.*;

public class DatabaseConnector {
    private static final String dbUrl="jdbc:sqlserver:/DESKTOP-919RBUB:1433;database=hybrid_autotech;encrypt=false;integratedSecurity=true;";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;
    private static ResultSet resultSet;

    public static boolean isConnected() {
        return connection != null;
    }

    public static void connect() throws SQLException {
        if(!isConnected())
            connection = DriverManager.getConnection(dbUrl);
    }

    public static void closeConnection() throws SQLException {
        if(isConnected()) {
            connection.close();
            connection = null;
        }
    }

    public static PreparedStatement getPrepared(String query) throws SQLException {
        if(!isConnected())
            connect();
        return connection.prepareStatement(query);
    }
}
