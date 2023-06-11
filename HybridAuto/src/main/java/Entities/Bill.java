package Entities;

import javafx.beans.property.SimpleStringProperty;

public class Bill {
    private SimpleStringProperty billID;
    private SimpleStringProperty billType;
    private SimpleStringProperty billAmount;
    private SimpleStringProperty dateTime;

    public Bill(String billID, String billType, String billAmount, String dateTime) {
        this.billID = new SimpleStringProperty(billID);
        this.billType = new SimpleStringProperty(billType);
        this.billAmount = new SimpleStringProperty(billAmount);
        this.dateTime = new SimpleStringProperty(dateTime);
    }

    public String getBillID() {
        return billID.get();
    }

    public SimpleStringProperty billIDProperty() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID.set(billID);
    }

    public String getBillType() {
        return billType.get();
    }

    public SimpleStringProperty billTypeProperty() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType.set(billType);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public SimpleStringProperty dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime.set(dateTime);
    }

    public String getBillAmount() {
        return billAmount.get();
    }

    public SimpleStringProperty billAmountProperty() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount.set(billAmount);
    }
}
