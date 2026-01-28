package application;
import java.util.ArrayList;

public class AccountRepository {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void save(Account account) {
        accounts.add(account);
    }

    public Account findByIdAndPassword(int id, int password) {
        for (Account acc : accounts) {
            if (acc.getAccId() == id && acc.getPassword() == password) {
                return acc;
            }
        }
        return null;
    }

}
