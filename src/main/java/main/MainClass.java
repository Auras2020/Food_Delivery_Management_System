package main;

import business.DeliveryService;
import data.Serializator;
import presentation.*;

public class MainClass {

    public static void main(String[] args){
        /*DeliveryService deliveryService=new DeliveryService();
        deliveryService.importProducts();

        Date d1=new Date(2011, Calendar.JANUARY, 4, 7, 8);
        //Order o1=new Order(1, 2, d1);
        Date d2=new Date(2012, Calendar.MARCH, 3, 9, 8);
        //Order o2=new Order(2, 3, d2);
        Date d3=new Date(2013, Calendar.FEBRUARY, 4, 3, 8);
        //Order o3=new Order(5, 6, d3);

        deliveryService.createNewOrder(1, 3, d1);
        deliveryService.createNewOrder(3, 3, d2);
        deliveryService.createNewOrder(5, 6, d3);

        List<String> products=new ArrayList<>();
        products.add("Soup");
        products.add("Grilled");
        products.add("Sauce");
        deliveryService.addProductToOrder(products, 1);

        products=new ArrayList<>();
        products.add("Sauce");
        products.add("Soup");
        products.add("Bean");
        deliveryService.addProductToOrder(products, 3);

        products=new ArrayList<>();
        products.add("Soup");
        products.add("Sauce");
        products.add("Bacon");
        deliveryService.addProductToOrder(products, 5);

        deliveryService.generateReport();
        //System.out.println(deliveryService.searchProduct("Soup").toString());
        //System.out.println(deliveryService.searchProduct("Soup").size());

        deliveryService.generateBill(3);*/

        LogInWindow logInWindow=new LogInWindow();
        Client client=new Client();
        Administrator administrator=new Administrator();
        DeliveryService deliveryService=new DeliveryService();
        Employee employee=new Employee(deliveryService);
        deliveryService.addObserver(employee);
        ControllerLogIn controllerLogIn=new ControllerLogIn(logInWindow, administrator, client, employee);

        ControllerClient controllerClient=new ControllerClient(client, deliveryService);
        ControllerAdministrator controllerAdministrator=new ControllerAdministrator(administrator, deliveryService);

        Serializator serializable=new Serializator(deliveryService, "serializare.txt");
        serializable.load();

    }
}
