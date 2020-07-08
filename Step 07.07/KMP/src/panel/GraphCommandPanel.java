package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphCommandPanel extends JPanel {

    private ButtonState buttonState;

    private JButton addVertex;
    private JButton removeVertex;
    private JButton addEdge;
    private JButton removeEdge;

    public GraphCommandPanel()
    {
        setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initButtons();
        addButtons();
        addLabels();
    }

    private void addLabels()
    {
        String str = "Алгоритм Прима - алгоритмпостроения минимального  остовного дерева взвешен-ного связного неориентир-ованного графа. Алгоритм впервые был открыт в 1930году чешским математиком Войцехом Ярником, позже  открыт Робертом Примом в 1957 году, и, независимо от них, Дейкстрой в 1959 году.                             ";
        for (int i = 0 ; i < str.length() - 25; i += 25) add(new JLabel(str.substring(i, i + 25)));
    }

    private void addButtons()
    {
        add(addVertex);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(removeVertex);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(addEdge);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(removeEdge);
        add(Box.createRigidArea(new Dimension(0,20)));
    }

    private void initButtons()
    {
        buttonState = ButtonState.noButton;

        addVertex = new JButton("    Добавить вершину    ");
        removeVertex = new JButton("     Удалить вершину     ");
        addEdge = new JButton("       Добавить ребро       ");
        removeEdge = new JButton("        Удалить ребро        ");

        addVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonState = ButtonState.addVertex;
            }
        });
        removeVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonState = ButtonState.removeVertex;
            }
        });
        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonState = ButtonState.removeEdge;
            }
        });
        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonState = ButtonState.addEdge;
            }
        });
    }

    public ButtonState ButtonState()
    {
        return buttonState;
    }

    public void setButtonState(ButtonState buttonState)
    {
        this.buttonState = buttonState;
    }

}
