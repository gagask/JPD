package GUI;

import GUI.listeners.GraphPanelMouseListener;
import GUI.listeners.LoadItemListener;
import GUI.listeners.OpenItemListener;
import algorithm.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private GraphPanel graphPanel;
    private final GraphCommandPanel gcp;
    private final AlgorithmCommandPanel acp;
    private final JFrame window;

    private JPanel MainWindowPanel;
    private JMenuBar menuBar;
    private Algorithm algorithm;
    private JMenuItem openItem;


    public MainWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720,480);
        setLocationRelativeTo(null);
        setResizable(false);

        window = this;

        gcp = new GraphCommandPanel();

        initMainWindowPanel();
        initAlgorithm();
        acp = new AlgorithmCommandPanel(algorithm);
        initExitAlgorithmButton();


        initMenuBar();
        setJMenuBar(menuBar);

        setContentPane(MainWindowPanel);
    }

    private void initExitAlgorithmButton()
    {
        JButton exit = new JButton("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.setGraph(algorithm.getDefaultGraph());
                MainWindowPanel.remove(acp);
                MainWindowPanel.add(gcp, 0);
                openItem.setEnabled(true);
            }
        });

        acp.add(exit);
        acp.add(Box.createRigidArea(new Dimension(0,20)));
    }

    private void initMenuBar()
    {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Файл");
        openItem = new JMenuItem("Загрузить граф");
        JMenuItem loadItem = new JMenuItem("Сохранить граф");

        openItem.addActionListener(new OpenItemListener(graphPanel, this));
        loadItem.addActionListener(new LoadItemListener(graphPanel, this));

        menu.add(openItem);
        menu.add(loadItem);

        menuBar.add(menu);
    }

    private void initAlgorithm()
    {
        algorithm = new Algorithm(graphPanel);

        JButton Algorithm = new JButton("       Алгоритм Прима       ");
        Algorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (graphPanel.getGraph().isEmpty())
                {
                    JOptionPane.showMessageDialog(window, "Граф не введен!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!graphPanel.getGraph().isConnected())
                {
                    JOptionPane.showMessageDialog(window, "Граф должен быть связным!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MainWindowPanel.remove(gcp);
                MainWindowPanel.add(acp, 0);
                acp.buttonEnabler();
                algorithm.updateGraph(graphPanel.getGraph());
                openItem.setEnabled(false);
            }
        });

        gcp.add(Box.createRigidArea(new Dimension(0,5)));
        gcp.add(Algorithm);
        gcp.add(Box.createRigidArea(new Dimension(0,10)));


    }

    private void initMainWindowPanel()
    {
        MainWindowPanel = new JPanel();
        graphPanel = new GraphPanel(MainWindowPanel, this);
        graphPanel.setBackground(Color.LIGHT_GRAY);

        graphPanel.addMouseListener(new GraphPanelMouseListener(graphPanel, gcp, this));

        MainWindowPanel.setLayout(new BoxLayout(MainWindowPanel, BoxLayout.X_AXIS));

        MainWindowPanel.add(gcp);
        MainWindowPanel.add(graphPanel);
    }
}
