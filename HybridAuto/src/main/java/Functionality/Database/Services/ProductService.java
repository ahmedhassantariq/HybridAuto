package Functionality.Database.Services;

import Entities.Product;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
//    public static boolean addProduct(Product p) {
//        try {
//            return DatabaseQueryExecutor.executeInsert(
//                    DatabaseQueries.INSERT_QUERIES.INSERT_PRODUCT,
//                    p.getCarID(), p.getProductID(), p.getSerialNumber(), p.getCost(), p.getDescription(),
//                    "GETDATE()", p.getCondition(), String.valueOf(1)
//                    );-
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static boolean updateProduct(Product p) {
//        try {
//            return DatabaseQueryExecutor.executeUpdate(
//                    DatabaseQueries.UPDATE_QUERIES.UPDATE_PRODUCT,
//                    p.getCarID(), p.getProductID(), p.getSerialNumber(), p.getCost(), p.getDescription(), p.getCondition()
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static boolean deleteProduct(String productId) {
        try {
            return DatabaseQueryExecutor.executeDelete(
                    DatabaseQueries.DELETE_QUERIES.DELETE_PRODUCT,
                    productId
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Product searchProduct(String productSerial) {
        try {
            return (Product) DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_PRODUCT_WITH_SERIAL,
                    "product",
                    productSerial
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> searchAllProducts() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_PRODUCTS, "product"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllConditions() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_PRODUCT_CONDITION,
                    "string"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int searchMaxInventoryProductId() {
        try {
            return (int) DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.AGGREGATES.MAX.GET_MAX_INVENTORY_PRODUCT_ID,
                    "integer"
            ).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int searchCountOfProducts() {
        try {
            return (int) DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.AGGREGATES.COUNT.GET_COUNT_INVENTORY_PRODUCT_ID,
                    "integer"
            ).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> searchAllProductsOfMakeModelYear(String make, String model, String year) {
        try {
            return DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_PRODUCTS_WITH_MAKE_MODEL_YEAR,
                    "product", make, model, year
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
