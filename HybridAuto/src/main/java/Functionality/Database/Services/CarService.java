package Functionality.Database.Services;

import Entities.Car;
import Functionality.Database.DB.DatabaseQueries;
import Functionality.Database.DB.DatabaseQueryExecutor;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static boolean addCar(Car c) {
        try {
            return DatabaseQueryExecutor.executeInsert(
                    DatabaseQueries.INSERT_QUERIES.INSERT_CAR,
                    c.getMake(), c.getModel(), c.getYear()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean updateCar(Car c) {
        try {
            return DatabaseQueryExecutor.executeUpdate(
                    DatabaseQueries.UPDATE_QUERIES.UPDATE_CAR,
                    c.getMake(), c.getModel(), c.getYear()
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


    public static Car searchCar(String carId) {
        try {
            return (Car) DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_CAR_WITH_ID,
                    "car",
                    carId
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car searchCar(String make, String model, String year) {
        try {
            return (Car) DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_CAR_WITH_MAKE_MODEL_AND_YEAR,
                    "car",
                    make, model, year
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllMakes() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_MAKES,
                    "car"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllModels() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_MODELS,
                    "car"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllYears() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_YEARS,
                    "car"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllModelsWIthMake(String make) {
        try {
            return DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_MODELS_WITH_MAKE,
                    "string", make
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllYearsWithModel(String model) {
        try {
            return DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_YEARS_WITH_MODEL,
                    "string", model
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
