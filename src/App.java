import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class App {

    static Scanner input = new Scanner(System.in);
    static HashMap<Integer, Person> accounts = new HashMap<>();
    static int accountNumber;

    App(){
    }

    public static void app(){

        System.out.println("Tafshi's Banking System");

        while(true){
            System.out.print("Please enter your choice (1 to Register/ 2 to Login): ");
            int option = input.nextInt();

            switch(option){
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
            }
        }
    }

    private static void register(){

        System.out.print("Please enter your first name: ");
        String firstName = input.next();
        System.out.print("Please enter your last name: ");
        String lastName = input.next();
        System.out.print("Please enter your password: ");
        String password = input.next();

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPassword(password);

        Random random = new Random();
        int id = random.nextInt(2000);

        accounts.put(id, person);
        System.out.println("This is your account number: " + id);

    }

    private static void login(){
        String accountPassword;
        do {
            System.out.print("Please enter your account number: ");
            accountNumber = input.nextInt();
            System.out.print("Please enter your password: ");
            accountPassword = input.next();
        }while(!loginCheck(accounts, accountNumber, accountPassword));
        bank();
    }

    private static void bank(){
        System.out.println("\nTafshi's Bank");

        System.out.println("Account number: " + accountNumber);
        System.out.println("Account name: " + accounts.get(accountNumber).getFirstName() + " " + accounts.get(accountNumber).getLastName());
        System.out.println("Account balance: " + accounts.get(accountNumber).getBalance());

        System.out.print("\nEnter 1 for Deposit or 2 for Withdrawal: ");
        int option = input.nextInt();
        switch(option){
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
        }
    }

    private static Boolean loginCheck(HashMap<Integer, Person> map, int accountNum, String password){

        if(!map.containsKey(accountNum)){
            System.out.println("This account does not exist");
            return false;
        }else if(!map.get(accountNum).getPassword().equals(password)) {
            System.out.println("Wrong password");
            return false;
        }else {
            System.out.println("Login successful");
            return true;
        }
    }

    private static void deposit(){
        System.out.print("Enter deposit amount: ");
        float depositAmount = input.nextFloat();
        accounts.get(accountNumber).setBalance(accounts.get(accountNumber).getBalance() + depositAmount);
    }

    private static void withdraw(){
        System.out.println("Enter withdrawal amount: ");
        float withdrawalAmount = input.nextFloat();
        if(withdrawalAmount > accounts.get(accountNumber).getBalance()){
            accounts.get(accountNumber).setBalance(accounts.get(accountNumber).getBalance() - withdrawalAmount);
        } else if (accounts.get(accountNumber).getBalance() <= 0) {
            System.out.println("Insufficient balance");
        }
    }
}
