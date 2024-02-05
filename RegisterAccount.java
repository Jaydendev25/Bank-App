import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterAccount extends JPanel implements ActionListener{
    JButton login;
    JButton signIn;
    RegisterAccount() {
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

        JTextField userNameField = new JTextField();
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

        JPasswordField passwordField = new JPasswordField();
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

        JPasswordField retypePasswordField = new JPasswordField();
        Font retypePasswordFieldFont = new Font("TimesRoman", Font.PLAIN, 24);
        retypePasswordField.setFont(retypePasswordFieldFont);
        retypePasswordField.setLocation(50, 360);
        retypePasswordField.setSize(400, 40);

        login = new JButton("Login");
        Font loginFont = new Font("Helvetica", Font.PLAIN, 32);
        login.setFont(loginFont);
        login.setLocation(50, 500);
        login.setSize(400, 40);

        signIn = new JButton("<HTML><U>Don't have an account? Register Here</U></HTML>");
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
        add(login);
        add(signIn);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signIn) {
            BankAppGUI.panel = "Login";
            BankAppGUI.changePanel = true;
        }
    }

   
}
