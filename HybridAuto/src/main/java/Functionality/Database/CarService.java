package Functionality.Database;

import Entities.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static Car getCar(String cID) {
        try{
            PreparedStatement pSt = DatabaseService.getPreparedFrom("SELECT * FROM tbl_cars cWHERE c.cid = ?");
            pSt.setString(1, cID);
            return DatabaseService.<Car>executeScalar(pSt, Car.class).orElseThrow(
                    () -> new SQLException(String.format("Car with Car ID %s Does Not Exist", cID))
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<Car> getCar(String make, String model, String year) {
        try{
            boolean makeBlank = make.isBlank();
            boolean modelBlank = model.isBlank();
            boolean yearBlank = year.isBlank();

            PreparedStatement pSt = DatabaseService.getPreparedFrom("SELECT * FROM tbl_cars c WHERE "
                    + (makeBlank? "" : "c.make = ? ")
                    + (modelBlank? "" : ((makeBlank? "" : "AND") + " c.model = ? "))
                    + (yearBlank? "" : ((makeBlank && modelBlank? "" : "AND") + " c.year = ? ")));

            int numAdded = 0;

            if(!makeBlank)
                pSt.setString(++numAdded, make);

            if(!modelBlank)
                pSt.setString(++numAdded, model);

            if(!yearBlank)
                pSt.setString(++numAdded, year);

            return DatabaseService.<Car>executeInline(pSt, Car.class).orElseThrow(
                    () -> new SQLException(String.format("Car with %s%s%s Does Not Exist",
                            makeBlank? "" : "Make " + make,
                            modelBlank? "" : " Model " + model,
                            yearBlank? "" : " Year " + year
                    ))
            );
        }catch (SQLException ex) {
            return null;
        }
    }

    public static List<String> getAllMakeDistinct() {
        try{
            return DatabaseService.getAllCarMakes().orElseThrow(
                    () -> new SQLException("No Make Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static List<String> getAllModelsDistinct() {
        try{
            return DatabaseService.getAllCarModels().orElseThrow(
                    () -> new SQLException("No Model Found")
            );
        }catch (SQLException ex) {
            return null;
        }
    }
}
