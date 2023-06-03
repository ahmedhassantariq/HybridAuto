package Functionality.Database.Services;

import Entities.Category;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;

public class CategoryService {
    public static boolean addCategory(Category c) {
        try {
            return DatabaseQueryExecutor.executeInsert(
                    DatabaseQueries.INSERT_QUERIES.INSERT_CATEGORY,
                    c.getMake(), c.getModel(), c.getYear(), c.getProduct()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateCategory(Category c) {
        try {
            return DatabaseQueryExecutor.executeUpdate(
                    DatabaseQueries.UPDATE_QUERIES.UPDATE_CATEGORY,
                    c.getMake(), c.getModel(), c.getYear(), c.getProduct()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteCategory(String categoryId) {
        try {
            return DatabaseQueryExecutor.executeDelete(
                    DatabaseQueries.DELETE_QUERIES.DELETE_CATEGORY,
                    categoryId
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
