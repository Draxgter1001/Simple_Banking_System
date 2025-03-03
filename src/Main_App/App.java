package Main_App;

import Java_Swing.LoginGUI;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class App {

    private static HashMap<Integer, Account> accounts = new HashMap<>();

    public static void app(){

        File f = new File("accounts.dat");

        if(f.exists()){
            accounts = loadAccounts();
        }
        SwingUtilities.invokeLater(() -> new LoginGUI(accounts).setVisible(true));

//        System.out.println("Tafshi's Banking System");
//        boolean exit = true;
//
//        while(exit) {
//            try {
//                System.out.print("Please enter your choice (1 to Register/ 2 to Login/ 3 to Exit): ");
//                int option = input.nextInt();
//
//                switch (option) {
//                    case 1:
//                        register();
//                        break;
//                    case 2:
//                        login();
//                        break;
//                    case 3:
//                        exit = false;
//                    default:
//                        System.out.println("Please enter 1 or 2.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Try again.");
//                input.next();
//            }
//        }
    }

//    private static void register(){
//
//        System.out.print("Please enter your first name: ");
//        String firstName = input.next();
//        System.out.print("Please enter your last name: ");
//        String lastName = input.next();
//        System.out.print("Please enter your password: ");
//        String password = input.next();
//
//        Account account = new Account();
//        account.setFirstName(firstName);
//        account.setLastName(lastName);
//        account.setPassword(password);
//
//        Random random = new Random();
//        int id = random.nextInt(2000);
//        account.setAccountNumber(id);
//
//        accounts.put(id, account);
//        System.out.println("This is your account number: " + id);
//
//    }
//
//    private static void login(){
//        String accountPassword = "";
//        boolean validInput = false;
//
//        do {
//            try{
//                System.out.print("Please enter your account number: ");
//                accountNumber = input.nextInt();
//                System.out.print("Please enter your password: ");
//                accountPassword = input.next();
//                validInput = true;
//            }catch (InputMismatchException e){
//                System.out.println("Invalid input. Try again.");
//                input.next();
//            }
//        }while(!validInput || !loginCheck(accounts, accountNumber, accountPassword));
//        bank();
//    }
//
//    private static void bank(){
//
//        boolean exit = false;
////        SwingUtilities.invokeLater(() -> new AppGUI(accounts.get(accountNumber)).setVisible(true));
//
//        do {
//            System.out.println("\nTafshi's Banking System");
//
//            System.out.println("Account number: " + accountNumber);
//            System.out.println("Account name: " + accounts.get(accountNumber).getFirstName() + " " + accounts.get(accountNumber).getLastName());
//            System.out.println("Account balance: " + accounts.get(accountNumber).getBalance());
//
//            try{
//                System.out.println("""
//
//                    Enter 1 for Deposit
//                    Enter 2 for Withdrawal
//                    Enter 3 to transfer money
//                    Enter 4 to check transaction history
//                    Enter any number to log out\s""");
//                System.out.println();
//                System.out.print("Enter your option: ");
//                int option = input.nextInt();
//                switch(option){
//                    case 1:
//                        deposit();
//                        break;
//                    case 2:
//                        withdraw();
//                        break;
//                    case 3:
//                        transfer();
//                        break;
//                    case 4:
//                        transactions();
//                        break;
//                    default:
//                        exit = true;
//                }
//            }catch (InputMismatchException e){
//                System.out.println("Invalid input. Try again.");
//                input.next();
//            }
//        }while(!exit);
//    }
//
//    private static Boolean loginCheck(HashMap<Integer, Account> map, int accountNum, String password){
//
//        if(!map.containsKey(accountNum)){
//            System.out.println("This account does not exist");
//            return false;
//        }else if(!map.get(accountNum).getPassword().equals(password)) {
//            System.out.println("Wrong password");
//            return false;
//        }else {
//            System.out.println("Login successful");
//            return true;
//        }
//    }
//
//    private static void deposit(){
//        System.out.print("Enter deposit amount: ");
//        float depositAmount = input.nextFloat();
//        accounts.get(accountNumber).setBalance(accounts.get(accountNumber).getBalance() + depositAmount);
//        System.out.println("Deposit successful!");
//    }
//
//    private static void withdraw(){
//        System.out.print("Enter withdrawal amount: ");
//        float withdrawalAmount = input.nextFloat();
//        if(withdrawalAmount < accounts.get(accountNumber).getBalance()){
//            accounts.get(accountNumber).setBalance(accounts.get(accountNumber).getBalance() - withdrawalAmount);
//            System.out.println("Withdrawal successful!");
//        } else if (accounts.get(accountNumber).getBalance() <= 0 || accounts.get(accountNumber).getBalance() < withdrawalAmount) {
//            System.out.println("Insufficient balance");
//        }
//    }
//
//    private static void transfer(){
//
//        try {
//            System.out.print("Enter transfer amount: ");
//            float transferAmount = input.nextFloat();
//            accounts.get(accountNumber).setBalance(accounts.get(accountNumber).getBalance() - transferAmount);
//            System.out.print("Enter the account number of the person you want to transfer to: ");
//            int transferPersonId = input.nextInt();
//            accounts.get(transferPersonId).setBalance(accounts.get(transferPersonId).getBalance() + transferAmount);
//
//            Transaction transaction = new Transaction(transferPersonId, accounts.get(transferPersonId).getFirstName(), transferAmount);
//            System.out.println("Transfer successful!");
//
//            accounts.get(accountNumber).addTransaction(transaction);
//        }catch (NullPointerException e){
//            System.out.println("That account does not exist");
//        }
//    }
//
//    private static void transactions(){
//        accounts.get(accountNumber).printTransactions();
//    }

    @SuppressWarnings("unchecked")
    public static HashMap<Integer, Account> loadAccounts(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))){

            return (HashMap<Integer, Account>) ois.readObject();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
