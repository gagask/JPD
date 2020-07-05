import javax.swing.*;
import java.awt.*;

public class WeightDialog extends JDialog {
    private final JTextField area;
    private final JLabel label;
    private int weight;
    private boolean ok = true;

    public WeightDialog(MainWindow window)
    {
        super(window, "Вес ребра", true);

        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(200, 80);
        addWindowListener(new WeightDialogWindowListener(this));

        label = new JLabel("Введите вес ребра: ");
        area = new JTextField(4);

        area.addActionListener(new TextAreaActionListener(window, this));

        add(label);
        add(area);
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

    public void notOk()
    {
        ok = false;
    }

    public boolean isOk()
    {
        return ok;
    }

}
