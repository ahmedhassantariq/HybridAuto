package Functionality.Database;

import Entities.Car;
import Entities.Category;
import Entities.Product;
import Functionality.Database.DB_Backup.DatabaseService;
import io.github.palexdev.mfxcore.filter.base.AbstractFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;

public class InventoryService {
    private static ResultSet resultSet;

    public static boolean addCategory(Category category) {
        return addCategory(category.getMake(), category.getModel(), category.getYear(), category.getProduct(), "");
    }
    public static boolean addCategory(String make, String model, String year,
                                      String type, String condition) {

        Car car = CarService.getCar(make, model, year).get(0);
        if (car == null) return false;

        PreparedStatement pSt = DatabaseService.getPreparedFrom("INSERT INTO tbl_category VALUES (?, ?, ?)");
        try {
            pSt.setString(1, car.getCarID());
            pSt.setString(2, type);
            pSt.setString(3, condition);

            return DatabaseService.executeQueryDML(pSt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean addProduct(Product p) {
        //todo deal with product type in addProduct()-> plus set price into product table price is fixed now...User required changed
        return addProduct(p.getCarID(), p.getProductID(), p.getSerialNumber(), Integer.parseInt(p.getCost()), p.getDescription(), p.getCondition());
    }
    public static boolean addProduct(String carID, String productID, String serial, int cost, String desc, String condition) {

//        Car car = CarService.getCar(carID);
//        Category category = getCategory(type, condition);

//        if (car == null || category == null) return false;

        // TODO: 6/2/2023 update add product query /// other queries also
//        String queryStr = String.format("insert into products(make, model, year, condition, description, serial)" +
//                        "values(%s, %s, %s, %s)", // TODO: 6/2/2023 category.GetCategoryID()
//                car.getCarID(), "category.getCategoryID()", desc, serial);

        PreparedStatement pSt = DatabaseService.getPreparedFrom(
                // assume product ID and inventory product ID are auto increment/auto generated
                "INSERT INTO tbl_products(carId, productId, serialNumber, cost, description, condition)" +
                        "values(?, ?, ?, ?, ?, ?)"
        );

        try {
            pSt.setString(1, carID);
            pSt.setString(2, productID);
            pSt.setInt(3, Integer.parseInt(serial));
            pSt.setInt(4, cost);
            pSt.setString(5, desc);
            pSt.setString(6, condition);

            return DatabaseService.executeQueryDML(pSt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteProduct(String pID) {
        PreparedStatement pSt = DatabaseService.getPreparedFrom("DELETE FROM tbl_product p WHERE p.pID = ?");
        try {
            pSt.setString(1, pID);

            return DatabaseService.executeQueryDML(pSt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Product searchProduct(int productSerial) {
        PreparedStatement pSt = DatabaseService.getPreparedFrom("SELECT * FROM tbl_products p where p.serial = ?");
        try {
            pSt.setInt(1, productSerial);

            return DatabaseService.executeScalar(pSt, Product.class).orElseThrow(
                    () -> new SQLException(String.format("Product with Serial %07d Does Not Exist", productSerial))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Category getCategory(String type, String condition) {
        try{
            PreparedStatement pSt = DatabaseService.getPreparedFrom("SELECT * FROM tbl_category c WHERE "
                    + (type.isBlank()? "" : " c.type = ? ")
                    + (condition.isBlank()? "" : ((type.isBlank()? "" : "AND") + " c.condition = ? ")));

            if(!type.isBlank())
                pSt.setString(1, type);

            if(!condition.isBlank()) {
                int idx = 1;
                if (!type.isBlank())
                    idx++;
                pSt.setString(idx, condition);
            }

            return DatabaseService.<Category>executeScalar(pSt, Category.class).orElseThrow(
                    () -> new SQLException(String.format("Category with %s%s Does Not Exist",
                            type.isBlank()? "" : "Type " + type,
                            condition.isBlank()? "" : " Condition " + condition
                    ))
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<Product> getAllProducts() {
        try{
            PreparedStatement pSt = DatabaseService.getPreparedFrom("SELECT * FROM tbl_products");
            return DatabaseService.<Product>executeInline(pSt, Product.class).orElseThrow(
                    () -> new SQLException("No Products Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }

    public static <T, U> ObservableList<Product> filterProductsList(ObservableList<Product> products,
                                                           ObservableList<AbstractFilter<T, U>> filters) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList(products);
        filteredList.forEach(p -> {
            boolean doesProductSatisfy = filters.stream().map(f -> {
                BiPredicate<U, U> allPreds = f.getPredicates().get(0).getPredicate();
                for (int i = 0; i < f.getPredicates().size(); i++) {
                    allPreds = allPreds.and(f.getPredicates().get(i).getPredicate());
                }
                return allPreds.test((U) p, null);
            }).reduce(Boolean.TRUE, (a, b) -> a && b);
            if(!doesProductSatisfy)
                filteredList.remove(p);
        });
        return filteredList;
    }
    public static List<String> getAllConditionsDistinct() {
        try{
            return DatabaseService.getAllProductConditions().orElseThrow(
                    () -> new SQLException("No Condition Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }

    public static List<String> getAllProductTypesDistinct() {
        try{
            return DatabaseService.getAllProductTypes().orElseThrow(
                    () -> new SQLException("No Type Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }

    public static int getMaxInventoryProductID() {
        PreparedStatement pSt =  DatabaseService.getPreparedFrom("SELECT MAX(inventoryProductID) from tbl_products");
        return DatabaseService.executeScalar(pSt, Integer.class).orElse(0);
    }

}
