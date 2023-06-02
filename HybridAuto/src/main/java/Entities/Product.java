package Entities;

public class Product {
    private final String inventoryProductID;
    private final String productID;
    private final String make;
    private final String model;
    private final int year;
    private final String condition;
    private final int cost;
    private final String description;
    private final String serialNumber;
    private final boolean display;

    public Product(String inventoryProductID,String productID, String make, String model, int year, String condition, int cost, String description, String serialNumber, boolean display) {
        this.inventoryProductID = inventoryProductID;
        this.productID = productID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.condition = condition;
        this.cost = cost;
        this.description = description;
        this.serialNumber = serialNumber;
        this.display = display;
    }

    public String getInventoryProductID() {
        return inventoryProductID;
    }
    public String getProductID() {
        return productID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getCondition() {
        return condition;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isDisplay() {
        return display;
    }

    public Object getInventoryProductID(Product product) {
        return inventoryProductID;
    }

    public Object getPID(Product product) {
        return productID;
    }
    public String getMake(Product product) {
        return make;
    }
    public Object getModel(Product product) {
        return model;
    }
    public Object getYear(Product product) {
        return year;
    }
    public Object getProduct(Product product) {
        return product;
    }
    public Object getCondition(Product product) {
        return condition;
    }
    public Object getCost(Product product) {
        return cost;
    }
    public Object getSerial(Product product) {
        return serialNumber;
    }
    public Object getDescription(Product product) {
        return description;
    }
    public Object getDisplay(Product product) {
        return display;
    }
}
