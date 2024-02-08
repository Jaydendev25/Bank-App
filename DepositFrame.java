
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DepositFrame {
    private final int DEPOSIT_PANEL_WIDTH = 450;
    private final int DEPOSIT_PANEL_HEIGHT = 400;
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

        JLabel amountText = new JLabel("Enter Amount:", SwingConstants.CENTER);
        amountText.setFont(balanceFont);
        amountText.setLocation(0, 50);
        amountText.setSize(460, 50);

        JTextField depositAmount = new JTextField();
        depositAmount.setFont(balanceFont);
        depositAmount.setLocation(20, 100);
        depositAmount.setSize(410, 50);
        depositAmount.setForeground(Color.black);
        depositAmount.setHorizontalAlignment(JTextField.CENTER);

        JButton depositButton = new JButton();
        depositButton.setText("Deposit");
        depositButton.setFont(balanceFont);
        depositButton.setLocation(20, 340);
        depositButton.setSize(410, 50);

        depositPanel.add(balance);
        depositPanel.add(amountText);
        depositPanel.add(depositAmount);
        depositPanel.add(depositButton);
    }
}
