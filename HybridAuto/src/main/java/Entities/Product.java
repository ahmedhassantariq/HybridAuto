package Entities;

import java.util.Objects;

public class Product {
    private final String inventoryProductID;
    private final String carID;
    private final String productID;
    private final String serialNumber;
    private final int cost;
    private final String description;
    private final String condition;


    public Product(String inventoryProductID, String carID, String productID, String serialNumber, int cost, String description, String condition) {
        this.inventoryProductID = inventoryProductID;
        this.carID = carID;
        this.productID = productID;
        this.serialNumber = serialNumber;
        this.cost = cost;
        this.description = description;
        this.condition = condition;
    }

    public String getInventoryProductID() {
        return inventoryProductID;
    }

    public String getCarID() {
        return carID;
    }

    public String getProductID() {
        return productID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getCondition() {
        return condition;
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
