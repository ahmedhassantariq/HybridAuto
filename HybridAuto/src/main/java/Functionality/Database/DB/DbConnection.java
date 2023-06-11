package Functionality.Database.DB;

import Entities.Services;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
    private static final String dbUrl="jdbc:sqlserver://DESKTOP-919RBUB:1433;database=hybrid_FINAL_DB;encrypt=false;integratedSecurity=true;";
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












    public static PreparedStatement searchOrder(Services services) throws SQLException {
        PreparedStatement st = connWrapper.getPreparedStatementFrom(prepareOrderQuery(services));
        setServiceParams(st,services);
        return st;
    }

    public static String prepareOrderQuery(Services services) {
        StringBuilder builder = new StringBuilder("select od.order_ID,c.first_Name,c.middle_Name,c.last_Name,c.phone,od.created_datetime from [Order] od " +
                "inner join Customer c on " +
                "od.Customer_ID = c.Customer_ID where ");
        if (ObjectUtils.isNotEmpty(services.getNameProperty())) {
            builder.append(" c.first_Name like CONCAT('%',?,'%')");
        }
        if (ObjectUtils.isNotEmpty(services.getPhone())) {
            builder.append(" AND c.phone like CONCAT('%',?,'%')");
        }
        if (ObjectUtils.isNotEmpty(services.getOrderDate())) {
            builder.append(" AND od.created_datetime like CONCAT('%',?,'%')");
        }
        return builder.toString();
    }

    public static PreparedStatement setServiceParams(PreparedStatement st,Services services) throws SQLException {
        int index = 1;
        if (ObjectUtils.isNotEmpty(services.getNameProperty())) {
            st.setString(index, services.getName());
            index += 1;
        }
        if (ObjectUtils.isNotEmpty(services.getPhone())) {
            st.setString(index, services.getPhone());
            index += 1;
        }
        if (ObjectUtils.isNotEmpty(services.getOrderDate())) {
            st.setString(index, services.getOrderDate());
            index += 1;
        }
        return st;
    }







}
