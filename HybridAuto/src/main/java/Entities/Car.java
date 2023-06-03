package Entities;

import java.util.Objects;

public class Car {
    private final int carID;
    private final String make;
    private final String model;
    private final String year;

    public Car(int carID, String make, String model, String year) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public int getCarID() {
        return carID;
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
