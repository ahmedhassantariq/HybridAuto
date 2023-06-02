package Entities;

public class Category {
    private final String make;
    private final String model;
    private final String year;
    private final String product;

    public Category(String make, String model, String year, String product) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.product = product;
    }

    public Category() {
        this("", "", "", "");
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getProduct() {
        return product;
    }

    public static Category getDummy() {
        return new Category("", "", "", "");
    }
}