package Functionality.Database.DB;

import Entities.Car;
import Entities.Category;
import Entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EntityConvertor {
    public static <T> List<T> convertToEntity(ResultSet rs, String entityName) throws SQLException {
        List<T> entities = new LinkedList<>();
        // TODO: 6/4/2023 add correct check and looping condition:
        //  does rs.next() ignore first value and should rs.isBeforeFirst() be used instead
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                switch (entityName.toLowerCase()) {
                    case "product" -> {
                        entities.add((T) convertToProduct(rs));
                    }
                    case "car" -> {
                        entities.add((T) convertToCar(rs));
                    }
                    case "category" -> {
                        entities.add((T) convertToCategory(rs));
                    }
                    case "string" -> {
                        entities.add((T) rs.getString(1));
                    }
                    case "integer" -> {
                        entities.add((T) Integer.valueOf((rs.getInt(1))));
                    }
                }
            }
        }
        return entities;
    }

    private static Product convertToProduct(ResultSet rs) throws SQLException {
        // TODO: 6/4/2023 update data types when Product class is updated
        String inventoryProductID = rs.getString("inventoryProductID");
        String carID = rs.getString("carID");
        String productID = rs.getString("productID");
        String serialNumber = rs.getString("serialNumber");
        int cost = rs.getInt("cost");
        String description = rs.getString("description");
        String condition = rs.getString("condition");

        return new Product(inventoryProductID, carID, productID, serialNumber, String.valueOf(cost), description, condition);
    }

    private static Car convertToCar(ResultSet rs) throws SQLException {
        String carID = rs.getString("carID");
        String manufacturerID = rs.getString("manufacturer_ID");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");

        return new Car(carID, manufacturerID, make, model, String.valueOf(year));
    }

    private static Category convertToCategory(ResultSet rs) throws SQLException {
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        String product = rs.getString("product");

        return new Category(make, model, String.valueOf(year), product);
    }
}
