package Functionality.Database;

import Entities.Bill;
import Functionality.Database.DB.DbConnection;
import Functionality.Forms.BillsController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillsService {
    private static ResultSet resultSet;

    public static void getBills() throws SQLException {
        resultSet = null;
        String queryString = "select * from Bills";
        resultSet = DbConnection.getPrepared(queryString).executeQuery();
        while(resultSet.next()) {
            BillsController.billsLists.add(new Bill(resultSet.getString("bill_ID"),
                    resultSet.getString("bill_Type"),
                    resultSet.getString("amount"),
                    resultSet.getString("created_DateTime")
            ));
        }
    }

    public static void insertBill(Bill bill) throws SQLException {
        resultSet = null;
        String queryString = "insert into Bills values(?,?,?)";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, bill.getBillType());
        pSt.setString(2, bill.getBillAmount());
        pSt.setString(3, bill.getDateTime());
        pSt.execute();
    }

    public static void updateBill(Bill bill) throws SQLException {
        resultSet = null;
        String queryString = "update Bills set bill_Type = ?,amount = ?,created_Datetime = ? where bill_ID = ? ";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, bill.getBillType());
        pSt.setString(2, bill.getBillAmount());
        pSt.setString(3, bill.getDateTime());
        pSt.setString(4, bill.getBillID());
        pSt.execute();
    }
    public static void deleteBill(Bill bill) throws SQLException {
        resultSet = null;
        String queryString = "delete from Bills where bill_ID = ? ";
        PreparedStatement pSt = DbConnection.getPrepared(queryString);
        pSt.setString(1, bill.getBillID());
        pSt.execute();
    }


}
