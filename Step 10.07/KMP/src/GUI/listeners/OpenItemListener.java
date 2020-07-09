package GUI.listeners;

import graph.io.FileGraph;
import GUI.GraphPanel;
import graph.io.IOResult;
import GUI.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenItemListener implements ActionListener {

    private GraphPanel graphPanel;
    private MainWindow window;

    public OpenItemListener(GraphPanel graphPanel, MainWindow window)
    {
        this.graphPanel = graphPanel;
        this.window = window;
    }

    public void actionPerformed(ActionEvent e) {
        FileGraph fw = new FileGraph();
        IOResult result = fw.loadGraph();

        if (result == IOResult.loadDataError || result == IOResult.loadError)
        {
            JOptionPane.showMessageDialog(window, "Ошибка при загрузке!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        graphPanel.setGraph(fw.getGraph());
        graphPanel.repaint();
        JOptionPane.showMessageDialog(window, "Загрузка графа прошла успешно!", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }
}
