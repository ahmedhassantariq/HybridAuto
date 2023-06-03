package Functionality.Database.Services;

import Entities.Car;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;

public class CarService {
    public static boolean addCar(Car c) {
        try {
            return DatabaseQueryExecutor.executeInsert(
                    DatabaseQueries.INSERT_QUERIES.INSERT_CAR,
                    c.getManufacturerID(), c.getMake(), c.getModel(), c.getYear()
            );
        } catch (SQLException e) {
            // TODO: 6/4/2023 maybe display dialog explaining error, maybe do this in CarController and not Service instead
            throw new RuntimeException(e);
        }
    }
    public static boolean updateCar(Car c) {
        try {
            return DatabaseQueryExecutor.executeUpdate(
                    DatabaseQueries.UPDATE_QUERIES.UPDATE_CAR,
                    c.getManufacturerID(), c.getMake(), c.getModel(), c.getYear()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean deleteCar(String carId) {
        try {
            return DatabaseQueryExecutor.executeDelete(
                    DatabaseQueries.DELETE_QUERIES.DELETE_CAR,
                    carId
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
