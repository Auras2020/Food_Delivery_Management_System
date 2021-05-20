package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogIn {

    private LogInWindow logInWindow;
    private  Administrator administrator;
    private Client client;
    private  Employee employee;
    private String adminName="Alex";
    private String adminPass="alx2021";
    private String employeeName="Bogdan";
    private String employeePass="bgd2021";

    public ControllerLogIn(LogInWindow logInWindow, Administrator administrator, Client client, Employee employee){
        this.logInWindow=logInWindow;
        this.administrator=administrator;
        this.client=client;
        this.employee=employee;

        this.logInWindow.addRegisterActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logInWindow.getText1().length()<3){
                    logInWindow.getSuccess1().setForeground(Color.RED);
                    logInWindow.setSuccess1("name is too short");
                    logInWindow.getLogIn1().setEnabled(false);
                }
                else if(logInWindow.getText2().length()<5){
                    logInWindow.getSuccess1().setForeground(Color.RED);
                    logInWindow.setSuccess1("password is too short");
                    logInWindow.getLogIn1().setEnabled(false);
                }
                else{
                    logInWindow.getSuccess1().setForeground(Color.GREEN);
                    logInWindow.setSuccess1("register successful");
                    logInWindow.getLogIn1().setEnabled(true);
                }
            }
        });

        this.logInWindow.addLogInActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInWindow.getFrame1().setVisible(false);
                logInWindow.getFrame2().setVisible(true);
            }
        });

        this.logInWindow.addLogIn1ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!logInWindow.getText3().equals(logInWindow.getText1())){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong client name");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else if(!logInWindow.getText4().equals(logInWindow.getText2())){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong client password");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else if(!logInWindow.getText5().equals(adminName)){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong administrator name");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else if(!logInWindow.getText6().equals(adminPass)){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong administrator password");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else if(!logInWindow.getText7().equals(employeeName)){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong employee name");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else if(!logInWindow.getText8().equals(employeePass)){
                    logInWindow.getSuccess2().setForeground(Color.RED);
                    logInWindow.setSuccess2("wrong employee password");
                    logInWindow.getFoodManagement().setEnabled(false);
                }
                else{
                    logInWindow.getSuccess2().setForeground(Color.GREEN);
                    logInWindow.setSuccess2("login successful");
                    logInWindow.getFoodManagement().setEnabled(true);
                }
            }
        });

        this.logInWindow.addFoodActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInWindow.getFrame2().setVisible(false);
                administrator.getFrame().setVisible(true);
                client.getFrame().setVisible(true);
                employee.getFrame().setVisible(true);
            }
        });
    }
}
