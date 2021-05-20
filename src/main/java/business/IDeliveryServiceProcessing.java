package business;

import java.util.Date;
import java.util.List;

public interface IDeliveryServiceProcessing{
    //administrator

    /**
     * Import products in the menu
     * @pre list!=null
     * @post list.size()>0
     */
    public void importProducts();

    /**
     * Adds a new product in the list
     * @pre m!=null
     * @post list.size()==list.size()@pre + 1
     */
    public void addProduct(MenuItem m);

    /**
     * Deletes the product that mathces the parameters in the method
     * @pre getSize>0
     * @post list.size()!=list.size()@pre
     */
    public void deleteProducts(String name, String rating, String calories, String proteins,
                              String fat, String sodium, String price);

    /**
     * Modifies price of products that matches parameters in the method
     * @pre newPrice>0
     * @post newPrice!=price
     */
    public void modifyProducts(String name, String rating, String calories, String proteins,
                               String fat, String sodium, String price, int newPrice);

    /**
     * Creates a composite product and adds it to the list
     * @pre products.size>0 && products!=null && title!=null
     * @post list.size()==list.size()@pre + 1
     */
    public void createNewComposedProduct(String title, List<MenuItem> products);
    public void generateReport();

    //client
    public void viewProducts(int clientID);
    public List<MenuItem> searchProduct(String name, String rating, String calories, String proteins,
                                        String fat, String sodium, String price);

    /**
     * Creates a new order
     * @pre orderID>0 && clientID>0 && orderDate!=null
     * @post order.size()==order.size()@pre + 1
     */
    public void createNewOrder(int orderID, int clientID, Date orderDate);

    /**
     * Adds a product to an order
     * @pre orderID>0 && product!=null
     * @post order.get(o).size()==order.get(o).size()@pre + 1
     */
    public void addProductToOrder(String product, int orderID);
    public double computePrice(Order o);
    public void generateBill(int clientID);
}
