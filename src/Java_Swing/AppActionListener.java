package Java_Swing;

import Main_App.Account;
import Main_App.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AppActionListener implements ActionListener {

    private final Account account;
    private final HashMap<Integer, Account> accounts;
    private final AppGUI appGUI;

    public AppActionListener(Account account, HashMap<Integer, Account> accounts, AppGUI appGUI) {
        this.account = account;
        this.accounts = accounts;
        this.appGUI = appGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Deposit":
                handleDeposit();
                break;
            case "Withdraw":
                handleWithdraw();
                break;
            case "Transfer":
                handleTransfer();
                break;
            case "Transaction History":
                handleTransactionHistory();
                break;
            case "Logout":
                handleLogout();
                break;
            default:
                throw new IllegalArgumentException("Unexpected command: " + command);
        }
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog(appGUI, "Enter deposit amount:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                float amount = Float.parseFloat(amountStr);
                account.setBalance(account.getBalance() + amount);
                JOptionPane.showMessageDialog(appGUI, "Deposit successful! New balance: £" + account.getBalance());
                appGUI.updateBalanceLabel(); // Update the balance label
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(appGUI, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog(appGUI, "Enter withdrawal amount:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                float amount = Float.parseFloat(amountStr);
                if (amount <= account.getBalance()) {
                    account.setBalance(account.getBalance() - amount);
                    JOptionPane.showMessageDialog(appGUI, "Withdrawal successful! New balance: £" + account.getBalance());
                    appGUI.updateBalanceLabel(); // Update the balance label
                } else {
                    JOptionPane.showMessageDialog(appGUI, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(appGUI, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleTransfer() {
        try {
            String transferAmountStr = JOptionPane.showInputDialog(appGUI, "Enter transfer amount:");
            if (transferAmountStr != null && !transferAmountStr.isEmpty()) {
                float transferAmount = Float.parseFloat(transferAmountStr);
                if (transferAmount > account.getBalance()) {
                    JOptionPane.showMessageDialog(appGUI, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String transferPersonIdStr = JOptionPane.showInputDialog(appGUI, "Enter the account number of the person you want to transfer to:");
                if (transferPersonIdStr != null && !transferPersonIdStr.isEmpty()) {
                    int transferPersonId = Integer.parseInt(transferPersonIdStr);

                    if (accounts.containsKey(transferPersonId)) {
                        account.setBalance(account.getBalance() - transferAmount);
                        Account transferAccount = accounts.get(transferPersonId);
                        transferAccount.setBalance(transferAccount.getBalance() + transferAmount);

                        Transaction transaction = new Transaction(transferPersonId, transferAccount.getFirstName(), transferAmount);
                        account.addTransaction(transaction);

                        JOptionPane.showMessageDialog(appGUI, "Transfer successful!");
                        appGUI.updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(appGUI, "That account does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(appGUI, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleTransactionHistory() {
        String transActionHistory = account.printTransactions();
        JOptionPane.showMessageDialog(appGUI, transActionHistory, "Transaction history", JOptionPane.INFORMATION_MESSAGE);

    }

    private void handleLogout() {
        new LoginGUI(accounts).setVisible(true);
        appGUI.dispose();
    }
}
