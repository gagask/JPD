import graph.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum ButtonState{
    addVertex,
    removeVertex,
    removeEdge,
    addEdge,
    noButton
}

public class MainWindow extends JFrame {

    private GraphPanel graph;
    private GraphCommandPanel gcp;
    private AlgorithmCommandPanel acp;

    private JPanel MainWindowPanel;


    public MainWindow(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720,480);
        setLocationRelativeTo(null);
        setResizable(false);

        gcp = new GraphCommandPanel();
        acp = new AlgorithmCommandPanel();

        initMainWindowPanel();
        initAlgorithm();

        setContentPane(MainWindowPanel);
    }

    private void initAlgorithm()
    {
        JButton Algorithm = new JButton("       Алгоритм Прима       ");
        Algorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.activateAlgorithm();
            }
        });

        gcp.add(Box.createRigidArea(new Dimension(0,20)));
        gcp.add(Algorithm);
    }

    private void initMainWindowPanel()
    {
        MainWindowPanel = new JPanel();
        graph = new GraphPanel(MainWindowPanel, this);
        graph.addMouseListener(new GraphPanelMouseListener(graph, gcp, this));

        MainWindowPanel.setLayout(new BoxLayout(MainWindowPanel, BoxLayout.X_AXIS));

        MainWindowPanel.add(gcp);
        MainWindowPanel.add(graph);
    }
}
