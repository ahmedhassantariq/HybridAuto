package Entities;

public class OrderDetails {
    private int orderID;
    private int stockID;

    public OrderDetails(int orderID, int stockID) {
        this.orderID = orderID;
        this.stockID = stockID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }
}
