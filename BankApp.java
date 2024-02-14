
public class BankApp {
    public static final int PANEL_WIDTH = 500;
    public static final int PANEL_HEIGHT = 600;
    public static void main(String[] args) {
        Server server = new Server();
        new BankAppGUI(server);
    }
}
