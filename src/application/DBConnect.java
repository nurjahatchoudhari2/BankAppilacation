package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class DBConnect {

    private Connection connection;

    public DBConnect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankaccount",
                "root",
                "Noor@0209"
        );
    }

    public void saveAccount(int accId, String fname, String lname, double balance, String pass)
            throws SQLException {

        String query = "INSERT INTO account VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, accId);
        ps.setString(2, fname);
        ps.setString(3, lname);
        ps.setDouble(4, balance);
        ps.setString(5, pass);

        ps.executeUpdate();
        System.out.println("Account Saved Successfully");
    }

    public Account loginAccount(int accId, String pass) throws Exception {

        String query = "SELECT * FROM account WHERE accid=? AND pass=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, accId);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Account(
                    rs.getInt(1),   // accid
                    rs.getString(2),// first name
                    rs.getString(3),// last name
                    rs.getDouble(4),// balance
                    rs.getString(5) // password
            );
        } else {
            throw new Exception("Account not found");
        }
    }


    public void updateBalance(int accId, double balance) throws SQLException {
        String query = "UPDATE account SET balance=? WHERE accid=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDouble(1, balance);
        ps.setInt(2, accId);
        ps.executeUpdate();
    }
    
    public boolean deleteAccount(int accId) throws SQLException {

        String query = "DELETE FROM account WHERE accid=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, accId);

        int rows = ps.executeUpdate();
        return rows > 0; 
    }

    
    public int getNextAccountId() throws Exception {

        String query = "SELECT MAX(accid) FROM account";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) != 0) {
            return rs.getInt(1) + 1;
        } else {
            return 2023102702;  
        }
    }

}
