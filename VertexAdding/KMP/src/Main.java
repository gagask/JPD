import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow("Алгоритм Прима");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setSize(720,480);
        window.setResizable(false);
    }
}
