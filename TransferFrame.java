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

public class TransferFrame implements ActionListener{
    JPanel transferPanel;
    Server server;
    float userBalance;
    JLabel balance;
    JTextField transferAmountField;
    JButton transferButton;
    JTextField transferUserField;
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

        transferAmountField = new JTextField();
        transferAmountField.setFont(balanceFont);
        transferAmountField.setLocation(20, 100);
        transferAmountField.setSize(410, 50);
        transferAmountField.setForeground(Color.black);
        transferAmountField.setHorizontalAlignment(JTextField.CENTER);

        transferButton = new JButton();
        transferButton.setText("Transfer");
        transferButton.setFont(balanceFont);
        transferButton.setLocation(20, 340);
        transferButton.setSize(410, 50);
        transferButton.addActionListener(this);

        JLabel enterUser = new JLabel("Enter User:", SwingConstants.CENTER);
        enterUser.setFont(balanceFont);
        enterUser.setLocation(0, 175);
        enterUser.setSize(460, 50);

        transferUserField = new JTextField();
        transferUserField.setFont(balanceFont);
        transferUserField.setLocation(20, 225);
        transferUserField.setSize(410, 50);
        transferUserField.setForeground(Color.black);
        transferUserField.setHorizontalAlignment(JTextField.CENTER);

        transferPanel.add(balance);
        transferPanel.add(amountText);
        transferPanel.add(transferAmountField);
        transferPanel.add(transferButton);
        transferPanel.add(enterUser);
        transferPanel.add(transferUserField);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        float amountEntered = Float.parseFloat(transferAmountField.getText());
        if(e.getSource() == transferButton && amountEntered > userBalance) {
            JOptionPane.showMessageDialog(null, "You do not have the required funds");
            transferAmountField.setText("");
            transferUserField.setText("");
        } else if(e.getSource() == transferButton && transferUserField.getText().equals(BankAppGUI.username)) {
            JOptionPane.showMessageDialog(null, "You can not transfer to yourself");
            transferAmountField.setText("");
            transferUserField.setText("");
        } else if(e.getSource() == transferButton && server.validUser(transferUserField.getText()) && amountEntered != 0) {
            transferFunds();
            userBalance = server.updateBalance();
            server.addTransaction("Transfer", amountEntered);
            balance.setText("Balance $"+ userBalance);
            transferAmountField.setText("");
            transferUserField.setText("");
            JOptionPane.showMessageDialog(null, "Transfer successful");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid user. Try again");
            transferAmountField.setText("");
            transferUserField.setText("");
        }
    }
    private void transferFunds() {
        Connection conn = server.getConnection();
        String removeFunds = "update wallet inner join users on walletID = userid set balance = balance - ? where users.username = ? ;";
        String addFunds = "update wallet inner join users on walletID = userid set balance = balance + ? where users.username = ? ;";
         try {
           PreparedStatement removeFundsStmt = conn.prepareStatement(removeFunds);
           removeFundsStmt.setFloat(1, Float.parseFloat(transferAmountField.getText()));
           removeFundsStmt.setString(2, BankAppGUI.username);
           removeFundsStmt.execute();
           PreparedStatement addFundsStmt = conn.prepareStatement(addFunds);
           addFundsStmt.setFloat(1, Float.parseFloat(transferAmountField.getText()));
           addFundsStmt.setString(2, transferUserField.getText());
           addFundsStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
