package Functionality.Database;

import Entities.Car;
import Entities.Category;
import Entities.Product;
import io.github.palexdev.mfxcore.filter.base.AbstractFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;

public class InventoryService {
    public static boolean addCategory(String make, String model, String year,
                                      String type, String condition) {
//        try{
            Car car = CarService.getCar(make, model, year);
            if(car == null) return false;
            String queryStr = String.format("insert into category" +
                        "values(%s, %s, %s)",
                car.getCarID(), type, condition);
        return DatabaseService.executeQuery(queryStr);
//        }catch (SQLException ex) {
//
//        }
    }
//    public static boolean addProduct(Product p) {
//        //todo deal with product type in addProduct()-> plus set price into product table price is fixed now...User required changed
//        return addProduct(p.getMake(), p.getModel(), String.valueOf(p.getYear()), "", p.getCondition(), p.getDescription(), p.getSerialNumber());
//    }
    private static boolean addProduct(String make, String model, String year, String type, String condition, String desc, String serial) {
//        try{
            Car car = CarService.getCar(make, model, year);
            Category category = getCategory(type, condition);

            if(car == null || category == null) return false;

        // TODO: 6/2/2023 update add product query /// other queries also
            String queryStr = String.format("insert into products(make, model, year, condition, description, serial)" +
                            "values(%s, %s, %s, %s)", // TODO: 6/2/2023 category.GetCategoryID()
                    car.getCarID(), "category.getCategoryID()", desc, serial);
            return DatabaseService.executeQuery(queryStr);
//        }catch (SQLException ex) {
//        }
    }
    public static boolean deleteProduct(String pID) {
        //        try{
        String queryStr = String.format("delete from products where products.id = %s", pID);
        return DatabaseService.executeQuery(queryStr);
//        }catch (SQLException ex) {
//        }
    }
    public static Product searchProduct(String productSerial) {
        try{
        String queryStr = String.format("select * from products p where p.serial = %d", Integer.valueOf(productSerial));
        return DatabaseService.<Product>executeScalar(queryStr).orElseThrow(
                () -> new SQLException(String.format("Product with Serial %07d Does Not Exist", Integer.valueOf(productSerial)))
        );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static Category getCategory(String type, String condition) {
        try{
            String queryStr = "select * from category c where";

            if(!type.isBlank()) {
                queryStr += String.format(" c.type = %s", type);
            }
            if(!condition.isBlank()) {
                if(queryStr.endsWith("where"))
                    queryStr += " ";
                else queryStr += " AND ";
                queryStr += String.format("c.condition = %s", condition);
            }

            return DatabaseService.<Category>executeScalar(queryStr).orElseThrow(
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
            String queryStr = "select * from products";
            return DatabaseService.<Product>executeInline(queryStr).orElseThrow(
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

    public static List<String> getAllMakeDistinct() {
        try{
            String queryStr = "select distinct p.make from products p";
            return DatabaseService.executeInlineStr(queryStr).orElseThrow(
                    () -> new SQLException("No Make Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<String> getAllModelsDistinct() {
        try{
            String queryStr = "select distinct p.model from products p";
            return DatabaseService.executeInlineStr(queryStr).orElseThrow(
                    () -> new SQLException("No Model Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<String> getAllConditionsDistinct() {
        try{
            String queryStr = "select distinct p.condition from products p";
            return DatabaseService.executeInlineStr(queryStr).orElseThrow(
                    () -> new SQLException("No Condition Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<String> getAllProductTypesDistinct() {
        try{
            String queryStr = "select distinct p.type from products p";
            return DatabaseService.executeInlineStr(queryStr).orElseThrow(
                    () -> new SQLException("No Type Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
}
