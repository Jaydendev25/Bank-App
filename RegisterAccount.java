import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class RegisterAccount extends JPanel implements ActionListener{
    JPasswordField passwordField;
    JPasswordField retypePasswordField;
    JTextField userNameField;
    JButton register;
    JButton signIn;
    Server server;
    RegisterAccount(Server server) {
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
        password.setLocation(50, -50);
        password.setSize(600, 600);

        passwordField = new JPasswordField();
        Font passwordFieldFont = new Font("TimesRoman", Font.PLAIN, 24);
        passwordField.setFont(passwordFieldFont);
        passwordField.setLocation(50, 260);
        passwordField.setSize(400, 40);

        JLabel retypePassword = new JLabel();
        Font retypePasswordFont = new Font("Helvetica", Font.BOLD, 16);
        retypePassword.setFont(retypePasswordFont);
        retypePassword.setText("Re-Type Password:");
        retypePassword.setLocation(50, 50);
        retypePassword.setSize(600, 600);

        retypePasswordField = new JPasswordField();
        Font retypePasswordFieldFont = new Font("TimesRoman", Font.PLAIN, 24);
        retypePasswordField.setFont(retypePasswordFieldFont);
        retypePasswordField.setLocation(50, 360);
        retypePasswordField.setSize(400, 40);

        register = new JButton("Register");
        Font registerFont = new Font("Helvetica", Font.PLAIN, 32);
        register.setFont(registerFont);
        register.setLocation(50, 500);
        register.setSize(400, 40);
        register.addActionListener(this);

        signIn = new JButton("<HTML><U>Have an account? Sign-in here</U></HTML>");
        Font signInFont = new Font("Helvetica", Font.PLAIN, 18);
        signIn.setForeground(Color.blue);
        signIn.setContentAreaFilled(false);
        signIn.setBorderPainted(false);
        signIn.setFont(signInFont);
        signIn.setLocation(50, 550);
        signIn.setSize(400, 40);
        signIn.addActionListener(this);
       

        add(bankAppTitle);
        add(userName);
        add(userNameField);
        add(password);
        add(passwordField);
        add(retypePassword);
        add(retypePasswordField);
        add(register);
        add(signIn);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signIn) {
            BankAppGUI.panel = "Login";
            BankAppGUI.changePanel = true;
        } else if(e.getSource() == register) {
            if((Arrays.equals(passwordField.getPassword(), retypePasswordField.getPassword())) && 
                (passwordField.getPassword().length > 0 || retypePasswordField.getPassword().length > 0)) {
                    if(userNameField.getText().length() > 0 ) {
                        createAccount();
                        
                        BankLogIn.accountName = userNameField.getText();
                        BankLogIn.accountPassword = passwordField;
                        BankAppGUI.panel = "Login";
                        BankAppGUI.changePanel = true;
                        JOptionPane.showMessageDialog(null, "Account Created!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a username!");
                        userNameField.setText("");
                        passwordField.setText("");
                        retypePasswordField.setText("");
                    }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Account creation. Please try again");
                userNameField.setText("");
                passwordField.setText("");
                retypePasswordField.setText("");
            }
           

        }
    }

    private void createAccount() {
        Connection connection = server.getConnection();
        String sql = " insert into users (username, user_password)" + " values (?, ?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, userNameField.getText());
            preparedStmt.setString(2, new String(passwordField.getPassword()));
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
