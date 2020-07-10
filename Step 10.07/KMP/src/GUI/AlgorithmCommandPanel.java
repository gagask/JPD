package GUI;

import GUI.MainWindow;
import algorithm.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmCommandPanel extends JPanel{

    private JButton NextStep;
    private JButton PrevStep;
    private JButton ForceAns;
    private JButton Restart;
    private JButton Exit;

    private Algorithm algorithm;

    public AlgorithmCommandPanel(Algorithm algorithm)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0,20,0,10));

        this.algorithm = algorithm;
        initButtons();
        addButtons();
    }

    private void addButtons()
    {
        add(Box.createRigidArea(new Dimension(0,5)));
        add(NextStep);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(PrevStep);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(Restart);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(ForceAns);
        add(Box.createRigidArea(new Dimension(0,200)));
    }

    private void initButtons()
    {
        NextStep = new JButton("                 Старт                  "); // "       Следующий шаг       "
        PrevStep = new JButton("     Предыдущий шаг      ");
        Restart = new JButton( "           Перезапуск            ");
        ForceAns = new JButton("         Быстрый ответ        ");
        buttonEnabler();

        NextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm.nextStep();
                buttonEnabler();
            }
        });

        PrevStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm.prevStep();
                buttonEnabler();
            }
        });

        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm.restart();
                buttonEnabler();
            }
        });

        ForceAns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm.forceAns();
                buttonEnabler();
            }
        });
    }

    public void buttonEnabler()
    {
        NextStep.setText( "       Следующий шаг       ");
        NextStep.setEnabled(true);
        PrevStep.setEnabled(true);
        ForceAns.setEnabled(true);
        Restart.setEnabled(true);

        if (algorithm.isStart()) {
            NextStep.setText("                 Старт                  ");
            PrevStep.setEnabled(false);
            Restart.setEnabled(false);
        }

        if (algorithm.isEnd())
        {
            NextStep.setEnabled(false);
            ForceAns.setEnabled(false);

        }


    }
}
