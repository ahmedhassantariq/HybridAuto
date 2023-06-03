package Functionality.Database.Services;

import Entities.Product;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;

public class ProductService {
    public static boolean addProduct(Product p) {
        try {
            return DatabaseQueryExecutor.executeInsert(
                    DatabaseQueries.INSERT_QUERIES.INSERT_PRODUCT,
                    p.getCarID(), p.getProductID(), p.getSerialNumber(), p.getCost(), p.getDescription(), p.getCondition()
                    );
        } catch (SQLException e) {
            // TODO: 6/4/2023 maybe display dialog explaining error, maybe do this in ProductController and not Service instead
            throw new RuntimeException(e);
        }
    }
    public static boolean updateProduct(Product p) {
        try {
            return DatabaseQueryExecutor.executeUpdate(
                    DatabaseQueries.UPDATE_QUERIES.UPDATE_PRODUCT,
                    p.getCarID(), p.getProductID(), p.getSerialNumber(), p.getCost(), p.getDescription(), p.getCondition()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
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
}
