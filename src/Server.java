package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    Connection connection;
    Server() {
        String url = "jdbc:mysql://localhost:3306/javabase";
        String username = "java";
        String password = "password";
        System.out.println("Connecting database ...");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.connection;
    }
    public float updateBalance() {
        float userBalance = 0;
        String checkBalanceQuery = "select ROUND(wallet.balance, 2) \n" + //
                        "from wallet inner join users on walletID = userid\n" + //
                        "where users.username = ?;";

        try {
            PreparedStatement checkBalanceStmt = connection.prepareStatement(checkBalanceQuery);
            checkBalanceStmt.setString(1, BankAppGUI.username);
            ResultSet checkBalanceResult = checkBalanceStmt.executeQuery();
            checkBalanceResult.next();
            userBalance = Float.parseFloat(checkBalanceResult.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBalance;
    }
    public boolean validUser(String user) {
        String query = "select username from users where username = ? ;";
        PreparedStatement checkValidUser;
        try {
            checkValidUser = connection.prepareStatement(query);
            checkValidUser.setString(1, user);
            ResultSet checkValidUserResult = checkValidUser.executeQuery();
            if (!checkValidUserResult.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public int getUserID() {
        String getUserID = "select userId from users where username = ?;";
        int userID = 0;
        try {
            PreparedStatement getUserIDStmt = connection.prepareStatement(getUserID);
            getUserIDStmt.setString(1, BankAppGUI.username);
            ResultSet getUserIDResult = getUserIDStmt.executeQuery();
            getUserIDResult.next();
            userID = getUserIDResult.getInt(1);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    public void addTransaction(String type, float amount) {
        String insert = " insert into user_transaction (transactionID, transaction_type, transaction_date, transaction_amount, userID)" 
                + " values (?, ?, NOW(), ?, ?)";
        String idQuery = "select count(transactionID) from user_transaction";
        try {

            PreparedStatement stmtQuery = connection.prepareStatement(idQuery);
            ResultSet rs = stmtQuery.executeQuery();
            rs.next();
            int transactionID = rs.getInt(1) + 1;

            PreparedStatement insertStmt = connection.prepareStatement(insert);
            insertStmt.setInt(1, transactionID);
            insertStmt.setString(2, type);
            insertStmt.setFloat(3, amount);
            insertStmt.setInt(4, this.getUserID());
            insertStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
