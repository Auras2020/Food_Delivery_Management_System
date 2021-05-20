package presentation;

import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;
import data.Serializator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControllerAdministrator {

    private Administrator administrator;
    private DeliveryService deliveryService;
    private List<MenuItem> products=new ArrayList<>();

    public ControllerAdministrator(Administrator administrator, DeliveryService deliveryService){
        this.administrator=administrator;
        this.deliveryService=deliveryService;

        this.administrator.addImportProductsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administrator.getChecked().setForeground(Color.GREEN);
                administrator.setChecked("Products have been imported successful");
                deliveryService.importProducts();
                Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                serializable.save();
            }
        });

        this.administrator.addAddProductActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=administrator.getText1();
                double rating=0;
                int calories=0, proteins=0, fat=0, sodium=0, price=0;
                int flag=0;
                try{
                    rating=Double.parseDouble(administrator.getText2());
                    calories=Integer.parseInt(administrator.getText3());
                    proteins=Integer.parseInt(administrator.getText4());
                    fat=Integer.parseInt(administrator.getText5());
                    sodium=Integer.parseInt(administrator.getText6());
                    price=Integer.parseInt(administrator.getText7());
                }catch(NumberFormatException ex){
                    flag=1;
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("Product details must be numbers!!");
                }
                if(name.isEmpty()){
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("Name of the product is empty!!");
                }
                else if(flag==0){
                    administrator.getChecked().setForeground(Color.GREEN);
                    administrator.setChecked("Product" + name + "have been added successful");
                    deliveryService.addProduct(new BaseProduct(name, rating, calories, proteins, fat, sodium, price));
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.administrator.addDeleteProductActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=administrator.getText1();
                String rating=administrator.getText2();
                String calories=administrator.getText3();
                String proteins=administrator.getText4();
                String fat=administrator.getText5();
                String sodium=administrator.getText6();
                String price=administrator.getText7();
                if(name.isEmpty() && rating.isEmpty() && calories.isEmpty() && proteins.isEmpty()
                        && fat.isEmpty() && sodium.isEmpty() && price.isEmpty()){
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("No restriction introduced for deleting!!");
                }
                else{
                    administrator.getChecked().setForeground(Color.GREEN);
                    administrator.setChecked("Product have been deleted successful");
                    deliveryService.deleteProducts(name, rating, calories, proteins, fat, sodium, price);
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.administrator.addComposedProductActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=administrator.getText1();
                String rating=administrator.getText2();
                String calories=administrator.getText3();
                String proteins=administrator.getText4();
                String fat=administrator.getText5();
                String sodium=administrator.getText6();
                String price=administrator.getText7();
                if(name.isEmpty() && rating.isEmpty() && calories.isEmpty() && proteins.isEmpty()
                        && fat.isEmpty() && sodium.isEmpty() && price.isEmpty()){
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("No restriction introduced for selecting a product to form a composed one!!");
                }
                else if(deliveryService.searchProduct(name, rating, calories, proteins, fat,
                        sodium, price).size()>0){
                    administrator.getChecked().setForeground(Color.GREEN);
                    administrator.setChecked("Product" +
                            deliveryService.searchProduct(name, rating, calories, proteins, fat,
                                    sodium, price).get(0).getTitle() + "have been added successful to composed list");
                    products.add(deliveryService.searchProduct(name, rating, calories, proteins, fat,
                            sodium, price).get(0));
                }
            }
        });

        this.administrator.addModifyProductActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0;
                String name=administrator.getText1();
                String rating=administrator.getText2();
                String calories=administrator.getText3();
                String proteins=administrator.getText4();
                String fat=administrator.getText5();
                String sodium=administrator.getText6();
                String price=administrator.getText7();
                int newPrice=0;
                try{
                    newPrice=Integer.parseInt(administrator.getUpdatePrice());
                }catch(NumberFormatException ex){
                    flag=1;
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("New price has to be an integer!!");
                }
                if(name.isEmpty() && rating.isEmpty() && calories.isEmpty() && proteins.isEmpty()
                        && fat.isEmpty() && sodium.isEmpty() && price.isEmpty()){
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("No restriction introduced for selecting a product to form a composed one!!");
                }
                else if(flag==0){
                    administrator.getChecked().setForeground(Color.GREEN);
                    administrator.setChecked("Products have been updated successful");
                    deliveryService.modifyProducts(name, rating, calories, proteins, fat,
                            sodium, price, newPrice);
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.administrator.addCreateComposedActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(administrator.getComposed().isEmpty()){
                    administrator.getChecked().setForeground(Color.RED);
                    administrator.setChecked("The name of the composed product cant be empty!!");
                }
                else{
                    administrator.getChecked().setForeground(Color.GREEN);
                    String menus="";
                    int i=0;
                    for(MenuItem p: products){
                        if(i==products.size()-1){
                            menus+=p.getTitle();
                        }
                        else{
                            menus+=p.getTitle() + ", ";
                        }
                        i++;
                    }
                    administrator.setChecked("Composed product " + administrator.getComposed() +
                            " containing products " + menus + " has been created successful");
                    deliveryService.createNewComposedProduct(administrator.getComposed(), products);
                    products=new ArrayList<>();
                    Serializator serializable=new Serializator(deliveryService, "serializare.txt");
                    serializable.save();
                }
            }
        });

        this.administrator.addGenerateReportsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administrator.getChecked().setForeground(Color.GREEN);
                administrator.setChecked("Reports have been generated successful");
                deliveryService.generateReport();
            }
        });
    }
}
