package src;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransactionFrame {
    int historyDisplayLimit = 4;
    Server server;
    JPanel transferPanal;
    Date date[] = new Date[historyDisplayLimit];
    String type[] = new String[historyDisplayLimit];
    float amount[] = new float[historyDisplayLimit];
    
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

        fillDate();
        fillType();
        fillAmount();

        addGUI();
    }
    private void addGUI() {
        Font standardFont = new Font("Helvetica", Font.BOLD, 18);
        DateFormat dateFormatFrameOne = new SimpleDateFormat("YYYY-MM-dd");  

        //First Frame
        JLabel frameOne = new JLabel();
        frameOne.setLocation(0, 20);
        frameOne.setSize(450, 90);
        frameOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameOneType = new JLabel();
        frameOneType.setFont(standardFont);
        frameOneType.setText(type[0]);
        frameOneType.setLocation(0, 0);
        frameOneType.setSize(100, 100);

        String strDate = dateFormatFrameOne.format(date[0]);  
        JLabel frameOneDate = new JLabel();
        frameOneDate.setFont(standardFont);
        frameOneDate.setText(strDate);
        frameOneDate.setLocation(0, 50);
        frameOneDate.setSize(100, 100);

        JLabel frameOneAmount = new JLabel();
        frameOneAmount.setFont(standardFont);
        frameOneAmount.setText(String.valueOf(amount[0]));
        frameOneAmount.setLocation(390, 0);
        frameOneAmount.setSize(100, 100);

        //Second Frame
        JLabel frameTwo = new JLabel();
        frameTwo.setLocation(0, 110);
        frameTwo.setSize(450, 90);
        frameTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameTwoType = new JLabel();
        frameTwoType.setFont(standardFont);
        frameTwoType.setText(type[1]);
        frameTwoType.setLocation(0, 90);
        frameTwoType.setSize(100, 100);

        strDate = dateFormatFrameOne.format(date[1]);  
        JLabel frameTwoDate = new JLabel();
        frameTwoDate.setFont(standardFont);
        frameTwoDate.setText(strDate);
        frameTwoDate.setLocation(0, 140);
        frameTwoDate.setSize(100, 100);

        JLabel frameTwoAmount = new JLabel();
        frameTwoAmount.setFont(standardFont);
        frameTwoAmount.setText(String.valueOf(amount[1]));
        frameTwoAmount.setLocation(390, 90);
        frameTwoAmount.setSize(100, 100);

        //Third Frame
        JLabel frameThree = new JLabel();
        frameThree.setLocation(0, 200);
        frameThree.setSize(450, 90);
        frameThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameThreeType = new JLabel();
        frameThreeType.setFont(standardFont);
        frameThreeType.setText(type[2]);
        frameThreeType.setLocation(0, 180);
        frameThreeType.setSize(100, 100);

        strDate = dateFormatFrameOne.format(date[2]);  
        JLabel frameThreeDate = new JLabel();
        frameThreeDate.setFont(standardFont);
        frameThreeDate.setText(strDate);
        frameThreeDate.setLocation(0, 230);
        frameThreeDate.setSize(100, 100);

        JLabel frameThreeAmount = new JLabel();
        frameThreeAmount.setFont(standardFont);
        frameThreeAmount.setText(String.valueOf(amount[2]));
        frameThreeAmount.setLocation(390, 180);
        frameThreeAmount.setSize(100, 100);

        //Fourth Frame
        JLabel frameFour = new JLabel();
        frameFour.setLocation(0, 290);
        frameFour.setSize(450, 90);
        frameFour.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel frameFourType = new JLabel();
        frameFourType.setFont(standardFont);
        frameFourType.setText(type[3]);
        frameFourType.setLocation(0, 270);
        frameFourType.setSize(100, 100);

        strDate = dateFormatFrameOne.format(date[3]);  
        JLabel frameFourDate = new JLabel();
        frameFourDate.setFont(standardFont);
        frameFourDate.setText(strDate);
        frameFourDate.setLocation(0, 320);
        frameFourDate.setSize(100, 100);

        JLabel frameFourAmount = new JLabel();
        frameFourAmount.setFont(standardFont);
        frameFourAmount.setText(String.valueOf(amount[3]));
        frameFourAmount.setLocation(390, 270);
        frameFourAmount.setSize(100, 100);

        transferPanal.add(frameOne);
        transferPanal.add(frameOneType);
        transferPanal.add(frameOneDate);
        transferPanal.add(frameOneAmount);

        transferPanal.add(frameTwo);
        transferPanal.add(frameTwoType);
        transferPanal.add(frameTwoDate);
        transferPanal.add(frameTwoAmount);

        transferPanal.add(frameThree);
        transferPanal.add(frameThreeType);
        transferPanal.add(frameThreeDate);
        transferPanal.add(frameThreeAmount);

        transferPanal.add(frameFour);
        transferPanal.add(frameFourType);
        transferPanal.add(frameFourDate);
        transferPanal.add(frameFourAmount);
    }

    private void fillDate() {
        Connection conn = server.getConnection();
        String sqlQuery = "select transaction_date from user_transaction where userId = " + server.getUserID() 
                + " order by transaction_date DESC limit 4;";
        try {
            PreparedStatement queryStmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = queryStmt.executeQuery();
            rs.next();
            for(int i = 0; i < historyDisplayLimit; i++) {
                date[i] = rs.getDate(1);
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    private void fillType() {
        Connection conn = server.getConnection();
        String sqlQuery = "select transaction_type from user_transaction where userId = " + server.getUserID() 
                + " order by transaction_date DESC limit 4;";
        try {
            PreparedStatement queryStmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = queryStmt.executeQuery();
            rs.next();
            for(int i = 0; i < historyDisplayLimit; i++) {
                type[i] = rs.getString(1);
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    private void fillAmount() {
        Connection conn = server.getConnection();
        String sqlQuery = "select transaction_amount from user_transaction where userId = " + server.getUserID() 
                + " order by transaction_date DESC limit 4;";
        try {
            PreparedStatement queryStmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = queryStmt.executeQuery();
            rs.next();
            for(int i = 0; i < historyDisplayLimit; i++) {
                amount[i] = rs.getFloat(1);
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
