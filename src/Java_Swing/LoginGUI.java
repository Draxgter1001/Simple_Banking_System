package Java_Swing;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends BaseFrame{

    public LoginGUI() {
        super("Tafshi's Banking System Login");
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

        JTextField accountNumberField = new JTextField();
        accountNumberField.setBounds(20, 160, super.getWidth() - 50, 40);
        accountNumberField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(accountNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 280, super.getWidth() - 50, 24);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 320, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 28));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20, 420, super.getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        add(loginButton);

    }
}
