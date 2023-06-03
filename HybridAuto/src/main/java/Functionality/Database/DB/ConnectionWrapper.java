package Functionality.Database.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
    private Connection connection;
    private final String dbUrl;

    public ConnectionWrapper(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() throws SQLException {
        if(!isConnected())
            connection = DriverManager.getConnection(dbUrl);
    }

    public void closeConnection() throws SQLException {
        if(isConnected()) {
            connection.close();
            connection = null;
        }
    }

    public PreparedStatement getPreparedStatementFrom(String query) throws SQLException {
        if(!isConnected())
            connect();

        return connection.prepareStatement(query);
    }
}
