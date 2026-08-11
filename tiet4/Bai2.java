import javax.swing.*;

public class Bai2 {
    public static void main(String[] args) {
        // Tạo JFrame với tiêu đề
        JFrame frame = new JFrame("Welcome");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hiển thị JOptionPane khi mở ứng dụng
        JOptionPane.showMessageDialog(frame, "Welcome to Java Swing", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        
        // Đóng ứng dụng sau khi nhấn OK
        System.exit(0);
    }
}