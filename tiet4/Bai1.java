import java.awt.*;
import javax.swing.*;

public class Bai1 {
    public static void main(String[] args) {
        // Tạo JFrame với tiêu đề
        JFrame frame = new JFrame("My First Swing App");
        
        // Đặt kích thước
        frame.setSize(400, 300);
        
        // Căn giữa màn hình
        frame.setLocationRelativeTo(null);
        
        // Tạo Label và căn giữa
        JLabel label = new JLabel("Hello World", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Thêm Label vào JFrame
        frame.add(label);
        
        // Thoát ứng dụng khi đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}