import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankApp {
    public static final int PANEL_WIDTH = 500;
    public static final int PANEL_HEIGHT = 600;
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/javabase";
        String username = "java";
        String password = "password";

        System.out.println("Connecting database ...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        new BankAppGUI();

    }
}
