package presentation;

import business.BaseProduct;
import business.DeliveryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Client extends JFrame {

    private JFrame frame;

    private JLabel title;

    private JLabel name;
    private JLabel rating;
    private JLabel calories;
    private JLabel proteins;
    private JLabel fats;
    private JLabel sodium;
    private JLabel price;

    private JLabel orderID;
    private JLabel clientID;

    private JLabel year;
    private JLabel month;
    private JLabel day;
    private JLabel hour;
    private JLabel minute;

    private JLabel product;
    private JLabel nrOrder;

    private JLabel checked;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;

    private JTextField order;
    private JTextField client;

    private JTextField an;
    private JTextField luna;
    private JTextField zi;
    private JTextField ora;
    private JTextField minut;

    private JTextField prod;
    private JTextField ord;

    private JButton viewProducts;
    private JButton searchProduct;
    //private JButton date;
    private JButton addProduct;
    private JButton createOrder;
    private JButton generateBill;

    private JTable table;
    private JScrollPane pane;
    private DefaultTableModel model;
    private DeliveryService deliveryService=new DeliveryService();

    public Client(){
        frame=new JFrame("Client");
        frame.setBackground(Color.white);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title=new JLabel("Client");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(title);

        viewProducts=new JButton("View Products");
        searchProduct=new JButton("Search Product");
        //date=new JButton("Create Date");
        createOrder=new JButton("Create Order");
        addProduct=new JButton("Add Product To Order");
        generateBill=new JButton("Generate Bill");

        JPanel panel2_1 = new JPanel();
        panel2_1.setLayout(new BoxLayout(panel2_1, BoxLayout.X_AXIS));
        panel2_1.add(viewProducts);
        panel2_1.add( Box.createRigidArea(new Dimension(20,0)) );
        panel2_1.add(searchProduct);
        //panel2.add( Box.createRigidArea(new Dimension(0,20)) );
        //panel2.add(date);
        JPanel panel2_2 = new JPanel();
        panel2_2.add(createOrder);
        panel2_2.add( Box.createRigidArea(new Dimension(20,0)) );
        panel2_2.add(addProduct);
        panel2_2.add( Box.createRigidArea(new Dimension(20,0)) );
        panel2_2.add(generateBill);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(panel2_1);
        panel2.add( Box.createRigidArea(new Dimension(0,10)) );
        panel2.add(panel2_2);

        name=new JLabel("name: ");
        text1=new JTextField();
        rating=new JLabel("rating: ");
        text2=new JTextField();
        calories=new JLabel("calories: ");
        text3=new JTextField();
        proteins=new JLabel("proteins: ");
        text4=new JTextField();
        fats=new JLabel("fats: ");
        text5=new JTextField();
        sodium=new JLabel("sodium: ");
        text6=new JTextField();
        price=new JLabel("price: ");
        text7=new JTextField();

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 7, 10, 10));
        panel3.add(name);
        panel3.add(rating);
        panel3.add(calories);
        panel3.add(proteins);
        panel3.add(fats);
        panel3.add(sodium);
        panel3.add(price);
        panel3.add(text1);
        panel3.add(text2);
        panel3.add(text3);
        panel3.add(text4);
        panel3.add(text5);
        panel3.add(text6);
        panel3.add(text7);

        orderID=new JLabel("orderID: ");
        order=new JTextField();
        clientID=new JLabel("clientID: ");
        client=new JTextField();

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel4.add( Box.createRigidArea(new Dimension(160,0)) );
        panel4.add(orderID);
        panel4.add( Box.createRigidArea(new Dimension(10,0)) );
        panel4.add(order);
        panel4.add( Box.createRigidArea(new Dimension(20,0)) );
        panel4.add(clientID);
        panel4.add( Box.createRigidArea(new Dimension(10,0)) );
        panel4.add(client);
        panel4.add( Box.createRigidArea(new Dimension(160,0)) );

        year=new JLabel("year: ");
        an=new JTextField();
        month=new JLabel("month: ");
        luna=new JTextField();
        day=new JLabel("day: ");
        zi=new JTextField();
        hour=new JLabel("hour: ");
        ora=new JTextField();
        minute=new JLabel("minute: ");
        minut=new JTextField();

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        panel5.add( Box.createRigidArea(new Dimension(20,0)) );
        panel5.add(year);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(an);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(month);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(luna);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(day);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(zi);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(hour);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(ora);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(minute);
        panel5.add( Box.createRigidArea(new Dimension(10,0)) );
        panel5.add(minut);
        panel5.add( Box.createRigidArea(new Dimension(20,0)) );

        product=new JLabel("add product ");
        prod=new JTextField();
        nrOrder=new JLabel(" to order with ID ");
        ord=new JTextField();

        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
        panel6.add( Box.createRigidArea(new Dimension(70,0)) );
        panel6.add(product);
        panel6.add( Box.createRigidArea(new Dimension(10,0)) );
        panel6.add(prod);
        panel6.add( Box.createRigidArea(new Dimension(10,0)) );
        panel6.add(nrOrder);
        panel6.add( Box.createRigidArea(new Dimension(10,0)) );
        panel6.add(ord);
        panel6.add( Box.createRigidArea(new Dimension(70,0)) );

        String []column=new String[]{"title", "rating", "calories", "protein", "fat", "sodium", "price"};
        //deliveryService.importProducts();
        String [][]data=new String[deliveryService.getList().size()][7];
        for(int i=0; i<deliveryService.getList().size(); i++){
            data[i][0]=(deliveryService.getList().get(i)).getTitle();
            data[i][1]=((BaseProduct)deliveryService.getList().get(i)).getRating() + "";
            data[i][2]=((BaseProduct)deliveryService.getList().get(i)).getCalories() + "";
            data[i][3]=((BaseProduct)deliveryService.getList().get(i)).getProtein() + "";
            data[i][4]=((BaseProduct)deliveryService.getList().get(i)).getFat() + "";
            data[i][5]=((BaseProduct)deliveryService.getList().get(i)).getSodium() + "";
            data[i][6]=(deliveryService.getList().get(i)).getPrice() + "";
        }
        table=new JTable();
        model=new DefaultTableModel(data, column);
        model.setColumnIdentifiers(column);
        table.setModel(model);
        pane=new JScrollPane(table);

        JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
        panel7.add( Box.createRigidArea(new Dimension(20,0)) );
        panel7.add(pane);
        panel7.add( Box.createRigidArea(new Dimension(20,0)) );

        checked=new JLabel("");

        JPanel panel7_1 = new JPanel();
        panel7_1.setLayout(new BoxLayout(panel7_1, BoxLayout.X_AXIS));
        panel7_1.add(checked);

        JPanel panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel1);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel2);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel3);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel4);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel5);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel6);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel7_1);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );
        panel8.add(panel7);
        panel8.add( Box.createRigidArea(new Dimension(0,20)) );

        frame.add(panel8);
        //frame.setVisible(true);
        //frame2.setLocationRelativeTo(null);
        frame.setBounds(460, 100, 647, 418);
        frame.pack();
    }

    public void addViewProductsActionListener(ActionListener view){
        this.viewProducts.addActionListener(view);
    }

    public void addSearchProductsActionListener(ActionListener search){
        this.searchProduct.addActionListener(search);
    }

    public void addcreateOrderActionListener(ActionListener order){
        this.createOrder.addActionListener(order);
    }

    public void addProductToOrderActionListener(ActionListener addProduct){
        this.addProduct.addActionListener(addProduct);
    }

    public void addGenerateBillActionListener(ActionListener bill){
        this.generateBill.addActionListener(bill);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public String getText1() {
        return text1.getText();
    }

    public String getText2() {
        return text2.getText();
    }

    public String getText3() {
        return text3.getText();
    }

    public String getText4() {
        return text4.getText();
    }

    public String getText5() {
        return text5.getText();
    }

    public String getText6() {
        return text6.getText();
    }

    public String getText7() {
        return text7.getText();
    }

    public void setChecked(String checked) {
        this.checked.setText(checked);
    }

    public String getOrder() {
        return order.getText();
    }

    public String getClient() {
        return client.getText();
    }

    public String getAn() {
        return an.getText();
    }

    public String getLuna() {
        return luna.getText();
    }

    public String getZi() {
        return zi.getText();
    }

    public String getOra() {
        return ora.getText();
    }

    public String getMinut() {
        return minut.getText();
    }

    public JLabel getChecked() {
        return checked;
    }

    public String getProd() {
        return prod.getText();
    }

    public String getOrd() {
        return ord.getText();
    }

    public JFrame getFrame() {
        return frame;
    }
}
