package GUI.listeners;

import graph.io.FileGraph;
import GUI.GraphPanel;
import graph.io.IOResult;
import GUI.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadItemListener implements ActionListener {

    private GraphPanel graphPanel;
    private MainWindow window;

    public LoadItemListener(GraphPanel graphPanel, MainWindow window)
    {
        this.graphPanel = graphPanel;
        this.window = window;
    }

    public void actionPerformed(ActionEvent e) {
        FileGraph fw = new FileGraph();
        IOResult result = fw.writeGraph(graphPanel.getGraph());

        if (result == IOResult.writeError)
            JOptionPane.showMessageDialog(window, "Ошибка при сохранении!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(window, "Граф сохранен успешно!", "Успех", JOptionPane.INFORMATION_MESSAGE);

    }
}
