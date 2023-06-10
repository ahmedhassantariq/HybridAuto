package Entities;

import javafx.beans.property.SimpleStringProperty;

public class Product {

    private final SimpleStringProperty stockID;
    private final SimpleStringProperty productCategory;
    private final SimpleStringProperty serialNumber;
    private final SimpleStringProperty cost;
    private final SimpleStringProperty comments;
    private final SimpleStringProperty condition;


    public Product(String stockID, String carMake,String carModel, String carYear, String productCategory, String serialNumber, String cost, String description, String condition) {
        this.stockID = new SimpleStringProperty(stockID);
        this.productCategory = new SimpleStringProperty(productCategory);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.cost = new SimpleStringProperty(cost);
        this.comments = new SimpleStringProperty(description);
        this.condition = new SimpleStringProperty(condition);
    }
}
