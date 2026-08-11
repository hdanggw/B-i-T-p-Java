import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Bai3 {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("Exit Application");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tạo JButton "Exit"
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setForeground(Color.BLUE);
        
        // Thêm sự kiện click cho nút
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thoát ứng dụng
                System.exit(0);
            }
        });
        
        // Căn giữa nút trong JFrame
        frame.setLayout(new GridBagLayout());
        frame.add(exitButton);
        
        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}