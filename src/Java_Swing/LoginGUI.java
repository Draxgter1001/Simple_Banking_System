package Java_Swing;

import Main_App.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class LoginGUI extends BaseFrame{

    private HashMap<Integer, Account> accounts;
    private JTextField accountNumberField;
    private JPasswordField passwordField;

    public LoginGUI(HashMap<Integer, Account> accounts) {
        super("Tafshi's Banking System Login");
        this.accounts = accounts;

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                saveAccountsToFile();
            }
        });
    }

    @Override
    protected void addGuiComponents() {
        JLabel loginLabel = new JLabel("Tafshi's Banking System");
        loginLabel.setBounds(-10, 20, super.getWidth(), 40);

        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(20, 120, super.getWidth() - 30, 24);
        accountNumberLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(20, 160, super.getWidth() - 50, 40);
        accountNumberField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(accountNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 280, super.getWidth() - 50, 24);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 320, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 28));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20, 420, super.getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        JLabel registerLabel = new JLabel("<html><a href=\"#\">Click here to Register</a></html>");
        registerLabel.setBounds(0, 500, super.getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        registerLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterGUI(accounts).setVisible(true);
                LoginGUI.this.dispose();
            }
        });

    }

    private void handleLogin(){
        try{
            int accountNumber = Integer.parseInt(accountNumberField.getText());
            String password = String.valueOf(passwordField.getPassword());

            if(loginCheck(accounts, accountNumber, password)){
                new AppGUI(accounts.get(accountNumber), accounts).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Account Number or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Account Number", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean loginCheck(HashMap<Integer, Account> accounts, int accountNumber, String password){
        if(!accounts.containsKey(accountNumber)){
            return false;
        } else return accounts.get(accountNumber).getPassword().equals(password);
    }

    private void saveAccountsToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))){
            oos.writeObject(accounts);
            oos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
