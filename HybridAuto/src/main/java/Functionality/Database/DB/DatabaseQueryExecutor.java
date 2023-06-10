package Functionality.Database.DB;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DatabaseQueryExecutor {
    public static boolean executeInsert(String insertQuery, String... values) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(insertQuery);
        for (int i = 0; i < values.length; i++) {
            if(values[i].toLowerCase().contains("date")) {
                Date curDate = null;
                try(Statement toGetDate = prep.getConnection().createStatement()) {
                    ResultSet rs = toGetDate.executeQuery("select GETDATE() AS CurDate;");
                    if(rs.next())
                        curDate = rs.getDate("CurDate");
                }
                prep.setDate(i+1, curDate);
            }
            else
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

    public static <T> List<T> executeGet(String getQuery, String entityName) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(getQuery);
        ResultSet rs = prep.executeQuery();
        return EntityConvertor.convertToEntity(rs, entityName);
    }
    public static <T> List<T> executeGetWithCondition(String getQuery, String entityName, String... values) throws SQLException {
        PreparedStatement prep = DatabaseConnector.getPrepared(getQuery);
        for (int i = 0; i < values.length; i++) {
            prep.setString(i+1, values[i]);
        }
        ResultSet rs = prep.executeQuery();
        return EntityConvertor.convertToEntity(rs, entityName);
    }


}
