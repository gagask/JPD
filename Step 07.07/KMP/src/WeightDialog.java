import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WeightDialog extends JDialog {
    private JTextField area;
    private JLabel label;
    private int weight;
    private boolean ok = true;

    public WeightDialog(MainWindow window)
    {
        super(window, "Вес ребра", true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(200, 80);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                ok = false;
                super.windowClosing(e);
            }
        });
        setLayout(new FlowLayout());

        initLabel();
        initTextArea();

        add(label);
        add(area);
    }

    private void initTextArea()
    {
        area = new JTextField(4);
        area.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWeight();
                setVisible(false);
            }
        });
    }

    private void initLabel()
    {
        label = new JLabel("Введите вес ребра: ");
    }

    public void loadWeight()
    {
        String result = area.getText();

        try
        {
            weight = Integer.parseInt(result);
            if (weight <= 0)
                throw new NumberFormatException();
        }
        catch (NumberFormatException e)
        {
            ok = false;
        }
    }

    public int getWeight()
    {
        return weight;
    }

    public boolean isOk()
    {
        return ok;
    }

}
