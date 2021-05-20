package presentation;

import business.*;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Employee implements Observer {

    private JFrame frame;

    private JLabel title;
    private JLabel label;

    private DeliveryService deliveryService;

    public Employee(DeliveryService deliveryService){
        this.deliveryService=deliveryService;

        frame = new JFrame("Employee");
        frame.setBackground(Color.white);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Employee");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(title);

        label=new JLabel("");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(label);
        panel2.add(Box.createRigidArea(new Dimension(350, 0)));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(Box.createRigidArea(new Dimension(0, 20)));
        panel3.add(panel1);
        panel3.add(Box.createRigidArea(new Dimension(0, 20)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(0, 20)));

        frame.add(panel3);
        //frame.setVisible(true);
        //frame2.setLocationRelativeTo(null);
        frame.setBounds(970, 300, 647, 418);
        frame.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Order){
            label.setForeground(Color.BLUE);
            String products="";
            int i=0;
            if(deliveryService.getOrder().get(((Order)arg))!=null){
                for(MenuItem m: deliveryService.getOrder().get(((Order)arg))){
                    if(i==deliveryService.getOrder().get(((Order)arg)).size()-1){
                        products+=m.getTitle();
                    }
                    else{
                        products+=m.getTitle() + ", ";
                    }
                    i++;
                }
            }
            label.setText("Order with id " + ((Order)arg).getOrderID() + " containing products " +
                     products  + " has been performed by" +
                    " client with id " + ((Order)arg).getClientID());
        }
        else{
            label.setForeground(Color.RED);
            label.setText("Other changes");
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
