package application;

public class Account {
	    private int accId;
	    private String firstName;
	    private String lastName;
	    private double balance;
	    private int password;

	    public Account(int accId, String firstName, String lastName, double balance, int password) {
	        this.accId = accId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.balance = balance;
	        this.password = password;
	    }

	    public int getAccId() {
	        return accId;
	    }

	    public int getPassword() {
	        return password;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void deposit(double amount) {
	        balance += amount;
	    }

	    public void withdraw(double amount) throws Exception {
	        if (amount > balance) {
	            throw new Exception("Insufficient Balance");
	        }
	        balance -= amount;
	    }

	    public void showDetails() {
	        System.out.println("Name : " + firstName + " " + lastName);
	        System.out.println("Account No : " + accId);
	    }
}
