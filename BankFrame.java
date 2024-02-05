import javax.swing.JFrame;

public class BankFrame extends JFrame {
    BankFrame() {
        this.add(new BankLogIn());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Bank App");

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}