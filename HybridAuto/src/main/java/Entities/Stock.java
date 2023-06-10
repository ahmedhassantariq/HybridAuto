package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Stock extends Car {
    private final SimpleIntegerProperty stockID;
    private final SimpleStringProperty productCategory;
    private final SimpleStringProperty serialNumber;
    private final SimpleStringProperty cost;
    private final SimpleStringProperty comments;
    private final SimpleStringProperty condition;


    public Stock( Integer stockID, String carMake,String carModel, String carYear, String productCategory, String serialNumber, String cost, String description, String condition) {
        super("",carMake,carModel,carYear);
        this.stockID = new SimpleIntegerProperty(stockID);
        this.productCategory = new SimpleStringProperty(productCategory);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.cost = new SimpleStringProperty(cost);
        this.comments = new SimpleStringProperty(description);
        this.condition = new SimpleStringProperty(condition);
    }

    public Integer getStockID() {
        return stockID.get();
    }

    public SimpleIntegerProperty stockIDProperty() {
        return stockID;
    }

    public void setStockID(Integer stockID) {
        this.stockID.set(stockID);
    }

    public String getProductCategory() {
        return productCategory.get();
    }

    public SimpleStringProperty productCategoryProperty() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory.set(productCategory);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public String getCost() {
        return cost.get();
    }

    public SimpleStringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getComments() {
        return comments.get();
    }

    public SimpleStringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public String getCondition() {
        return condition.get();
    }

    public SimpleStringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCategory());
    }

    public Object getProductID(Stock stock) {
        return productCategory;
    }

    public Object getCondition(Stock stock) {
        return condition;
    }

}
