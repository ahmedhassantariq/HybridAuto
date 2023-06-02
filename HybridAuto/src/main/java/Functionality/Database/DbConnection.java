package Functionality.Database;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DbConnection {
    private static final String dbUrl="jdbc:sqlserver://DESKTOP-919RBUB:1433;database=hybrid_autotech;encrypt=false;integratedSecurity=true;";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static Connection connection;
    private static ResultSet resultSet;

    public DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(dbUrl);
    }

    public static void callQuery(PreparedStatement preparedStatement ) throws SQLException {
        preparedStatement.execute();
    }
    public static void callUpdate(PreparedStatement preparedStatement ) throws SQLException {
        preparedStatement.executeUpdate();
    }

    //TODO revise the function to get data from database and insert accordingly
//    public static void displayTable(PreparedStatement preparedStatement) throws SQLException {
//        resultSet = null;
//        Constants.tableData.clear();
//        resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()) {
//            Constants.tableData.add(new Product(
//                    resultSet.getString(1),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    new Typ(resultSet.getString(2)),
//                    null,
//                    resultSet.getString(6),
//                    null,
//                    resultSet.getString(8),
//                    resultSet.getString(10)));
//        }
//    }
//
//    public static void displayInvoice(PreparedStatement preparedStatement) throws SQLException {
//        resultSet = null;
//        Constants.searchInvoiceData.clear();
//        resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()) {
//            Constants.searchInvoiceData.add(new SearchInvoice(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3).substring(0,8)));
//        }
//
//    }

//    public static String countQuery(PreparedStatement preparedStatement) throws SQLException {
//        resultSet = null;
//        Constants.tableData.clear();
//        resultSet = preparedStatement.executeQuery();
//
//        while(resultSet.next()) {
//            return resultSet.getString(1);
//        }
//        return resultSet.getString(1);
//    }

    public static void readPDF(PreparedStatement preparedStatement,String fileName) throws SQLException {
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            byte [] fileData = resultSet.getBytes("invoiceData");
            try (FileOutputStream fos = new FileOutputStream("src/main/java/Output/"+fileName)) {
                fos.write(fileData);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
