package application;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AccountRepository repo = new AccountRepository();
        BankService bank = new BankService(repo);
        
        int choice;

        do {
            System.out.println("WELCOME TO MY BANK");
            System.out.println("1.Create Account");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;

                case 2:
                    bank.login();
                    break;

                case 3:
                    System.out.println("Thank you for using our bank!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 3);
    }
}