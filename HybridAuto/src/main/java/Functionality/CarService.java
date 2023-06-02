package Functionality;

import Entities.Car;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    public static Car getCar(String cID) {
        try{
            String queryStr = String.format("select * from cars c where c.cid = %s", cID);
            return DatabaseService.<Car>executeScalar(queryStr).orElseThrow(
                    () -> new SQLException(String.format("Car with Car ID %s Does Not Exist", cID))
            );
        }catch (SQLException ex) {
            return null;
        }
    }
    public static Car getCar(String make, String model, String year) {
        try{
            String queryStr = "select * from cars c where";

            if(!make.isBlank()) {
                queryStr += String.format(" c.make = %s", make);
            }
            if(!model.isBlank()) {
                if(queryStr.endsWith("where"))
                    queryStr += " ";
                else queryStr += " AND ";
                queryStr += String.format("c.model = %s", model);
            }
            if(!year.isBlank()) {
                if(queryStr.endsWith("where"))
                    queryStr += " ";
                else queryStr += " AND ";
                queryStr += String.format("c.year = %s", year);
            }

            return DatabaseService.<Car>executeScalar(queryStr).orElseThrow(
                    () -> new SQLException(String.format("Car with %s%s%s Does Not Exist",
                            make.isBlank()? "" : "Make " + make,
                            model.isBlank()? "" : " Model " + model,
                            year.isBlank()? "" : " Year " + year))
            );
        }catch (SQLException ex) {
            return null;
        }
    }
}
