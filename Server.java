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
}
