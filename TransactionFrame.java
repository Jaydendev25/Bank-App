import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransactionFrame {
    int historyDisplay = 4;
    Server server;
    JPanel transferPanal;
    Date date[] = new Date[historyDisplay];
    String type[] = new String[historyDisplay];
    float amount[] = new float[historyDisplay];
    TransactionFrame(Server server) {
        this.server = server;

        JFrame transactionFrame = new JFrame();
        transferPanal = new JPanel();
        transferPanal.setLayout(null);
        transferPanal.setPreferredSize(new Dimension(MainMenu.NEW_FRAME_WIDTH, MainMenu.NEW_FRAME_HEIGHT));
        transferPanal.setBackground(Color.WHITE);
        transactionFrame.add(transferPanal);
        transactionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionFrame.setResizable(false);
        transactionFrame.setTitle("Past Transaction");

        transactionFrame.pack();
        transactionFrame.setVisible(true);
        transactionFrame.setLocationRelativeTo(null);
        fillAmount();

        addGUI();
    }
    private void addGUI() {
        JLabel frameOne = new JLabel();
        frameOne.setLocation(0, 20);
        frameOne.setSize(450, 90);
        frameOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameTwo = new JLabel();
        frameTwo.setLocation(0, 110);
        frameTwo.setSize(450, 90);
        frameTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameThree = new JLabel();
        frameThree.setLocation(0, 200);
        frameThree.setSize(450, 90);
        frameThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameFour = new JLabel();
        frameFour.setLocation(0, 290);
        frameFour.setSize(450, 90);
        frameFour.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        transferPanal.add(frameOne);
        transferPanal.add(frameTwo);
        transferPanal.add(frameThree);
        transferPanal.add(frameFour);
    }
    private void fillDate() {

    }
    private void fillType() {
        
    }
    private void fillAmount() {
        Connection conn = server.getConnection();
        String sqlQuery = "select transaction_amount from user_transaction order by transaction_date DESC limit 4;";
        try {
            PreparedStatement queryStmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = queryStmt.executeQuery();
            rs.next();
            for(int i = 0; i < historyDisplay; i++) {
                amount[i] = rs.getFloat(1);
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
}
