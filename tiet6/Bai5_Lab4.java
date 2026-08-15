package BaiTap_Lab4;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Bai5_Lab4 extends JFrame {
    private File selectedFile;
    private JLabel lblFile;
    private JLabel lblResult;
    private JProgressBar progressBar;
    private JButton btnChoose;
    private JButton btnCount;

    public Bai5_Lab4() {
        setTitle("Đếm dòng file lớn");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // Panel trên cùng: chọn file
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        btnChoose = new JButton("Chọn file");
        lblFile = new JLabel("Chưa chọn file");
        topPanel.add(btnChoose, BorderLayout.WEST);
        topPanel.add(lblFile, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel giữa: progress bar
        JPanel centerPanel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        centerPanel.add(progressBar, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Panel dưới: kết quả và nút đếm
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        lblResult = new JLabel("Số dòng: ");
        btnCount = new JButton("Đếm dòng");
        bottomPanel.add(lblResult, BorderLayout.CENTER);
        bottomPanel.add(btnCount, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện
        btnChoose.addActionListener(e -> chooseFile());
        btnCount.addActionListener(e -> countLines());
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "java", "csv", "log"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblFile.setText("File: " + selectedFile.getAbsolutePath());
            lblResult.setText("Số dòng: ");
            progressBar.setValue(0);
            btnCount.setEnabled(true);
        }
    }

    private void countLines() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn file trước");
            return;
        }

        btnCount.setEnabled(false);
        btnChoose.setEnabled(false);
        progressBar.setValue(0);
        lblResult.setText("Đang đọc file...");

        SwingWorker<Long, Void> worker = new SwingWorker<>() {
            @Override
            protected Long doInBackground() throws Exception {
                long totalBytes = Files.size(selectedFile.toPath());
                long readBytes = 0;
                long lines = 0;

                try (BufferedReader reader = Files.newBufferedReader(
                        selectedFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines++;
                        readBytes += line.getBytes(StandardCharsets.UTF_8).length + 1;

                        int progress = totalBytes == 0 ? 100 :
                                (int) Math.min(100, (readBytes * 100 / totalBytes));
                        setProgress(progress);
                    }
                }
                return lines;
            }

            @Override
            protected void done() {
                try {
                    long lineCount = get();
                    lblResult.setText("Số dòng: " + lineCount);
                } catch (Exception ex) {
                    lblResult.setText("Lỗi khi đọc file: " + ex.getMessage());
                }
                progressBar.setValue(100);
                btnCount.setEnabled(true);
                btnChoose.setEnabled(true);
            }
        };

        // Cập nhật progress bar từ worker
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai5_Lab4().setVisible(true));
    }
}