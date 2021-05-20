package data;

import business.DeliveryService;

import java.io.*;

public class Serializator {

    private DeliveryService deliveryService;
    private String filename;

    public Serializator(DeliveryService deliveryService, String filename) {
        this.deliveryService = deliveryService;
        this.filename = filename;
    }

    public void save(){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(deliveryService);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }catch(IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public void load(){
        DeliveryService deliveryService1=null;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            deliveryService1 = (DeliveryService)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("List of products: ");
            System.out.println(deliveryService1.getList());
            System.out.println("Number of products: ");
            System.out.println(deliveryService1.getList().size());
            System.out.println("Performed orders: ");
            deliveryService1.getOrder().entrySet().stream()
                    .forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue()));
            System.out.println("User information ");
            System.out.println("ID for each client: ");
            deliveryService1.getOrder().entrySet().stream()
                    .distinct()
                    .forEach(entry-> System.out.println(entry.getKey().getClientID()));
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
