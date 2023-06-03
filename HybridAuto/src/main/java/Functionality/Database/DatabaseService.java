package Functionality.Database;

import Entities.Car;
import Entities.Product;
import Functionality.Database.DB.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DatabaseService {
    public static PreparedStatement getPreparedFrom(String qry) {
        return DbConnection.getPrepared(qry);
    }
    public static boolean executeQueryDML(PreparedStatement pSt) throws SQLException {
        pSt.executeUpdate();
        return true;
    }

    public static <T> Optional<T> executeScalar(PreparedStatement pSt, Class<T> targetClass) {
        try {
            ResultSet rs = pSt.executeQuery();
            if(rs.isBeforeFirst()) {
                if (targetClass == Product.class) {
                    String inventoryProductID = rs.getString("inventoryProductID");
                    String carID = rs.getString("carID");
                    String productID = rs.getString("productID");
                    String serialNumber = rs.getString("serialNumber");
                    int cost = rs.getInt("cost");
                    String description = rs.getString("description");
                    String condition = rs.getString("condition");
//                    return (Optional<T>) Optional.of(
//                            new Product(inventoryProductID, carID, productID, serialNumber, cost, description, condition)
//                    );
                }
                else if (targetClass == Car.class) {
                    String carID = rs.getString("carID");
                    String manufacturerID = rs.getString("manufacturerID");
                    String make = rs.getString("make");
                    String model = rs.getString("model");
                    int year = rs.getInt("year");
                    return (Optional<T>) Optional.of(
                            new Car(carID, manufacturerID, make, model, String.valueOf(year))
                    );
                } // TODO: 6/2/2023 add other cases for all tables
            }
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public static <T> Optional<List<T>> executeInline(PreparedStatement pSt, Class<T> targetClass) {
        try {
            ResultSet rs = pSt.executeQuery();
            if(rs.isBeforeFirst()) {
                List<T> results = new LinkedList<>();
                while(!rs.next()) {
                    if (targetClass == Product.class) {
                        String inventoryProductID = rs.getString("inventoryProductID");
                        String carID = rs.getString("carID");
                        String productID = rs.getString("productID");
                        String serialNumber = rs.getString("serialNumber");
                        int cost = rs.getInt("cost");
                        String description = rs.getString("description");
                        String condition = rs.getString("condition");
//                        results.add(
//                                (T) new Product(inventoryProductID, carID, productID, serialNumber, cost, description, condition)
//                        );
                    } else if (targetClass == Car.class) {
                        String carID = rs.getString("carID");
                        String manufacturerID = rs.getString("manufacturerID");
                        String make = rs.getString("make");
                        String model = rs.getString("model");
                        int year = rs.getInt("year");
                        results.add(
                                (T) new Car(carID, manufacturerID, make, model, String.valueOf(year))
                        );
                    }
                } // TODO: 6/2/2023 add other cases for all tables
                return Optional.of(results);
            }
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<List<String>> getAllProductConditions() {
        PreparedStatement pSt = getPreparedFrom("SELECT DISTINCT p.condition FROM tbl_products p");
        return execStrQuery(pSt);
    }
    public static Optional<List<String>> getAllProductTypes() {
        PreparedStatement pSt = getPreparedFrom("SELECT DISTINCT p.type FROM tbl_products p");
        return execStrQuery(pSt);
    }
    public static Optional<List<String>> getAllCarMakes() {
        PreparedStatement pSt = getPreparedFrom("SELECT DISTINCT c.make FROM tbl_cars c");
        return execStrQuery(pSt);
    }
    public static Optional<List<String>> getAllCarModels() {
        PreparedStatement pSt = getPreparedFrom("SELECT DISTINCT c.model FROM tbl_cars c");
        return execStrQuery(pSt);
    }
    private static Optional<List<String>> execStrQuery(PreparedStatement pSt) {
        try {
            ResultSet rs = pSt.executeQuery();
            if(rs.next()) {
                List<String> results = new LinkedList<>();
                while(rs.next()) {
                    results.add(rs.getString(1));
                }
                return Optional.of(results);
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
