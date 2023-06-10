package Functionality.Database.DB;

import Entities.Car;
import Entities.Category;
import Entities.Product;
import Entities.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EntityConvertor {
    public static <T> List<T> convertToEntity(ResultSet rs, String entityName) throws SQLException {
        List<T> entities = new LinkedList<>();
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
                    case "stock" -> {
                        entities.add((T) convertToStock(rs));
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
        String inventoryProductID = rs.getString("inventoryProductID");
        String carID = rs.getString("carID");
        String productID = rs.getString("productID");
        String serialNumber = rs.getString("serialNumber");
        int cost = rs.getInt("cost");
        String description = rs.getString("description");
        String condition = rs.getString("condition");
        return null;
//        return new Product(inventoryProductID, carID, productID, serialNumber, String.valueOf(cost), description, condition);
    }

    private static Car convertToCar(ResultSet rs) throws SQLException {
        String carID = rs.getString("carID");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");

        return new Car(carID, make, model, String.valueOf(year));
    }

    private static Category convertToCategory(ResultSet rs) throws SQLException {
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        String product = rs.getString("product");

        return new Category(make, model, String.valueOf(year), product);
    }

    private static Stock convertToStock(ResultSet rs) throws SQLException {
        String stockID = rs.getString("stockId");
        String carMake = rs.getString("make");
        String carModel= rs.getString("model");
        String carYear= rs.getString("year");
        String productCategory= rs.getString("product");
        String serialNumber= rs.getString("serialnumber");
        String cost= rs.getString("cost");
        String description= rs.getString("description");
        String condition= rs.getString("condition");
        return new Stock(stockID, carMake, carModel, carYear, productCategory, serialNumber, cost, description, condition);
    }
}
