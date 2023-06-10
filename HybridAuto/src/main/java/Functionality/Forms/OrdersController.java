package Functionality.Forms;

import Entities.Customer;
import Entities.Order;
import Entities.OrderDetail;
import Entities.Stock;
import Functionality.Database.OrdersService;
import Screens.CustomerForm;
import Screens.OrderForm;
import Utils.CartTable;
import Utils.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
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
            if(itemID.get(i).equals(stock.getStockID())&&stock!=null){
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

    public static void addCustomer(Customer customer){
        try {
            if(OrdersService.getCustomer(customer.getPhone())==null){
                OrdersService.insertCustomer(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Customer searchCustomer(String phone){
        try{
            return OrdersService.getCustomer(phone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addOrder(Order order){
        try {
            OrdersService.insertOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int newOrderID(){
        try {
            return OrdersService.getNewOrderID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void orderCheckout(Customer customer){
        try{
            addCustomer(customer);
            String customerID = OrdersService.getCustomer(customer.getPhone()).getCustomerID();
            int orderID = OrdersService.getNewOrderID();
            if(customerID!=null&&orderID!=0) {
                OrdersService.insertOrder(new Order(String.valueOf(orderID),customerID,null));
            }
            while(!orderList.isEmpty()){
                OrdersService.insertOrderDetails(new OrderDetail(orderID,Integer.parseInt(orderList.get(orderList.size()-1).getStockID())));
                InventoryController.deleteProduct(orderList.get(orderList.size()-1).getStockID());
                orderList.remove(orderList.size()-1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            orderList.clear();
            itemID.clear();
            OrderTable.inventoryTable.refresh();
        }
    }



}
