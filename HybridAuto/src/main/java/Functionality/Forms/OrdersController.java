package Functionality.Forms;

import Entities.Stock;
import Utils.CartTable;
import Utils.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class OrdersController {
    public static ObservableList<Stock> orderList = FXCollections.observableArrayList();
    private static LinkedList<String> itemID = new LinkedList<>();
    private static Stack<ObservableList<Stock>> orderStack = new Stack<>();


    public static void addOrderItem(Stock stock){
        if(!itemID.contains(stock.getStockID())){
            orderList.add(stock);
            InventoryController.inventoryList.remove(stock);
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
                InventoryController.inventoryList.add(stock);
                break;
            }
        }

    }

    public static void pushOrder(){
        if(!orderList.isEmpty()) {
            orderStack.push(orderList);
            orderList = FXCollections.observableArrayList();
            CartTable.cartTable.setItems(orderList);
        }
    }
    public static void popOrder(){
        if(!orderStack.isEmpty()) {
            orderList = orderStack.pop();
            CartTable.cartTable.setItems(orderList);
        }
    }
}
