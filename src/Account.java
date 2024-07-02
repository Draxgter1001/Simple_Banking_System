import java.util.ArrayList;

public class Account {

    private String firstName;
    private String lastName;
    private String password;
    private final ArrayList<Transfer> transfers = new ArrayList<>();

    private float balance = 0;

    Account() {
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    public void printTransfers() {
        for (Transfer transfer : transfers) {
            System.out.println(transfer.toString());
        }
    }
}
