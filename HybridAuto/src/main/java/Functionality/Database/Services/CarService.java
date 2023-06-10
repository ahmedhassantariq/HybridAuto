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

    public static Car searchCarWithMakeModelYear(String make, String model, String year) {
        try {
            return (Car) DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_CAR_WITH_MAKE_MODEL_AND_YEAR,
                    "car",
                    make, model, year
            ).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllMakes() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_MAKES,
                    "string"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllModels() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_MODELS,
                    "string"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> searchAllYears() {
        try {
            return DatabaseQueryExecutor.executeGet(
                    DatabaseQueries.SEARCH_QUERIES.GET_ALL_DISTINCT_CAR_YEARS,
                    "string"
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

    public static List<String> searchAllYearsWithMakeModel(String make, String model) {
        try {
            return DatabaseQueryExecutor.executeGetWithCondition(
                    DatabaseQueries.SEARCH_QUERIES.WITH_CONDITION.SEARCH_YEARS_WITH_MAKE_AND_MODEL,
                    "string", make, model
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

    // TODO: 6/5/2023 Stuff in Ahmed Branch Inventory Service
    public static int searchCarIDWithMakeModelYear(String make, String model, String year) {
        return Integer.parseInt(searchCarWithMakeModelYear(make, model, year)
                .getCarID());
    }
}
