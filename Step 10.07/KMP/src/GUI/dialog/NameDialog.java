package GUI.dialog;

import GUI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NameDialog extends DialogBase
{
    private char name;

    public NameDialog(MainWindow window)
    {
        super(window, "Имя вершины");
    }

    void initTextArea() {
        area = new JTextField(1);
        area.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadName();
                setVisible(false);
            }
        });
    }

    void initLabel() {
        label = new JLabel("Введите имя вершины: ");
    }

    private void loadName() {
        name = area.getText().toCharArray()[0];
    }

    public char getVertexName() {
        return name;
    }
}
