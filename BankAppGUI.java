import javax.swing.JFrame;

public class BankAppGUI {
    public static String panel = "Login";
    public static boolean changePanel = false;
    JFrame frame;
    BankAppGUI() {
       frame = new JFrame();
       frame.add(new BankLogIn());
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setTitle("Bank App");

       frame.pack();
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);
       changePanel();
        
    }
    public void changePanel() {
        while(true) {
            System.out.println("t");
            if(changePanel) {
                switch (panel) {
                    case "Login":
                        frame.add(new BankLogIn());
                        frame.revalidate();
                        frame.repaint();
                        changePanel = false;
                        break;
                    case "RegisterAccount":
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.repaint();
                        changePanel = false;
                }   
            }
        }
    }
}
