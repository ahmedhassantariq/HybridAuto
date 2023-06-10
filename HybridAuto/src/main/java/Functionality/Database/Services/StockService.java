package Functionality.Database.Services;

import Entities.Product;
import Entities.Stock;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;
import java.util.List;

public class StockService {
    public static boolean addProduct(Stock s) {
        try {
            return DatabaseQueryExecutor.executeInsert(
                    DatabaseQueries.INSERT_QUERIES.INSERT_PRODUCT,
                    s.getCarID(), s.getStockID(), s.getSerialNumber(), s.getCost(), s.getComments(),
                    "GETDATE()", s.getCondition(), String.valueOf(1)
            );
        } catch (SQLException e) {
            // TODO: 6/4/2023 maybe display dialog explaining error, maybe do this in ProductController and not Service instead
            throw new RuntimeException(e);
        }
    }
    public static boolean updateProduct(Stock s) {
        try {
            return DatabaseQueryExecutor.executeUpdate(
                    DatabaseQueries.UPDATE_QUERIES.UPDATE_PRODUCT,
                    s.getCarID(), s.getStockID(), s.getSerialNumber(), s.getCost(), s.getComments(), s.getCondition()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteProduct(String stockId) {
        try {
            return DatabaseQueryExecutor.executeDelete(
                    DatabaseQueries.DELETE_QUERIES.DELETE_PRODUCT,
                    stockId
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
}

