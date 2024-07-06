package Main_App;

public class Transaction {

    private final int accountNumber;
    private final String name;
    private final double amount;

    Transaction(int accountNumber, String name, double amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;

    }

    public String toString() {
        return accountNumber + " " + name + " " + amount;
    }

}
