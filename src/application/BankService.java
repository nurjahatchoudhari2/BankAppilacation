package application;

 
import java.util.ArrayList;
import java.util.Scanner;

public class BankService 
{
	private AccountRepository repository;
    private Scanner scanner = new Scanner(System.in);
    private static int accIdGenerator = 2023102701;
    
    public BankService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount()
    {
        try {
             System.out.print("First Name: ");
    		 String firstname = scanner.next();
    		 

             System.out.print("Last Name: ");
             String lastname = scanner.next();

             System.out.print("Initial Balance: ");
             double bal = scanner.nextDouble();

             System.out.print("Password: ");
             int p1 = scanner.nextInt();

             System.out.print("Confirm Password: ");
             int p2 = scanner.nextInt();

             if (p1 != p2) {
                 throw new Exception("Password mismatch");
             }

             Account acc = new Account(++accIdGenerator, firstname, lastname, bal, p1);
             repository.save(acc);

             System.out.println("Account Created Successfully");
             acc.showDetails();

         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }
   
    public void login() {
        try {
            System.out.print("Account ID: ");
            int id = scanner.nextInt();

            System.out.print("Password: ");
            int pass = scanner.nextInt();

            Account acc = findAccount(id, pass);
            System.out.println("Login Successful");

            accountMenu(acc);

        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private Account findAccount(int id, int pass) throws InvalidLoginException 
    {
    	 Account acc = repository.findByIdAndPassword(id, pass);
         if (acc == null) {
             throw new InvalidLoginException("Invalid account ID or password");
         }
         return acc;
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
                        System.out.println("Withdraw Successful");
                        break;

                    case 2:
                        System.out.print("Amount: ");
                        acc.deposit(scanner.nextDouble());
                        System.out.println("Deposit Successful");
                        break;

                    case 3:
                        System.out.println("Balance: " + acc.getBalance());
                        break;

                    case 4:
                        System.out.println("Logged out");
                        break;

                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 4);
    }
}
