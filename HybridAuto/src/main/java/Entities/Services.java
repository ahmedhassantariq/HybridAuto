package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Services extends Customer {
    private SimpleStringProperty orderID;
    private SimpleStringProperty orderDate;

    public Services(String customerID, String firstName, String middleName, String lastName, String phone, String areaCode, String address, String orderID,String orderDate) {
        super(customerID, firstName, middleName, lastName, phone, areaCode, address);
        this.orderID = new SimpleStringProperty(orderID);
        this.orderDate = new SimpleStringProperty(orderDate);
    }

    public String getOrderID() {
        return orderID.get();
    }

    public SimpleStringProperty orderIDProperty() {
        return this.orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID.set(orderID);
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public SimpleStringProperty orderDateProperty() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public String getName(){
        String name ="";
        if(getFirstName()!=null){
            name+=" "+getFirstName();
        }
        if(getMiddleName()!=null){
            name+=" "+getMiddleName();
        }
        if(getLastName()!=null){
            name+=" "+getLastName();
        }
        return name;
    }


    public SimpleStringProperty getNameProperty(){
        String name ="";
        if(getFirstName()!=null){
            name+=" "+getFirstName();
        }
        if(getMiddleName()!=null){
            name+=" "+getMiddleName();
        }
        if(getLastName()!=null){
            name+=" "+getLastName();
        }
        return new SimpleStringProperty(name);
    }
    public SimpleStringProperty getPhoneProperty(){
        return getPhoneProp();
    }
    public SimpleStringProperty getDateProperty(){
        return orderDate;
    }
}
