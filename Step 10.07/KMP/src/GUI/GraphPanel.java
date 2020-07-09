package GUI;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    private final JPanel MainWindowPanel;
    private final MainWindow window;
    private Graph graph;

    public GraphPanel(JPanel MainWindowPanel, MainWindow window) {
        this.MainWindowPanel = MainWindowPanel;
        this.window = window;

        graph = new Graph();
    }

    public void add(Vertex vertex)
    {
        graph.addVertex(vertex);
        MainWindowPanel.repaint();
    }

    public void add(Edge edge)
    {
        Edge e = graph.getEdge(edge.getFrom(), edge.getTo());
        if (e != null) {
            JOptionPane.showMessageDialog(window, "Ребро между выбранными вершинами уже существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        graph.addEdge(edge);
        MainWindowPanel.repaint();
    }

    public void remove(Vertex v1, Vertex v2)
    {
        Edge e = graph.getEdge(v1, v2);
        if (e == null)
        {
            JOptionPane.showMessageDialog(window, "Ребра между выбранными вершинами не существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        graph.removeEdge(e);
        MainWindowPanel.repaint();
    }

    public void remove(Edge edge)
    {
        graph.removeEdge(edge);
        MainWindowPanel.repaint();
    }

    public void remove(Vertex vertex)
    {
        graph.removeVertex(vertex);
        MainWindowPanel.repaint();
    }

    @Override
    public void paint(Graphics grph) {
        super.paint(grph);
        graph.draw(grph);
        MainWindowPanel.repaint();
    }


    public Vertex getContainingVertex(int x, int y)
    {
        return graph.getContainingVertex(x, y);
    }

    public void setGraph(Graph graph) {
        this.graph = new Graph(graph);
    }

    public Graph getGraph() {
        return graph;
    }
}

