package Entities;

import java.sql.Date;
import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int customerID;
    private Date dateTime;


    public Order(int orderID, int customerID, Date dateTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.dateTime = dateTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
