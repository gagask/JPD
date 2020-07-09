package GUI.dialog;

import GUI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WeightDialog extends DialogBase {
    private int weight;

    public WeightDialog(MainWindow window)
    {
        super(window, "Вес ребра");
    }

    void initTextArea() {
        area = new JTextField(4);
        area.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWeight();
                setVisible(false);
            }
        });
    }

    void initLabel() {
        label = new JLabel("Введите вес ребра: ");
    }

    public void loadWeight() {
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

}
