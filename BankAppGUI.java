import javax.swing.JFrame;

public class BankAppGUI {
    public volatile static String panel = "Login";
    public volatile static boolean changePanel = false;
    JFrame frame;
    Server server;
    BankAppGUI(Server server) {
        this.server = server;
        frame = new JFrame();
        frame.add(new BankLogIn());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Banking App Login");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        changePanel();
        
    }
    public void changePanel() {
        while(true) {
            if(changePanel) {
                switch (panel) {
                    case "Login":
                        frame.getContentPane().removeAll();
                        frame.add(new BankLogIn());
                        frame.setTitle("Banking App Login");
                        frame.revalidate();
                        frame.repaint();
                        changePanel = false;
                        break;
                    case "RegisterAccount":
                        frame.getContentPane().removeAll();
                        frame.add(new RegisterAccount(server));
                        frame.setTitle("Banking App Register");
                        frame.revalidate();
                        frame.repaint();
                        changePanel = false;
                        break;
                    case "MainMenu":
                        frame.getContentPane().removeAll();
                        frame.add(new MainMenu());
                        frame.setTitle("Banking App");
                        frame.revalidate();
                        frame.repaint();
                        changePanel = false;
                        break;
                }   
            }
        }
    }
}
