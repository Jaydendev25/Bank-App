import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MainMenu extends JPanel {
    MainMenu() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(BankApp.PANEL_WIDTH, BankApp.PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
        addGUI();
    }

    private void addGUI() {
        JLabel helloUser = new JLabel("Hello ", SwingConstants.CENTER);
        Font helloUserFont = new Font("Helvetica", Font.BOLD, 20);
        helloUser.setFont(helloUserFont);
        helloUser.setText("Hello " + BankLogIn.accountName);
        helloUser.setLocation(0, 0);
        helloUser.setSize(460, 50);

        JLabel entryText = new JLabel();
        Font entryTextFont = new Font("Helvetica", Font.PLAIN, 16);
        entryText.setFont(entryTextFont);
        entryText.setText("What would you like to do today?");
        entryText.setLocation(130, -200);
        entryText.setSize(500, 550);

        JLabel balanceText = new JLabel();
        Font balanceTextFont = new Font("Helvetica", Font.BOLD, 28);
        balanceText.setFont(balanceTextFont);
        balanceText.setText("Current Balance");
        balanceText.setLocation(140, -150);
        balanceText.setSize(500, 550);

        JLabel balance = new JLabel("$0.00",SwingConstants.RIGHT);
        balance.setFont(balanceTextFont);
       // balance.setText("$0.00");
        balance.setLocation(20, 150);
        balance.setSize(460, 50);
        Color borderColor = new Color(173, 216, 230);
        balance.setBorder(BorderFactory.createLineBorder(borderColor));
        
        JButton deposit = new JButton("Deposit");
        Font buttonFont = new Font("Helvetica", Font.BOLD, 18);
        deposit.setFont(buttonFont);
        deposit.setLocation(50, 210);
        deposit.setSize(400, 50);
        deposit.setFocusable(false);

        JButton withdraw = new JButton("Withdraw");
        withdraw.setFont(buttonFont);
        withdraw.setLocation(50, 280);
        withdraw.setSize(400, 50);
        withdraw.setFocusable(false);
    
        JButton pastTransaction = new JButton("Past Transaction");
        pastTransaction.setFont(buttonFont);
        pastTransaction.setLocation(50, 350);
        pastTransaction.setSize(400, 50);
        pastTransaction.setFocusable(false);

        JButton transfer = new JButton("Transfer");
        transfer.setFont(buttonFont);
        transfer.setLocation(50, 420);
        transfer.setSize(400, 50);
        transfer.setFocusable(false);

        JButton logout = new JButton("Logout");
        logout.setFont(buttonFont);
        logout.setLocation(50, 540);
        logout.setSize(400, 50);
        logout.setFocusable(false);

        add(helloUser);
        add(entryText);
        add(balanceText);
        add(balance);
        add(deposit);
        add(withdraw);
        add(pastTransaction);
        add(transfer);
        add(logout);
    }
}
