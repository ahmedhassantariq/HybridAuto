package Functionality.Database.DB_Backup;

import org.apache.commons.lang3.ObjectUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl="jdbc:sqlserver://DESKTOP-919RBUB:1433;database=hybrid_autoDB;encrypt=false;integratedSecurity=true;";
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


    public static PreparedStatement search(String make, String model, String year, String serial) throws SQLException {
        PreparedStatement st = connWrapper.getPreparedStatementFrom(prepareSearchQuery(make, model, year, serial));
        setSearchParams(st,make, model, year, serial);
        return st;
    }

    public static String prepareSearchQuery(String make, String model, String year, String serial) {
        StringBuilder builder = new StringBuilder("select * from stock s " +
                "inner join Product p on " +
                "s.product_ID = p.product_ID " +
                "inner join Car c on " +
                "p.car_ID = c.car_ID where display = 1 ");
        if (ObjectUtils.isNotEmpty(make)) {
            builder.append(" AND c.car_make like CONCAT('%',?,'%')");
        }
        if (ObjectUtils.isNotEmpty(model)) {
            builder.append(" AND c.car_model like CONCAT('%',?,'%')");
        }
        if (ObjectUtils.isNotEmpty(year)) {
            builder.append(" AND c.car_year like CONCAT('%',?,'%')");
        }
        if (ObjectUtils.isNotEmpty(serial)) {
            builder.append(" AND s.serial_number like CONCAT('%',?,'%')");
        }
        return builder.toString();
    }

    public static PreparedStatement setSearchParams(PreparedStatement st,String make, String model, String year, String serial) throws SQLException {
        int index = 1;
        if (ObjectUtils.isNotEmpty(make)) {
            st.setString(index, make);
            index += 1;
        }
        if (ObjectUtils.isNotEmpty(model)) {
            st.setString(index, model);
            index += 1;
        }
        if (ObjectUtils.isNotEmpty(year)) {
            st.setString(index, year);
            index += 1;
        }
        if (ObjectUtils.isNotEmpty(serial)) {
            st.setString(index, serial);
            index += 1;
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