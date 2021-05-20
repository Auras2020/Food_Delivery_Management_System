package presentation;

import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;
import data.Serializator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerClient {

    private Client client;
    private DeliveryService deliveryService;
    private List<String> products=new ArrayList<>();

    public ControllerClient(Client client, DeliveryService deliveryService){
        this.client=client;
        this.deliveryService=deliveryService;

        this.client.addViewProductsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0, clientID=0;
                try{
                    clientID=Integer.parseInt(client.getClient());
                }catch(NumberFormatException ex){
                    flag=1;
                    client.getChecked().setForeground(Color.RED);
                    client.setChecked("ClientId must be an integer!!");
                }
                if(flag==0){
                    client.getChecked().setForeground(Color.GREEN);
                    client.setChecked("Clientul " + clientID + " vizualizeaza lista de produse din meniu");
                    deliveryService.viewProducts(clientID);
                    refreshTable(deliveryService.getList());
                }
            }
        });

        this.client.addSearchProductsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=client.getText1();
                String rating=client.getText2();
                String calories=client.getText3();
                String proteins=client.getText4();
                String fat=client.getText5();
                String sodium=client.getText6();
                String price=client.getText7();
                refreshTable(deliveryService.searchProduct(name, rating, calories, proteins, fat, sodium, price));
            }
        });

        this.client.addcreateOrderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0;
                int orderID=0, year=0, month=0, day=0, hour=0, minute=0, clientID=0;
                try{
                    orderID=Integer.parseInt(client.getOrder());
                    clientID=Integer.parseInt(client.getClient());
                    year=Integer.parseInt(client.getAn());
                    month=Integer.parseInt(client.getLuna());
                    day=Integer.parseInt(client.getZi());
                    hour=Integer.parseInt(client.getOra());
                    minute=Integer.parseInt(client.getMinut());
                }catch(NumberFormatException ex){
                    flag=1;
                    client.getChecked().setForeground(Color.RED);
                    client.setChecked("Order details must be integers!!");
                }
                if(flag==0){
                    client.getChecked().setForeground(Color.GREEN);
                    client.setChecked("Order with id " + client.getOrder() + " was generated");
                    Date date=new Date(year, month, day, hour, minute);
                    deliveryService.createNewOrder(orderID, clientID, date);
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.client.addProductToOrderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0;
                int orderID=0;
                String product=client.getProd();
                try{
                    orderID=Integer.parseInt(client.getOrd());
                }catch(NumberFormatException ex){
                    flag=1;
                    client.getChecked().setForeground(Color.RED);
                    client.setChecked("Order ID must be an integer!!");
                }
                if(flag==0){
                    client.getChecked().setForeground(Color.GREEN);
                    try{
                        client.setChecked("Product " + deliveryService.searchProduct(product, "", "", "", "", "", "").get(0).getTitle() + " was processed");
                    }catch(IndexOutOfBoundsException ex){
                        client.getChecked().setForeground(Color.RED);
                        client.setChecked("Product " + product + " doesnt exist!!");
                    }
                    deliveryService.addProductToOrder(product, orderID);
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.client.addGenerateBillActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0, clientID=0;
                try{
                    clientID=Integer.parseInt(client.getClient());
                }catch(NumberFormatException ex){
                    flag=1;
                    client.getChecked().setForeground(Color.RED);
                    client.setChecked("ClientId must be an integer!!");
                }
                if(flag==0){
                    deliveryService.generateBill(clientID);
                    client.getChecked().setForeground(Color.GREEN);
                    client.setChecked("Bill for client " + clientID + " was generated with success");
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });
    }

    public void refreshTable(List<MenuItem> list){
        client.getModel().setRowCount(0);
        for(MenuItem m: list){
            Object[] row=new Object[7];
            row[0]=m.getTitle();
            row[1]=((BaseProduct)m).getRating();
            row[2]=((BaseProduct)m).getCalories();
            row[3]=((BaseProduct)m).getProtein();
            row[4]=((BaseProduct)m).getFat();
            row[5]=((BaseProduct)m).getSodium();
            row[6]=m.getPrice();
            client.getModel().addRow(row);
        }
    }
}
