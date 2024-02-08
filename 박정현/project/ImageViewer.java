package project;
import javax.swing.*;
import java.awt.*;

public class ImageViewer extends JFrame {

    private JPanel currentPanel; // 현재 보여지고 있는 패널을 추적하기 위한 변수

    public ImageViewer() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Attendance_System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 1024);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(133, 91, 221));
        topPanel.setPreferredSize(new Dimension(770, 200));
        topPanel.setLayout(new BorderLayout());
        layeredPane.add(topPanel, JLayeredPane.DEFAULT_LAYER);
        topPanel.setBounds(300, 0, 1100, 130);

        JLabel toplabel = new JLabel("     동의대 학생 교육원 프로그램");
        toplabel.setForeground(Color.white);
        toplabel.setFont(new Font("Inter", Font.BOLD, 30));
        topPanel.add(toplabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 50));
        buttonPanel.setOpaque(false);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        JButton button1 = new JButton("로그아웃");
        JButton button2 = new JButton("동기화");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        button1.setBackground(Color.WHITE);
        button1.setFont(new Font("Inter", Font.PLAIN, 18));
        button1.setBorderPainted(false);
        button2.setBackground(Color.WHITE);
        button2.setFont(new Font("Inter", Font.PLAIN, 18));
        button2.setBorderPainted(false);

        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(ImageViewer.class.getResource("due.PNG"));
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(img);
        imgLabel.setIcon(updateIcon);
        imgLabel.setBounds(85, 10, 150, 150);
        layeredPane.add(imgLabel, JLayeredPane.PALETTE_LAYER);

        addButtons(layeredPane, imgLabel.getY() + imgLabel.getHeight() + 20);

        setVisible(true);
    }

    private void addButtons(JLayeredPane layeredPane, int initialY) {
        int width = 280;
        int height = 80;

        String[] buttonNames = {"홈", "강의 정보", "출결 관리", "상담 신청", "채팅창", "프로필 수정", "청소 당번", "공지 사항", "퇴실"};

        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = new JButton(buttonNames[i]);
            int xCoordinate = 30;
            int yCoordinate = initialY + (height + 0) * i;
            button.setBounds(xCoordinate, yCoordinate, width, height);
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

            if (i == buttonNames.length - 1) {
                button.setBackground(new Color(133, 91, 221));
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setForeground(Color.WHITE);
                button.setBorderPainted(false);
                
            }else if (i == buttonNames.length - 9) {
                  button.setBackground(new Color(255, 255, 255));
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setForeground(Color.black);
                button.setBorderPainted(false);
                
                button.addActionListener(e ->  showPanel(new StaticsPanel(), layeredPane)); // StaticsPanel 보여주는 동작
                
            }else if (i == buttonNames.length - 6) {
                button.setBackground(new Color(255, 255, 255));
              button.setFont(new Font("Inter", Font.PLAIN, 18));
              button.setForeground(Color.black);
              button.setBorderPainted(false);
              
              button.addActionListener(e ->  showPanel(new CounselingPanel(), layeredPane)); // CounselingPanel 보여주는 동작
              
          }
            else if (i == buttonNames.length - 3) {
                button.setBackground(new Color(255, 255, 255));
                 button.setFont(new Font("Inter", Font.PLAIN, 18));
                 button.setForeground(Color.black);
                 button.setBorderPainted(false);
                 
                 button.addActionListener(e -> showPanel(new CleanPanel(), layeredPane)); // CleanPanel 보여주는 동작
      }else {
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setBorderPainted(false);
            }
        }
    }

    	

	// 새로운 패널을 보여주는 메서드
    private void showPanel(JPanel panel, JLayeredPane layeredPane) {
        // 현재 보여지고 있는 패널을 숨김
        if (currentPanel != null) {
            currentPanel.setVisible(false);
        }
        // 새로운 패널을 추가하고 보여줌
        currentPanel = panel;
        panel.setBounds(300, 130, 1100, 770); // 패널의 위치와 크기 지정
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageViewer();
        });
    }
}