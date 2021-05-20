package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Administrator extends JFrame {

    private JFrame frame;

    private JLabel title;

    private JLabel name;
    private JLabel rating;
    private JLabel calories;
    private JLabel proteins;
    private JLabel fats;
    private JLabel sodium;
    private JLabel price;

    private JLabel newPrice;

    private JLabel nameComposed;

    private JLabel checked;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;

    private JTextField updatePrice;

    private JTextField composed;

    private JButton importProd;
    private JButton add;
    private JButton delete;
    private JButton modify;
    private JButton addComp;
    private JButton prodComp;
    private JButton report;

    public Administrator() {
        frame = new JFrame("Administrator");
        frame.setBackground(Color.white);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Administrator");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(title);

        importProd = new JButton("Import");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(importProd);

        add = new JButton("Add Product");
        delete = new JButton("Delete Product");
        addComp=new JButton("Add Composed");

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.add(add);
        panel3.add(Box.createRigidArea(new Dimension(20, 0)));
        panel3.add(delete);
        panel3.add(Box.createRigidArea(new Dimension(20, 0)));
        panel3.add(addComp);

        name = new JLabel("name: ");
        text1 = new JTextField();
        rating = new JLabel("rating: ");
        text2 = new JTextField();
        calories = new JLabel("calories: ");
        text3 = new JTextField();
        proteins = new JLabel("proteins: ");
        text4 = new JTextField();
        fats = new JLabel("fats: ");
        text5 = new JTextField();
        sodium = new JLabel("sodium: ");
        text6 = new JTextField();
        price = new JLabel("price: ");
        text7 = new JTextField();

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(2, 7, 10, 10));
        panel4.add(name);
        panel4.add(rating);
        panel4.add(calories);
        panel4.add(proteins);
        panel4.add(fats);
        panel4.add(sodium);
        panel4.add(price);
        panel4.add(text1);
        panel4.add(text2);
        panel4.add(text3);
        panel4.add(text4);
        panel4.add(text5);
        panel4.add(text6);
        panel4.add(text7);

        newPrice = new JLabel("Price: ");
        updatePrice = new JTextField();
        modify = new JButton("Modify Product");

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        panel5.add(Box.createRigidArea(new Dimension(90, 0)));
        panel5.add(newPrice);
        panel5.add(Box.createRigidArea(new Dimension(20, 0)));
        panel5.add(updatePrice);
        panel5.add(Box.createRigidArea(new Dimension(20, 0)));
        panel5.add(modify);
        panel5.add(Box.createRigidArea(new Dimension(90, 0)));

        nameComposed = new JLabel("Composed Product: ");
        composed = new JTextField();
        prodComp = new JButton("Create Composed");

        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
        panel6.add(Box.createRigidArea(new Dimension(10, 0)));
        panel6.add(nameComposed);
        panel6.add(Box.createRigidArea(new Dimension(20, 0)));
        panel6.add(composed);
        panel6.add(Box.createRigidArea(new Dimension(20, 0)));
        panel6.add(prodComp);
        panel6.add(Box.createRigidArea(new Dimension(10, 0)));

        report = new JButton("Generate Reports");

        JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
        panel7.add(report);

        checked = new JLabel("");

        JPanel panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
        panel8.add(checked);

        JPanel panel9 = new JPanel();
        panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel1);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel2);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel3);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel4);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel5);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel6);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel7);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));
        panel9.add(panel8);
        panel9.add(Box.createRigidArea(new Dimension(0, 20)));

        frame.add(panel9);
        //frame.setVisible(true);
        //frame2.setLocationRelativeTo(null);
        frame.setBounds(10, 200, 647, 418);
        frame.pack();
    }

    public void addImportProductsActionListener(ActionListener importProd){
        this.importProd.addActionListener(importProd);
    }

    public void addAddProductActionListener(ActionListener add){
        this.add.addActionListener(add);
    }

    public void addDeleteProductActionListener(ActionListener delete){
        this.delete.addActionListener(delete);
    }

    public void addComposedProductActionListener(ActionListener addComp){
        this.addComp.addActionListener(addComp);
    }

    public void addModifyProductActionListener(ActionListener modify){
        this.modify.addActionListener(modify);
    }

    public void addCreateComposedActionListener(ActionListener createComp){
        this.prodComp.addActionListener(createComp);
    }

    public void addGenerateReportsActionListener(ActionListener reports){
        this.report.addActionListener(reports);
    }

    public void setChecked(String checked) {
        this.checked.setText(checked);
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

    public JLabel getChecked() {
        return checked;
    }

    public String getUpdatePrice() {
        return updatePrice.getText();
    }

    public String getComposed() {
        return composed.getText();
    }

    public JFrame getFrame() {
        return frame;
    }
}
