package project;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaticsPanel extends JPanel {

    public StaticsPanel() {
        setLayout(new BorderLayout()); // Set BorderLayout for the main panel

        JPanel chartPanel = new JPanel(new BorderLayout()); // Create a panel for the chart
        add(chartPanel, BorderLayout.CENTER); // Add the chart panel to the center

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem?serverTimezone=UTC",
                    "root", "1234");
            System.out.println("success");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM attendance";
            ResultSet rs = stmt.executeQuery(query);
            

            DefaultPieDataset dataset = new DefaultPieDataset();

            int presentCount = 0;
            int totalCount = 0;

            while (rs.next()) {
                String column4Value = rs.getString("attendance_status");
               
                System.out.println(column4Value);
                

                // "attendance_status"가 "present"이면 presentCount 증가
                if (column4Value.equalsIgnoreCase("present")) {
                    presentCount++;
                }
                totalCount++; // 총 출석 횟수 증가
            }

            // 파이 차트에 present인 경우와 아닌 경우의 비율 표시
            dataset.setValue("Present (" + presentCount + " / " + totalCount + ")", presentCount);
            dataset.setValue("Absent or Tardy (" + (totalCount - presentCount) + " / " + totalCount + ")", totalCount - presentCount);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Attendance Statistics",
                    dataset,
                    true,
                    true,
                    false
            );
            rs.close();

            ChartPanel chartPanelChart = new ChartPanel(chart);
            chartPanelChart.setPreferredSize(new Dimension(800, 600));
            chartPanel.add(chartPanelChart, BorderLayout.CENTER); 
            

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 
            add(buttonPanel, BorderLayout.NORTH);
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem?serverTimezone=UTC",
                    "root", "1234");
            System.out.println("success");
            Statement stmt1 = conn1.createStatement();
            String query1 = "SELECT * FROM class";
            ResultSet rs1 = stmt1.executeQuery(query1);
            
            

            while (rs1.next()) {
                String className = rs1.getString("class_name");
                JButton button = new JButton(className);
                button.setPreferredSize(new Dimension(275, 82));
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setBorderPainted(false);
                buttonPanel.add(button);
            }

           
            rs1.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Statistics Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new StaticsPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}