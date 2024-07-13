package Main_App;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String password;
    private int accountNumber;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    private float balance = 0;

    public Account() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance() {
        return balance;
    }

    public int getAccountNumber() { return accountNumber; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String printTransactions() {

        StringBuilder sb = new StringBuilder();

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
            sb.append(transactions).append("\n");
        }

        return sb.toString();
    }
}
