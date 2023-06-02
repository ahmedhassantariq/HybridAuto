package Entities;

public class Category {
    private final String categoryID;
    private final String carID; // TODO: 6/2/2023 confirm category and other tables attributes
    private final String type;
    private final String condition;

    public Category(String categoryID, String carID, String type, String condition) {
        this.categoryID = categoryID;
        this.carID = carID;
        this.type = type;
        this.condition = condition;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCarID() {
        return carID;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }
}
