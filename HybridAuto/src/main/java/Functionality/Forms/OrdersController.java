package Functionality.Forms;

import Entities.Customer;
import Entities.Order;
import Entities.OrderDetail;
import Entities.Stock;
import Functionality.Database.OrdersService;
import Screens.CheckOutForm;
import Screens.CustomerForm;
import Screens.OrderForm;
import Screens.StatusScreen;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
            itemID.add(stock.getStockID());
        }else {
            StatusScreen.setNotification(stock.getSerialNumber()+" Already Added");
        }
    }
    public static void removeOrderItem(Stock stock){
        for(int i = 0;i<itemID.size();i++){
            if(itemID.get(i).equals(stock.getStockID())&&stock!=null){
                itemID.remove(i);
                orderList.remove(stock);
                break;
            }
        }
    }
    public static void clearCart(){
        orderList.clear();
        itemID.clear();
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
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static Customer searchCustomer(String phone){
        try{
            return OrdersService.getCustomer(phone);
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }

    public static void addOrder(Order order){
        try {
            OrdersService.insertOrder(order);
        } catch (SQLException e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }
    public static int newOrderID(){
        try {
            return OrdersService.getNewOrderID();
        } catch (SQLException e) {
            new Notification(e);
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
            PDFDocument pdfDocument = new PDFDocument(customer);
            while(!orderList.isEmpty()){
                OrdersService.insertOrderDetails(new OrderDetail(orderID,Integer.parseInt(orderList.get(orderList.size()-1).getStockID())));
                InventoryController.deleteProduct(orderList.get(orderList.size()-1).getStockID());
                orderList.remove(orderList.size()-1);
            }
            orderList.clear();
            itemID.clear();
            OrderTable.inventoryTable.refresh();
            CheckOutForm.orderIDLabel.setText("Order-ID: "+OrdersController.newOrderID());
            CheckOutForm.discountField.clear();
            CheckOutForm.discountAmountField.clear();
            StatusScreen.setNotification(customer.getPhone()+" Checkout " + LocalDateTime.now().toLocalTime());
            OrderTable.inventoryTable.setItems(InventoryController.getInventoryList());
        } catch (Exception e) {
            new Notification(e);
            throw new RuntimeException(e);
        }
    }



}
