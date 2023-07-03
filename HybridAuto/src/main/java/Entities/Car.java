package Entities;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Car {
    private final SimpleStringProperty carID;
    private final SimpleStringProperty make;
    private final SimpleStringProperty model;
    private final SimpleStringProperty year;

    public Car(String carID, String make, String model, String year) {
        this.carID = new SimpleStringProperty (carID);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleStringProperty(year);
    }

    public String getCarID() {
        return carID.get();
    }

    public SimpleStringProperty carIDProperty() {
        return carID;
    }

    public String getMake() {
        return make.get();
    }

    public SimpleStringProperty makeProperty() {
        return make;
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public String getYear() {
        return year.get();
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public SimpleStringProperty CarIDProperty() {
        return carID;
    }

    public SimpleStringProperty getMakeProperty() {
        return make;
    }

    public SimpleStringProperty getModelProperty() {
        return model;
    }

    public SimpleStringProperty getYearProperty() {
        return year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarID());
    }
}
