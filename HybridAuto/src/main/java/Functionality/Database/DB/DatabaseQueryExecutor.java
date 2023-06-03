package Functionality.Database.DB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseQueryExecutor {
    public static boolean executeInsert(String insertQuery, String... values) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(insertQuery);
        for (int i = 0; i < values.length; i++) {
            prep.setString(i+1, values[i]);
        }
        prep.executeUpdate();
        return true;
    }
    public static boolean executeUpdate(String updateQuery, String... values) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(updateQuery);
        for (int i = 0; i < values.length; i++) {
            prep.setString(i+1, values[i]);
        }
        prep.executeUpdate();
        return true;
    }
    public static boolean executeDelete(String deleteQuery, String pkValue) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(deleteQuery);
        prep.setString(1, pkValue);
        prep.executeUpdate();
        return true;
    }
}
