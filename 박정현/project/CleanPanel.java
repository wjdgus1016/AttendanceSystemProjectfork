package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CleanPanel extends JPanel implements ActionListener {

    private JButton btn1, btn2;
    private JTextField tf1, tf2, tf3;
    private JTextArea tArea;
    private Random random;
    private int red = 133;
    private int green = 91;
    private int blue = 221;
    private int MIN_VALUE = 0;
    private int MAX_VALUE = 25;
    private String previousData = "";
    private Font interFont;
    private Set<String> selectedNames;
    private ArrayList<String> allNames;
    private List<List<String>> weekNamesList;
    private int currentWeek;

    public CleanPanel() {
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        allNames = new ArrayList<>();
        selectedNames = new HashSet<>();
        random = new Random();

        JPanel p1 = new JPanel();
        p1.add(tf1 = new JTextField("0",2));
        p1.add(tf2 = new JTextField("주차"));
        p1.add(tf3 = new JTextField("이번주 청소 당번은?", 30));
        p1.add(btn1 = new JButton("<"));
        p1.add(btn2 = new JButton(">"));
        p1.setFont(new Font("Inter", Font.PLAIN, 18));
        Color getBackgroundColor = new Color(red, green, blue);
        p1.setBackground(getBackgroundColor);
        tArea = new JTextArea();

        add(p1, BorderLayout.NORTH);
        add(new JScrollPane(tArea), BorderLayout.CENTER);
        tf1.setEditable(false);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        validate();
        initializeDatabaseData();
    }

    private void initializeDatabaseData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("SELECT user_name FROM user");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("user_name");
                allNames.add(userName);
            }

            int chunkSize = allNames.size() / 5;
            weekNamesList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                List<String> chunk = new ArrayList<>();
                for (int j = 0; j < chunkSize; j++) {
                    chunk.add(allNames.get(i * chunkSize + j));
                }
                weekNamesList.add(chunk);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception ex) {
            System.out.println("SQLException" + ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btn1 && Integer.parseInt(tf1.getText()) > MIN_VALUE) {
            int value = Integer.parseInt(tf1.getText());
            value--;
            tf1.setText(Integer.toString(value));
            currentWeek = (currentWeek - 1 + weekNamesList.size()) % weekNamesList.size();
            displayWeekNames();
        } else if (obj == btn2 && Integer.parseInt(tf1.getText()) < MAX_VALUE) {
            int value = Integer.parseInt(tf1.getText());
            value++;
            tf1.setText(Integer.toString(value));
            currentWeek = (currentWeek + 1) % weekNamesList.size();
            displayWeekNames();
        }
    }

    private void displayWeekNames() {
        List<String> currentWeekNames = weekNamesList.get(currentWeek);
        StringBuilder sb = new StringBuilder();
        sb.append(" \n\n\n\n\n\n");
        sb.append("                                                            " + currentWeek + " 주차\n");
        sb.append("                                         ");
        sb.append("-----------------------------------------\n");

        for (String name : currentWeekNames) {
            sb.append(name).append("                     ");
        }
        tArea.setText(sb.toString());
    }
}