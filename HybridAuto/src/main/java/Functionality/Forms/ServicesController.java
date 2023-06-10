package Functionality.Forms;

import Entities.Services;
import Functionality.Database.ServicesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServicesController {
    public static ObservableList<Services> servicesList = FXCollections.observableArrayList();


    public static void getServicesList(){
        try{
            ServicesService.getOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void searchOrder(Services services){
        try{
            ServicesService.searchOrders(services);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
