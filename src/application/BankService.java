package application;

 
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Scanner;

public class BankService {

    private DBConnect db;
    private Scanner scanner = new Scanner(System.in);
    private static int accIdGenerator = 2023102701;

    public BankService(AccountRepository repository) {
        try {
            db = new DBConnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createAccount() {
        try {
            System.out.print("First Name: ");
            String firstname = scanner.next();

            System.out.print("Last Name: ");
            String lastname = scanner.next();

            System.out.print("Initial Balance: ");
            double bal = scanner.nextDouble();

            System.out.print("Password: ");
            String p1 = scanner.next();

            System.out.print("Confirm Password: ");
            String p2 = scanner.next();

            if (!p1.equals(p2)) {
                throw new Exception("Password mismatch");
            }

            int accId = db.getNextAccountId();
            db.saveAccount(accId, firstname, lastname, bal, p1);

            System.out.println("Account Created Successfully");
            System.out.println("Your Account ID: " + accId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        try {
            System.out.print("Account ID: ");
            int id = scanner.nextInt();

            System.out.print("Password: ");
            String pass = scanner.next();

            Account acc = db.loginAccount(id, pass);

             
            System.out.println("\nLogin Successful");
            System.out.println("Account Details:");
            acc.showDetails();
            System.out.println("Balance : " + acc.getBalance());

            accountMenu(acc);  

        } catch (InvalidLoginException e) {
            
            System.out.println("Account not found");
        } catch (Exception e1)
        {
        	e1.getMessage();
        }
    }



    private void accountMenu(Account acc) {
        int choice;
        do {
            System.out.println("\n1.Withdraw\n2.Deposit\n3.Check Balance\n4.Exit");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Amount: ");
                        acc.withdraw(scanner.nextDouble());
                        db.updateBalance(acc.getAccId(), acc.getBalance());
                        System.out.println("Withdraw Successful");
                        break;

                    case 2:
                        System.out.print("Amount: ");
                        acc.deposit(scanner.nextDouble());
                        db.updateBalance(acc.getAccId(), acc.getBalance());
                        System.out.println("Deposit Successful");
                        break;

                    case 3:
                        System.out.println("Balance: " + acc.getBalance());
                        break;

                    case 4:
                        System.out.println("Logged out");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 4);
    }
    
    public void deleteAccount() {
        try {
            System.out.print("Enter Account ID to delete: ");
            int id = scanner.nextInt();

            boolean deleted = db.deleteAccount(id);

            if (deleted) {
                System.out.println("Account deleted successfully");
            } else {
                System.out.println("Account not found");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
