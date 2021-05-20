package business;

import data.FileWrite;
import data.Serializator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable{

    private HashMap<Order, List<MenuItem>> order=new HashMap<>();
    private List<MenuItem> list=new ArrayList<>();

    protected boolean isWellFormed(){
        return order!=null && list!=null;
    }

    /**
     * Import products in the menu
     * @pre list!=null
     * @invariant isWellFormed()
     * @post list.size()>0
     */
    @Override
    public void importProducts() {
        assert list!=null;
        assert isWellFormed();
        try{
            List<String[]> l=Files.lines(Paths.get("products.csv"), Charset.defaultCharset())
                    .skip(1).map(line->line.split(",", 7))
                    .filter( distinctByKey(p-> Arrays.stream(p).findFirst()))
                    .collect(Collectors.toList());
            for(int i=0; i<l.size(); i++){
                BaseProduct b=new BaseProduct(l.get(i)[0], Double.parseDouble(l.get(i)[1]),
                        Integer.parseInt(l.get(i)[2]), Integer.parseInt(l.get(i)[3]),
                        Integer.parseInt(l.get(i)[4]), Integer.parseInt(l.get(i)[5]),
                        Integer.parseInt(l.get(i)[6]));
                b.setOrderedTimes(0);
                list.add(b);
            }
            System.out.println(list.toString());
            System.out.println("Number of products: ");
            System.out.println(list.size());
        }
        catch(IOException e){
            System.out.println("Could not access file");
        }
        assert list.size()>0;
        assert isWellFormed();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Adds a new product in the list
     * @pre m!=null
     * @invariant isWellFormed()
     * @post getSize==getSize@pre + 1
     */
    @Override
    public void addProduct(MenuItem m) {
        int sizePre=list.size();
        assert m!=null;
        assert isWellFormed();
        list.add(m);
        int sizePost=list.size();
        assert sizePost==sizePre+1;
        assert isWellFormed();
    }

    /**
     * Deletes the product that mathces the parameters in the method
     * @pre getSize>0
     * @invariant isWellFormed()
     * @post list.size()!=list.size()@pre
     */
    @Override
    public void deleteProducts(String name, String rating, String calories, String proteins,
                              String fat, String sodium, String price) {
        int sizePre=list.size();
        assert list.size()>0;
        assert isWellFormed();
        list.removeAll(searchProduct(name, rating, calories, proteins, fat, sodium, price));
        int sizePost=list.size();
        assert sizePost<sizePre;
        assert isWellFormed();
    }

    /**
     * Modifies price of products that matches parameters in the method
     * @pre newPrice>0
     * @invariant isWellFormed()
     * @post newPrice!=price
     */
    @Override
    public void modifyProducts(String name, String rating, String calories, String proteins,
                              String fat, String sodium, String price, int newPrice) {
        assert newPrice>0;
        assert isWellFormed();
        for(MenuItem product: searchProduct(name, rating, calories, proteins, fat, sodium, price)){
            product.setPrice(newPrice);
        }
        assert !price.equals(newPrice+"");
        assert isWellFormed();
    }

    /**
     * Creates a composite product and adds it to the list
     * @pre products.size>0 && products!=null && title!=null
     * @invariant isWellFormed()
     * @post list.size()==list.size()@pre + 1
     */
    @Override
    public void createNewComposedProduct(String title, List<MenuItem> products) {
        int sizePre=list.size();
        assert products.size()>0 && products!=null && title!=null;
        assert isWellFormed();
        CompositeProduct m=new CompositeProduct(title, 0);
        double rating=0;
        int calories=0, proteins=0, fat=0, sodium=0;
        for(MenuItem p: products){
            m.addProduct(p);
            m.setPrice(m.getPrice()+p.getPrice());
            rating+=((BaseProduct)p).getRating();
            calories+=((BaseProduct)p).getCalories();
            proteins+=((BaseProduct)p).getProtein();
            fat+=((BaseProduct)p).getFat();
            sodium+=((BaseProduct)p).getSodium();
        }
        BaseProduct b=new BaseProduct(m.getTitle(), rating, calories, proteins, fat, sodium, m.getPrice());
        list.add(b);
        int sizePost=list.size();
        assert sizePost==sizePre+1;
        assert isWellFormed();
    }

    public int timesOrdered(int clientID){
        int nr=0;
        for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
            if(entry.getKey().getClientID()==clientID){
                nr++;
            }
        }
        return nr;
    }

    public double totalPrice(int clientID){
        double price=0;
        for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
            if(entry.getKey().getClientID()==clientID){
                price+=computePrice(entry.getKey());
            }
        }
        return price;
    }

    public void generateReport1(FileWriter fWriter){
        int start=2, end=7;
        List<Order> orders=order.entrySet().stream()
                .filter(t->t.getKey().getOrderDate().getHours()>=start)
                .filter(t->t.getKey().getOrderDate().getHours()<=end)
                .map(p->p.getKey())
                .collect(Collectors.toList());
        System.out.println(orders.toString());
        try{
            fWriter.write("orders performed between " + start + " and " + end + ": " + System.getProperty("line.separator"));
            for(Order o: orders){
                fWriter.write(o.toString() + System.getProperty("line.separator"));
            }
        }
        catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }

    public void generateReport2(FileWriter fWriter){
        int times=2;
        List<MenuItem> products=list.stream()
                .filter(t->t.getOrderedTimes()>times)
                .collect(Collectors.toList());
        System.out.println(products.toString());
        try{
            fWriter.write(System.getProperty("line.separator") + "products ordered more than " + times + " times: " + System.getProperty("line.separator"));
            for(MenuItem m: products){
                fWriter.write(m.toString() + System.getProperty("line.separator"));
            }
        }
        catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }

    public void generateReport3(FileWriter fWriter){
        int nrOrders=1;
        double amount=100;
        List<Integer> clients=order.entrySet().stream()
                .filter(t->timesOrdered(t.getKey().getClientID())>nrOrders)
                .filter(t->totalPrice(t.getKey().getClientID())>amount)
                .map(p->p.getKey().getClientID())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(clients);
        try{
            fWriter.write(System.getProperty("line.separator") + "clients that have ordered more than " + nrOrders + " and the total value " +
                    "of the orders was bigger than " + amount + ": " + System.getProperty("line.separator"));
            for(Integer i: clients){
                fWriter.write(i + " ");
            }
            fWriter.write(System.getProperty("line.separator"));
        }
        catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }

    public void generateReport4(FileWriter fWriter){
        int day=4;
        List<List<MenuItem>> listProducts=order.entrySet().stream()
                .filter(t->t.getKey().getOrderDate().getDate()==day)
                .map(p->p.getValue())
                .collect(Collectors.toList());
        List<MenuItem> lp=new ArrayList<>();
        for(List<MenuItem> l: listProducts){
            lp.addAll(l);
        }
        List<MenuItem> lp1=lp.stream()
                .distinct()
                .collect(Collectors.toList());
        List<Integer> nrTimes=new ArrayList<>();
        for(int i=0; i<lp1.size(); i++){
            nrTimes.add(0);
        }
        for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
            if(entry.getKey().getOrderDate().getDate()==day){
                for(MenuItem m: entry.getValue()){
                    if(lp1.contains(m)){
                        nrTimes.set(lp1.indexOf(m), nrTimes.get(lp1.indexOf(m))+1);
                    }
                }
            }
        }
        try{
            fWriter.write(System.getProperty("line.separator") + "products ordered in day " +
                    day + ": " + System.getProperty("line.separator"));
            for(MenuItem m: lp1){
                System.out.println(m + " nr: " + nrTimes.get(lp1.indexOf(m)));
                fWriter.write(m + System.getProperty("line.separator") + "number of times being ordered: " + nrTimes.get(lp1.indexOf(m)) + System.getProperty("line.separator"));
            }
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }

    public void closeFile(FileWriter fWriter){
        try{
            fWriter.close();
        }catch(IOException ex) {
            System.out.println("Could not close file");
        }
    }

    @Override
    public void generateReport() {
        File file=new File("AdministratorReports.txt");
        FileWrite fWrite=new FileWrite(file);
        FileWriter fWriter=fWrite.createFWriter();

        generateReport1(fWriter);
        generateReport2(fWriter);
        generateReport3(fWriter);
        generateReport4(fWriter);

        closeFile(fWriter);
    }

    @Override
    public void viewProducts(int clientID) {
        System.out.println("Clientul " + clientID + " vizualizeaza lista de produse din meniu: ");
        for(MenuItem m: list){
            System.out.println(m.toString());
        }
        System.out.println("Number of products: ");
        System.out.println(list.size());
    }

    @Override
    public List<MenuItem> searchProduct(String name, String rating, String calories, String proteins,
                                         String fat, String sodium, String price) {
        List<MenuItem> menus=list.stream()
                .filter(m->m.getTitle().contains(name))
                .filter(rating.isEmpty() ? m->true : m->(((BaseProduct)m).getRating()+"").equals(rating))
                .filter(calories.isEmpty() ? m->true : m->(((BaseProduct)m).getCalories()+"").equals(calories))
                .filter(proteins.isEmpty() ? m->true : m->(((BaseProduct)m).getProtein()+"").equals(proteins))
                .filter(fat.isEmpty() ? m->true : m->(((BaseProduct)m).getFat()+"").equals(fat))
                .filter(sodium.isEmpty() ? m->true : m->(((BaseProduct)m).getSodium()+"").equals(sodium))
                .filter(price.isEmpty() ? m->true : m->(m.getPrice()+"").equals(price))
                .collect(Collectors.toList());
        return menus;
    }

    /**
     * Creates a new order
     * @pre orderID>0 && clientID>0 && orderDate!=null
     * @invariant isWellFormed()
     * @post order.size()==order.size()@pre + 1
     */
    @Override
    public void createNewOrder(int orderID, int clientID, Date orderDate) {
        int sizePre=order.size();
        assert orderID>0 && clientID>0 && orderDate!=null;
        assert isWellFormed();
        Order o=new Order(orderID, clientID, orderDate);
        List<MenuItem> menu=new ArrayList<>();
        order.put(o, menu);
        setChanged();
        notifyObservers(o);
        int sizePost=order.size();
        assert sizePost==sizePre+1;
        assert isWellFormed();
    }

    /**
     * Adds a product to an order
     * @pre orderID>0 && product!=null
     * @invariant isWellFormed()
     * @post order.get(o).size()==order.get(o).size()@pre + 1
     */
    @Override
    public void addProductToOrder(String product, int orderID) {
        Order o=null;
        int sizePre=0;
        assert orderID>0 && product!=null;
        assert isWellFormed();
        for(MenuItem m: list){
            if(m.getTitle().contains(product)){
                for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
                    if(entry.getKey().getOrderID()==orderID){
                        o=entry.getKey();
                        sizePre=entry.getValue().size();
                        entry.getValue().add(m);
                        m.setOrderedTimes(m.getOrderedTimes()+1);
                        break;
                    }
                }
                break;
            }
        }
        setChanged();
        notifyObservers(o);
        int sizePost=order.get(o)!=null ? order.get(o).size() : 0;
        assert sizePost==sizePre+1;
        assert isWellFormed();
    }

    @Override
    public double computePrice(Order o) {
        double price=0;
        for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
            if(entry.getKey().equals(o)){
                for(MenuItem menu: entry.getValue()){
                    price+=menu.computePrice();
                }
            }
        }
        return price;
    }

    @Override
    public void generateBill(int clientID){
        File file=new File("ClientBill.txt");
        FileWrite fWrite=new FileWrite(file);
        FileWriter fWriter=fWrite.createFWriter();

        try{
            fWriter.write("ordered products by client with id " + clientID + ": " +
                    System.getProperty("line.separator") + System.getProperty("line.separator"));
            int nr=0;
            for(HashMap.Entry<Order, List<MenuItem>> entry: order.entrySet()){
                if(entry.getKey().getClientID()==clientID){
                    nr++;
                    System.out.println("Order " + nr + ": ");
                    System.out.println(entry.getValue().toString());
                    System.out.println("Price of the order: " + computePrice(entry.getKey()));
                    System.out.println();

                    fWriter.write("Order " + nr + ": " + System.getProperty("line.separator"));
                    for(MenuItem m: entry.getValue()){
                        fWriter.write(m.toString() + System.getProperty("line.separator"));
                    }
                    fWriter.write("Price of the order: " + computePrice(entry.getKey()) + System.getProperty("line.separator"));
                    fWriter.write(System.getProperty("line.separator"));
                }
            }
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }

        closeFile(fWriter);
    }

    public List<MenuItem> getList() {
        return list;
    }

    public HashMap<Order, List<MenuItem>> getOrder() {
        return order;
    }

    public void setList(List<MenuItem> list) {
        this.list = list;
    }
}
