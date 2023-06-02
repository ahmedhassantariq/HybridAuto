package Entities;

import java.util.Objects;

public class Car {
    private final String carID;
    private final String manufacturerID;
    private final String make;
    private final String model;
    private final String year;

    public Car(String carID, String manufacturerID, String make, String model, String year) {
        this.carID = carID;
        this.manufacturerID = manufacturerID;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getCarID() {
        return carID;
    }

    public String getManufacturerID() {
        return manufacturerID;
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

    @Override
    public int hashCode() {
        return Objects.hash(getCarID());
    }
}
