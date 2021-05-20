package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    private String title;
    private int price;
    private int orderedTimes;

    public MenuItem(String title, int price){
        this.title=title;
        this.price=price;
    }

    public abstract int computePrice();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getOrderedTimes() {
        return orderedTimes;
    }

    public void setOrderedTimes(int orderedTimes) {
        this.orderedTimes = orderedTimes;
    }

}
