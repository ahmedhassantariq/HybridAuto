package Functionality.Database.DB;

import Functionality.Database.DB_Backup.ConnectionWrapper;

import java.sql.*;

public class DatabaseConnector {
//    private static final String dbUrl="jdbc:sqlserver:/DESKTOP-DCA2IJR:1433;database=hybrid_autotest;encrypt=false;integratedSecurity=true;";

    private static final String dbUrl="jdbc:sqlserver://"
        + "localhost"
        + ":1433;DatabaseName="
        + "hybrid_autotest"
        + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
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
