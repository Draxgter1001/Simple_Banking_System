package Java_Swing;

import Main_App.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

public class RegisterGUI extends BaseFrame {

    private JTextField firstNameField, lastNameField;
    private JPasswordField passwordField;
    private HashMap<Integer, Account> accounts;

    public RegisterGUI(HashMap<Integer, Account> accounts) {
        super("Tafshi's Banking System Register");
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
        JLabel registerLabel = new JLabel("Tafshi's Banking System");
        registerLabel.setBounds(-10, 20, super.getWidth(), 40);

        registerLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 120, super.getWidth() - 30, 24);
        firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(20, 160, super.getWidth() - 50, 40);
        firstNameField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setBounds(20, 220, super.getWidth() - 30, 24);
        lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(20, 260, super.getWidth() - 50, 40);
        lastNameField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(lastNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 320, super.getWidth() - 50, 24);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 360, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 28));
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20, 420, super.getWidth() - 50, 40);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        JLabel loginLabel = new JLabel("<html><a href=\"#\">Click here to Login</a></html>");
        loginLabel.setBounds(0, 500, super.getWidth() - 10, 30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        loginLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginGUI(accounts).setVisible(true);
                RegisterGUI.this.dispose();
            }
        });
    }

    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = new String(passwordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPassword(password);

        Random random = new Random();
        int id = 1000 + random.nextInt(1000);
        account.setAccountNumber(id);

        accounts.put(id, account);
        JOptionPane.showMessageDialog(this, "Account has been registered successfully,\n " +
                    "        Your Account Number is: " + account.getAccountNumber(),
                "Registration Success", JOptionPane.INFORMATION_MESSAGE);

        new LoginGUI(accounts).setVisible(true);
        this.dispose();
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
