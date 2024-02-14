package src;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WithdrawFrame implements ActionListener{
    JPanel withdrawPanel;
    JButton withdrawButton;
    JTextField withdrawAmountField;
    Server server;
    float userBalance;
    JLabel balance;
    WithdrawFrame(Server server) {
        JFrame withdrawFrame = new JFrame();
        this.server = server;
        this.userBalance = server.updateBalance();
        withdrawPanel = new JPanel();
        withdrawPanel.setLayout(null);
        withdrawPanel.setPreferredSize(new Dimension(MainMenu.NEW_FRAME_WIDTH, MainMenu.NEW_FRAME_HEIGHT));
        withdrawPanel.setBackground(Color.WHITE);
        withdrawFrame.add(withdrawPanel);
        withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        withdrawFrame.setResizable(false);
        withdrawFrame.setTitle("Withdraw");

        withdrawFrame.pack();
        withdrawFrame.setVisible(true);
        withdrawFrame.setLocationRelativeTo(null);

        addGUI();
    }
    private void addGUI() {
        balance = new JLabel("Balance: ", SwingConstants.CENTER);
        Font balanceFont = new Font("Helvetica", Font.BOLD, 20);
        balance.setFont(balanceFont);
        balance.setText("Balance: $"+ userBalance);
        balance.setLocation(0, 0);
        balance.setSize(460, 50);

        JLabel amountText = new JLabel("Enter Amount:", SwingConstants.CENTER);
        amountText.setFont(balanceFont);
        amountText.setLocation(0, 50);
        amountText.setSize(460, 50);

        withdrawAmountField = new JTextField();
        withdrawAmountField.setFont(balanceFont);
        withdrawAmountField.setLocation(20, 100);
        withdrawAmountField.setSize(410, 50);
        withdrawAmountField.setForeground(Color.black);
        withdrawAmountField.setHorizontalAlignment(JTextField.CENTER);

        withdrawButton = new JButton();
        withdrawButton.setText("Withdraw");
        withdrawButton.setFont(balanceFont);
        withdrawButton.setLocation(20, 340);
        withdrawButton.setSize(410, 50);
        withdrawButton.addActionListener(this);

        withdrawPanel.add(balance);
        withdrawPanel.add(amountText);
        withdrawPanel.add(withdrawAmountField);
        withdrawPanel.add(withdrawButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        float amountEntered = Float.parseFloat(withdrawAmountField.getText());
        if(e.getSource() == withdrawButton && amountEntered > userBalance) {
            withdrawAmountField.setText("");
            JOptionPane.showMessageDialog(null, "You do not have the required funds");
        }
        if(!withdrawAmountField.getText().isEmpty() && e.getSource() == withdrawButton &&  amountEntered != 0) {
            withdrawAmount();
            withdrawAmountField.setText("");
            userBalance = server.updateBalance();
            server.addTransaction("Withdraw", amountEntered);
            balance.setText("Balance $"+ userBalance);
            JOptionPane.showMessageDialog(null, "Withdraw Successfully!");
        }
    }
    private void withdrawAmount() {
        Connection conn = server.getConnection();
        String updateBalance = "update wallet inner join users on walletID = userid set balance = balance - ? where users.username = ? ;";
        try {
           PreparedStatement updateBalanceStmt = conn.prepareStatement(updateBalance);
           updateBalanceStmt.setFloat(1, Float.parseFloat(withdrawAmountField.getText()));
           updateBalanceStmt.setString(2, BankAppGUI.username);
           updateBalanceStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
