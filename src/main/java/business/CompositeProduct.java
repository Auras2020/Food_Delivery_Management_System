package business;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{

    private String title;
    private int price;
    private List<MenuItem> list=new ArrayList<>();

    public CompositeProduct(String title, int price) {
        super(title, price);
    }

    @Override
    public int computePrice() {
        int p=0;
        for(MenuItem m: list){
            p+=m.getPrice();
        }
        setPrice(p);
        return p;
    }

    public void addProduct(MenuItem m){
        list.add(m);
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<MenuItem> getList() {
        return list;
    }
}
