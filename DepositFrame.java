
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DepositFrame {
    private final int DEPOSIT_PANEL_WIDTH = 450;
    private final int DEPOSIT_PANEL_HEIGHT = 500;
    JPanel depositPanel;
    DepositFrame() {
        JFrame depositFrame = new JFrame();
        depositPanel = new JPanel();
        depositPanel.setLayout(null);
        depositPanel.setPreferredSize(new Dimension(DEPOSIT_PANEL_WIDTH, DEPOSIT_PANEL_HEIGHT));
        depositPanel.setBackground(Color.WHITE);
        depositFrame.add(depositPanel);
        depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositFrame.setResizable(false);
        depositFrame.setTitle("Deposit");

        depositFrame.pack();
        depositFrame.setVisible(true);
        depositFrame.setLocationRelativeTo(null);

        addGUI();
    }
    private void addGUI() {
        JLabel balance = new JLabel("Balance: ", SwingConstants.CENTER);
        Font balanceFont = new Font("Helvetica", Font.BOLD, 20);
        balance.setFont(balanceFont);
        balance.setText("Balance: ");
        balance.setLocation(0, 0);
        balance.setSize(460, 50);

        depositPanel.add(balance);
    }
}
