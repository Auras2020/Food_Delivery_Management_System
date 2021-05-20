package business;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private int orderID;
    private int clientID;
    private Date orderDate;

    public Order(int orderID, int clientID, Date orderDate){
        this.orderID=orderID;
        this.clientID=clientID;
        this.orderDate=orderDate;
    }

    public int hashCode(){
        return Integer.parseInt(orderID + "" + clientID + "" + Math.abs(orderDate.hashCode()/1000));
    }

    public boolean equals(Object object){
        boolean x;
        if(object==this){
            x=true;
        }
        if(!(object instanceof Order)){
            x=false;
        }
        Order order=(Order)object;
        if(order.orderID==orderID && order.clientID==clientID && order.orderDate.equals(orderDate)){
            x=true;
        }
        else{
            x=false;
        }
        return x;
    }

    public String toString(){
        return "orderID " + orderID + " clientID " + clientID + " orderDate " + orderDate + "\n";
    }

    public int getClientID() {
        return clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getOrderID() {
        return orderID;
    }
}
