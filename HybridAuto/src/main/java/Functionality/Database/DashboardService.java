package Functionality.Database;

import Functionality.Database.DB.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardService {
    private static ResultSet resultSet;


    public static String dashBoardQueries(String queryString) throws SQLException {
        resultSet = null;
        String ressult = "";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        resultSet = pSt.executeQuery();
        while(resultSet.next()) {
            if(resultSet.getString(1)!=null) {
                ressult = resultSet.getString(1);
            }else return ""+0.0;
        }
        return ressult;
    }
}
