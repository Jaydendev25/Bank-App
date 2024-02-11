import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TransferFrame {
    JPanel transferPanel;
    Server server;
    float userBalance;
    TransferFrame(Server server) {
        this.server = server;
        this.userBalance = server.updateBalance();
        JFrame transferFrame = new JFrame();
        transferPanel = new JPanel();
        transferPanel.setLayout(null);
        transferPanel.setPreferredSize(new Dimension(MainMenu.NEW_FRAME_WIDTH, MainMenu.NEW_FRAME_HEIGHT));
        transferPanel.setBackground(Color.WHITE);
        transferFrame.add(transferPanel);
        transferFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transferFrame.setResizable(false);
        transferFrame.setTitle("Transfer");

        transferFrame.pack();
        transferFrame.setVisible(true);
        transferFrame.setLocationRelativeTo(null);

        addGUI();
    }
    private void addGUI() {
    }
}
