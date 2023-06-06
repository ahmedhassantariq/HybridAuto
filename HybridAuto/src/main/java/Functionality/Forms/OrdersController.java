package Functionality.Forms;

import Entities.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;

public class OrdersController {
    public static ObservableList<Stock> orderList = FXCollections.observableArrayList();
    private static LinkedList<String> itemID = new LinkedList<>();

    public static void addOrderItem(Stock stock){
        if(!itemID.contains(stock.getStockID())){
            orderList.add(stock);
            itemID.add(stock.getStockID());
        }else {
            System.out.println("Item already In list");
        }
    }
    public static void removeOrderItem(Stock stock){
        for(int i = 0;i<itemID.size();i++){
            if(itemID.get(i).equals(stock.getStockID())){
                itemID.remove(i);
                orderList.remove(stock);
                break;
            }
        }

    }
}
