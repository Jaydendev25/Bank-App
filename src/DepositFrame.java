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

public class DepositFrame implements ActionListener{
    JPanel depositPanel;
    JButton depositButton;
    JTextField depositAmountField;
    Server server;
    float userBalance;
    JLabel balance;
    DepositFrame(Server server) {
        this.server = server;
        this.userBalance = server.updateBalance();
        JFrame depositFrame = new JFrame();
        depositPanel = new JPanel();
        depositPanel.setLayout(null);
        depositPanel.setPreferredSize(new Dimension(MainMenu.NEW_FRAME_WIDTH, MainMenu.NEW_FRAME_HEIGHT));
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

        depositAmountField = new JTextField();
        depositAmountField.setFont(balanceFont);
        depositAmountField.setLocation(20, 100);
        depositAmountField.setSize(410, 50);
        depositAmountField.setForeground(Color.black);
        depositAmountField.setHorizontalAlignment(JTextField.CENTER);

        depositButton = new JButton();
        depositButton.setText("Deposit");
        depositButton.setFont(balanceFont);
        depositButton.setLocation(20, 340);
        depositButton.setSize(410, 50);
        depositButton.addActionListener(this);

        depositPanel.add(balance);
        depositPanel.add(amountText);
        depositPanel.add(depositAmountField);
        depositPanel.add(depositButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        float amountEntered = Float.parseFloat(depositAmountField.getText());
        if(!depositAmountField.getText().isEmpty() && e.getSource() == depositButton &&  amountEntered != 0) {
            depositAmount();
            depositAmountField.setText("");
            userBalance = server.updateBalance();
            server.addTransaction("Deposit", amountEntered);
            balance.setText("Balance $"+ userBalance);
            JOptionPane.showMessageDialog(null, "Deposit Successfully!");
        }
    }
    private void depositAmount() {
        Connection conn = server.getConnection();
        String updateBalance = "update wallet inner join users on walletID = userid set balance = balance + ? where users.username = ? ;";
        try {
           PreparedStatement updateBalanceStmt = conn.prepareStatement(updateBalance);
           updateBalanceStmt.setFloat(1, Float.parseFloat(depositAmountField.getText()));
           updateBalanceStmt.setString(2, BankAppGUI.username);
           updateBalanceStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
