import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainMenu extends JPanel {
    MainMenu() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(BankApp.PANEL_WIDTH, BankApp.PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
    }
}
