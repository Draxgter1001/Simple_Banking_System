package Java_Swing;

import Main_App.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class AppGUI extends BaseFrame {

    private HashMap<Integer, Account> accounts;
    private JLabel currentBalanceLabel;

    public AppGUI(Account account, HashMap<Integer, Account> accounts) {
        super("Tafshi's Banking System", account);
        this.accounts = accounts;
        initializeUI();

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                saveAccountsToFile();
            }
        });
    }

    @Override
    protected void addGuiComponents() {
        // This method is now empty because UI initialization is moved to initializeUI()
    }

    private void initializeUI() {
        String welcomeMessage = "Welcome " + account.getFirstName() + " " + account.getLastName();

        JLabel appLabel = new JLabel("Tafshi's Banking System");
        appLabel.setBounds(-10, 20, super.getWidth(), 40);
        appLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        appLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(appLabel);

        JLabel welcomeLabel = new JLabel(welcomeMessage);
        welcomeLabel.setBounds(0, 60, super.getWidth(), 40);
        welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);

        JLabel accountLabel = new JLabel("Account Number: " + account.getAccountNumber());
        accountLabel.setBounds(0, 100, super.getWidth(), 40);
        accountLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(accountLabel);

        currentBalanceLabel = new JLabel("Balance: £" + account.getBalance());
        currentBalanceLabel.setBounds(0, 140, super.getWidth(), 40);
        currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalanceLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(18, 180, getWidth() - 50, 40);
        depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
        depositButton.setHorizontalAlignment(SwingConstants.CENTER);
        depositButton.setActionCommand("Deposit");
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(18, 240, getWidth() - 50, 40);
        withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
        withdrawButton.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawButton.setActionCommand("Withdraw");
        add(withdrawButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(18, 300, getWidth() - 50, 40);
        transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
        transferButton.setHorizontalAlignment(SwingConstants.CENTER);
        transferButton.setActionCommand("Transfer");
        add(transferButton);

        JButton transactionHistoryButton = new JButton("Transaction History");
        transactionHistoryButton.setBounds(18, 360, getWidth() - 50, 40);
        transactionHistoryButton.setFont(new Font("Dialog", Font.BOLD, 22));
        transactionHistoryButton.setHorizontalAlignment(SwingConstants.CENTER);
        transactionHistoryButton.setActionCommand("Transaction History");
        add(transactionHistoryButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(18, 460, getWidth() - 50, 40);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 22));
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.setActionCommand("Logout");
        add(logoutButton);

        AppActionListener actionListener = new AppActionListener(account, accounts, this);
        depositButton.addActionListener(actionListener);
        withdrawButton.addActionListener(actionListener);
        transferButton.addActionListener(actionListener);
        transactionHistoryButton.addActionListener(actionListener);
        logoutButton.addActionListener(actionListener);
    }

    public void updateBalanceLabel() {
        currentBalanceLabel.setText("Balance: £" + account.getBalance());
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
