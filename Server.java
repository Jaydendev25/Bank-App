import java.sql.Connection;
import java.sql.DriverManager;
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
}
