package GUI.dialog;

import GUI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class DialogBase extends JDialog{
    protected JTextField area;
    protected JLabel label;

    protected boolean ok = true;

    public DialogBase(MainWindow window, String title)
    {
        super(window, title, true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(200, 80);
        setLayout(new FlowLayout());

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                ok = false;
                super.windowClosing(e);
            }
        });

        initLabel();
        initTextArea();

        add(label);
        add(area);
    }

    abstract void initTextArea();
    abstract void initLabel();

    public boolean isOk()
    {
        return ok;
    }
}
