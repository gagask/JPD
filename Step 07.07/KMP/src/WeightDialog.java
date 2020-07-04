import javax.swing.*;
import java.awt.*;

public class WeightDialog extends JDialog {
    JButton ok;
    JTextField area;
    JLabel label;
    JLabel label2;
    MainWindow window;

    public WeightDialog(MainWindow window)
    {
        super();
        this.window = window;
        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(250, 100);
        setTitle("Вес ребра");

        label = new JLabel("       Введите вес ребра: ");
        label2 = new JLabel( "         ");
        ok = new JButton("  OK  ");
        ok.add
        area = new JTextField(4);

        add(label);
        add(area);
        add(label2);
        add(ok);
    }
}
