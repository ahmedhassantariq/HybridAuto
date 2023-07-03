package Entities;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDateTime;

public class Order {
    private String  orderID;
    private String  customerID;
    private Date dateTime;


    public Order(String orderID, String customerID, Date dateTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.dateTime = dateTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

}
