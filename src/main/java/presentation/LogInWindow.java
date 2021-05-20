package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInWindow extends JFrame {

    private JFrame frame1;
    private JFrame frame2;

    private JLabel title;
    private JLabel name;
    private JLabel pass;
    private JLabel title1;
    private JLabel name1;
    private JLabel pass1;
    private JLabel title2;
    private JLabel name2;
    private JLabel pass2;
    private JLabel title3;
    private JLabel name3;
    private JLabel pass3;
    private JLabel success1;
    private JLabel success2;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JTextField text8;

    private JButton register;
    private JButton logIn1;
    private JButton logIn2;
    private JButton foodManagement;

    public LogInWindow(){
        //register client
        frame1=new JFrame("Register");
        frame1.setBackground(Color.white);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title=new JLabel("Register Client");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(title);

        name=new JLabel("name: ");
        pass=new JLabel("password: ");
        text1=new JTextField();
        text2=new JPasswordField();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 2, 10, 10));
        panel2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2.add(name);
        panel2.add(text1);
        panel2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2.add(pass);
        panel2.add(text2);
        panel2.add( Box.createRigidArea(new Dimension(10,0)) );

        register=new JButton("Register");
        success1=new JLabel("");
        logIn1=new JButton("Log in");
        logIn1.setEnabled(false);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.add(register);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel4.add(success1);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        panel5.add(logIn1);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6.add(panel1);
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6.add(panel2);
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6.add(panel3);
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6.add(panel4);
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6.add(panel5);
        panel6.add( Box.createRigidArea(new Dimension(0,20)) );

        frame1.add(panel6);
        frame1.setVisible(true);
        //frame1.setLocationRelativeTo(null);
        frame1.setBounds(500, 200, 647, 418);
        frame1.pack();

        //log in client, administrator, employee
        frame2=new JFrame("Log in");
        frame2.setBackground(Color.white);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title1=new JLabel("Log in client");

        JPanel panel1_1 = new JPanel();
        panel1_1.setLayout(new BoxLayout(panel1_1, BoxLayout.X_AXIS));
        panel1_1.add(title1);

        name1=new JLabel("name: ");
        pass1=new JLabel("password: ");
        text3=new JTextField();
        text4=new JPasswordField();

        JPanel panel2_1 = new JPanel();
        panel2_1.setLayout(new GridLayout(2, 2, 10, 10));
        panel2_1.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_1.add(name1);
        panel2_1.add(text3);
        panel2_1.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_1.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_1.add(pass1);
        panel2_1.add(text4);
        panel2_1.add( Box.createRigidArea(new Dimension(10,0)) );

        title2=new JLabel("Log in administrator");

        JPanel panel1_2 = new JPanel();
        panel1_2.setLayout(new BoxLayout(panel1_2, BoxLayout.X_AXIS));
        panel1_2.add(title2);

        name2=new JLabel("name: ");
        pass2=new JLabel("password: ");
        text5=new JTextField();
        text6=new JPasswordField();

        JPanel panel2_2 = new JPanel();
        panel2_2.setLayout(new GridLayout(2, 2, 10, 10));
        panel2_2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_2.add(name2);
        panel2_2.add(text5);
        panel2_2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_2.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_2.add(pass2);
        panel2_2.add(text6);
        panel2_2.add( Box.createRigidArea(new Dimension(10,0)) );

        title3=new JLabel("Log in employee");

        JPanel panel1_3 = new JPanel();
        panel1_3.setLayout(new BoxLayout(panel1_3, BoxLayout.X_AXIS));
        panel1_3.add(title3);

        name3=new JLabel("name: ");
        pass3=new JLabel("password: ");
        text7=new JTextField();
        text8=new JPasswordField();

        JPanel panel2_3 = new JPanel();
        panel2_3.setLayout(new GridLayout(2, 2, 10, 10));
        panel2_3.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_3.add(name3);
        panel2_3.add(text7);
        panel2_3.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_3.add( Box.createRigidArea(new Dimension(10,0)) );
        panel2_3.add(pass3);
        panel2_3.add(text8);
        panel2_3.add( Box.createRigidArea(new Dimension(10,0)) );

        logIn2=new JButton("Log in");
        success2=new JLabel("");
        foodManagement=new JButton("Food Management");
        foodManagement.setEnabled(false);

        JPanel panel3_1 = new JPanel();
        panel3_1.setLayout(new BoxLayout(panel3_1, BoxLayout.X_AXIS));
        panel3_1.add(logIn2);

        JPanel panel4_1 = new JPanel();
        panel4_1.setLayout(new BoxLayout(panel4_1, BoxLayout.X_AXIS));
        panel4_1.add(success2);

        JPanel panel5_1 = new JPanel();
        panel5_1.setLayout(new BoxLayout(panel5_1, BoxLayout.X_AXIS));
        panel5_1.add(foodManagement);

        JPanel panel6_1 = new JPanel();
        panel6_1.setLayout(new BoxLayout(panel6_1, BoxLayout.Y_AXIS));
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel1_1);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel2_1);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel1_2);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel2_2);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel1_3);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel2_3);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel3_1);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel4_1);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );
        panel6_1.add(panel5_1);
        panel6_1.add( Box.createRigidArea(new Dimension(0,20)) );

        frame2.add(panel6_1);
        //frame2.setVisible(true);
        //frame2.setLocationRelativeTo(null);
        frame2.setBounds(500, 200, 647, 418);
        frame2.pack();
    }

    public void addRegisterActionListener(ActionListener registerBut){
        this.register.addActionListener(registerBut);
    }

    public void addLogInActionListener(ActionListener logInBut){
        this.logIn1.addActionListener(logInBut);
    }

    public void addLogIn1ActionListener(ActionListener logInBut1){
        this.logIn2.addActionListener(logInBut1);
    }

    public void addFoodActionListener(ActionListener foodBut){
        this.foodManagement.addActionListener(foodBut);
    }

    public String getText1() {
        return text1.getText();
    }

    public void setText1(String text1) {
        this.text1.setText(text1);
    }

    public String getText2() {
        return text2.getText();
    }

    public void setText2(String text2) {
        this.text2.setText(text2);
    }

    public String getText3() {
        return text3.getText();
    }

    public void setText3(String text3) {
        this.text3.setText(text3);
    }

    public String getText4() {
        return text4.getText();
    }

    public void setText4(String text4) {
        this.text4.setText(text4);
    }

    public String getText5() {
        return text5.getText();
    }

    public void setText5(String text5) {
        this.text5.setText(text5);
    }

    public String getText6() {
        return text6.getText();
    }

    public void setText6(String text6) {
        this.text6.setText(text6);
    }

    public String getText7() {
        return text7.getText();
    }

    public void setText7(String text7) {
        this.text7.setText(text7);
    }

    public String getText8() {
        return text8.getText();
    }

    public void setText8(String text8) {
        this.text8.setText(text8);
    }

    public JFrame getFrame1() {
        return frame1;
    }

    public JFrame getFrame2() {
        return frame2;
    }

    public JLabel getSuccess1() {
        return success1;
    }

    public JLabel getSuccess2() {
        return success2;
    }

    public void setSuccess1(String success1) {
        this.success1.setText(success1);
    }

    public void setSuccess2(String success2) {
        this.success2.setText(success2);
    }

    public JButton getLogIn1() {
        return logIn1;
    }

    public JButton getFoodManagement() {
        return foodManagement;
    }
}
