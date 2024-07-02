
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    static Scanner input = new Scanner(System.in);
    static HashMap<Integer, Person> people = new HashMap<>();
    static int id;

    public static void main(String[] args) {
        app();
    }

    private static void app(){

        System.out.println("Tafshi's Banking System");

        while(true){
            System.out.print("Please enter your choice (1 to Register/ 2 to Login): ");
            int option = input.nextInt();

            switch(option){
                case 1:
                    register();
//                    for(Map.Entry<Integer, Person> entry : people.entrySet()){
//                        System.out.println(entry.getKey() + " " + entry.getValue());
//                    }
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
        id = random.nextInt(2000);

        people.put(id, person);
        System.out.println("This is your account number: " + id);

    }

    private static void login(){

    }
}