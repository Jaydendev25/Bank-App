import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class BankLogIn extends JPanel implements ActionListener {
    JButton login;
    JButton registerAccount;
    JTextField userNameField;
    JPasswordField passwordField;
    Server server;
    BankLogIn(Server server) {
        this.server = server;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(BankApp.PANEL_WIDTH, BankApp.PANEL_HEIGHT));
        this.setBackground(Color.WHITE);

        addGUI();
    }

    private void addGUI() {
        JLabel bankAppTitle = new JLabel();
        Font bankAppTitleFont = new Font("Helvetica", Font.BOLD, 32);
        bankAppTitle.setFont(bankAppTitleFont);
        bankAppTitle.setText("Banking Application");
        bankAppTitle.setLocation(100, -268);
        bankAppTitle.setSize(600, 600);

        JLabel userName = new JLabel();
        Font userNameFont = new Font("Helvetica", Font.BOLD, 16);
        userName.setFont(userNameFont);
        userName.setText("Username:");
        userName.setLocation(50, -150);
        userName.setSize(600, 600);

        userNameField = new JTextField();
        Font userNameFieldFont = new Font("TimesRoman", Font.PLAIN, 24);
        userNameField.setFont(userNameFieldFont);
        userNameField.setLocation(50, 160);
        userNameField.setSize(400, 40);

        JLabel password = new JLabel();
        Font passwordFont = new Font("Helvetica", Font.BOLD, 16);
        password.setFont(passwordFont);
        password.setText("Password:");
        password.setLocation(50, 0);
        password.setSize(600, 600);

        passwordField = new JPasswordField();
        Font passwordFieldFont = new Font("TimesRoman", Font.PLAIN, 24);
        passwordField.setFont(passwordFieldFont);
        passwordField.setLocation(50, 310);
        passwordField.setSize(400, 40);

        login = new JButton("Login");
        Font loginFont = new Font("Helvetica", Font.PLAIN, 32);
        login.setFont(loginFont);
        login.setLocation(50, 500);
        login.setSize(400, 40);
        login.addActionListener(this);

        registerAccount = new JButton("<HTML><U>Don't have an account? Register Here</U></HTML>");
        Font registerAccountFont = new Font("Helvetica", Font.PLAIN, 18);
        registerAccount.setForeground(Color.blue);
        registerAccount.setContentAreaFilled(false);
        registerAccount.setBorderPainted(false);
        registerAccount.setFont(registerAccountFont);
        registerAccount.setLocation(50, 550);
        registerAccount.setSize(400, 40);
        registerAccount.addActionListener(this);

        this.add(bankAppTitle);
        this.add(userName);
        this.add(userNameField);
        this.add(password);
        this.add(passwordField);
        this.add(login);
        this.add(registerAccount);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerAccount) {
            BankAppGUI.panel = "RegisterAccount";
            BankAppGUI.changePanel = true;
        } else if(userNameField.getText().length() > 0 && passwordField.getPassword().length > 0
            && (e.getSource() == login) && verifyAccountLogin()) {
                BankAppGUI.panel = "MainMenu";
                BankAppGUI.changePanel = true;
                JOptionPane.showMessageDialog(null, "Login successful!");
        } else{
            JOptionPane.showMessageDialog(null, "Login unsuccessful! Try again");
            userNameField.setText("");
            passwordField.setText("");
        }
    }
    
    private boolean verifyAccountLogin() {
        String query = "select username, user_password from users where username = ? and user_password = ? ;";
        PreparedStatement stmt;
        try {
            stmt = server.getConnection().prepareStatement(query);
            stmt.setString(1, userNameField.getText());
            stmt.setString(2, new String(passwordField.getPassword()));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
