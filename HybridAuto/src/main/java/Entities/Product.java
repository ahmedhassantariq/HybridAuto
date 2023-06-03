package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Product {
    private final SimpleStringProperty inventoryProductID;
    private final SimpleStringProperty carID;
    private final SimpleStringProperty productID;
    private final SimpleStringProperty serialNumber;
    private final SimpleStringProperty cost;
    private final SimpleStringProperty description;
    private final SimpleStringProperty condition;


    public Product(String inventoryProductID, String carID, String productID, String serialNumber, String cost, String description, String condition) {
        this.inventoryProductID = new SimpleStringProperty(inventoryProductID);
        this.carID = new SimpleStringProperty(carID);
        this.productID = new SimpleStringProperty(productID);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.cost = new SimpleStringProperty(cost);
        this.description = new SimpleStringProperty(description);
        this.condition = new SimpleStringProperty(condition);
    }

    public String getInventoryProductID() {
        return inventoryProductID.get();
    }

    public SimpleStringProperty inventoryProductIDProperty() {
        return inventoryProductID;
    }

    public void setInventoryProductID(String inventoryProductID) {
        this.inventoryProductID.set(inventoryProductID);
    }

    public String getCarID() {
        return carID.get();
    }

    public SimpleStringProperty carIDProperty() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID.set(carID);
    }

    public String getProductID() {
        return productID.get();
    }

    public SimpleStringProperty productIDProperty() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID.set(productID);
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

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
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
        return Objects.hash(getProductID());
    }

    public Object getProductID(Product product) {
        return productID;
    }

    public Object getCondition(Product product) {
        return condition;
    }

    public Object getCarID(Product product) {
        return carID;
    }

}
