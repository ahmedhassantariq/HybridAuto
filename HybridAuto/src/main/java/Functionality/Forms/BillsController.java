package Functionality.Forms;

import Entities.Bill;
import Functionality.Database.BillsService;
import Screens.StatusScreen;
import Utils.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BillsController {
    public static ObservableList<Bill> billsLists = FXCollections.observableArrayList();
    public static ObservableList<String> billTypeList = FXCollections.observableArrayList(
            "WATER","ELECTRICITY","GAS","SALARY","OTHER"
    );

    public static ObservableList<Bill> getBills(){
        try {
            billsLists.clear();
           BillsService.getBills();
           return billsLists;
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void addBill(Bill bill){
        try {
            BillsService.insertBill(bill);
            StatusScreen.setNotification("Bill Added");
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void updateBill(Bill bill){
        try {
            BillsService.updateBill(bill);
            StatusScreen.setNotification("Bill Updated");
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void deleteBill(Bill bill){
        try {
            BillsService.deleteBill(bill);
            StatusScreen.setNotification("Bill Deleted");
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
}
