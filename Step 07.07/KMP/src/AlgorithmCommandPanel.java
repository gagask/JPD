import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmCommandPanel extends JPanel{

    private JButton Start;
    private JButton Pause;
    private JButton Restart;
    private JButton Exit;

    AlgorithmCommandPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0,20,0,10));

        initButtons();
        addButtons();
    }

    private void addButtons()
    {
        add(Start);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(Pause);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(Restart);
        add(Box.createRigidArea(new Dimension(0,280)));
        add(Exit);
        add(Box.createRigidArea(new Dimension(0,20)));

        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    private void initButtons()
    {
        Start = new JButton("      Старт     ");
        Pause = new JButton("      Пауза     ");
        Restart = new JButton("Перезапуск");
        Exit = new JButton("      Выход     ");
    }
}
