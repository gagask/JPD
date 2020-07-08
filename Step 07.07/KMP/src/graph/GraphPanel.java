package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GraphPanel extends JPanel {

    private final JPanel MainWindowPanel;
    private final Graph g = new Graph();
    private final JFrame window;

    public GraphPanel(JPanel MainWindowPanel, JFrame window) {
        this.MainWindowPanel = MainWindowPanel;
        this.window = window;
    }

    public void activateAlgorithm(){
        Random random = new Random();

        if (g.vertexList.size() == 0) {
            JOptionPane.showMessageDialog(window, "Граф не введен!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        final int[] k = {1};
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("lox bluat ebanui");
                k[0] = 0;
            }
        });
        timer.setRepeats(false);
        timer.setInitialDelay(0);
        timer.start();

        int startVertexInd = random.nextInt(g.vertexList.size());
        g.vertexList.get(startVertexInd).changeColor(Color.GREEN);
        MainWindowPanel.repaint();
        while (k[0] == 1)
        {}

        HashSet<Vertex> resultVertexSet = new HashSet<Vertex>();
        resultVertexSet.add(g.vertexList.get(startVertexInd));

        HashSet<Edge> resultEdgeSet = new HashSet<Edge>();
        HashSet<Edge> curTreeEdge = new HashSet<Edge>();

        while (resultVertexSet.size() != g.vertexList.size())
        {

            for (Vertex v : resultVertexSet)
            {
                ArrayList<Edge> tmpEdgeList = g.getIncidentalEdges(v);

                for (Edge e : tmpEdgeList)
                {
                    VertexPair curEdgeEndings = e.getEndings();
                    Vertex curNewVertex = curEdgeEndings.from;
                    if (v == curNewVertex)
                        curNewVertex = curEdgeEndings.to;

                    if (!resultVertexSet.contains(curNewVertex))
                        curTreeEdge.add(e);

                }

            }

            Edge min = null;
            for (Edge e : curTreeEdge)
            {
                e.changeColor(Color.YELLOW);
                k[0] = 1;
                while (k[0] == 1)
                {}

                MainWindowPanel.repaint();
                if (min == null || e.getWeight() < min.getWeight())
                    min = e;
            }

            if (min == null)
            {
                JOptionPane.showMessageDialog(window, "Граф должен быть связным!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            min.changeColor(Color.GREEN);

            VertexPair endings = min.getEndings();
            resultEdgeSet.add(min);

            Vertex curNewVertex = endings.from;
            if (resultVertexSet.contains(curNewVertex))
                curNewVertex = endings.to;
            resultVertexSet.add(curNewVertex);

            curTreeEdge.clear();
        }

        g.edgeList = new ArrayList<Edge>(resultEdgeSet);
        
        MainWindowPanel.repaint();
    }

    public boolean addVertex(Vertex vertex) {
        boolean result = g.isVertexCoordinatesCorrect(vertex);
        if (result) {
            g.addVertex(vertex);
            MainWindowPanel.repaint();
        }

        return result;
    }

    public void addEdge(Edge edge) {
        Edge e = g.getEdge(edge.from, edge.to);
        if (e != null) {
            JOptionPane.showMessageDialog(window, "Ребро между выбранными вершинами уже существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        g.addEdge(edge);
        MainWindowPanel.repaint();
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        Edge e = g.getEdge(v1, v2);
        if (e == null)
        {
            JOptionPane.showMessageDialog(window, "Ребра между выбранными вершинами не существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        g.deleteEdge(e);
        MainWindowPanel.repaint();
    }

    public void removeEdge(Edge e) {
        g.deleteEdge(e);
        MainWindowPanel.repaint();
    }

    public void removeVertex(Vertex v) {
        g.deleteVertex(v);
        MainWindowPanel.repaint();
    }

    @Override
    public void paint(Graphics grph) {
        g.draw(grph);
        MainWindowPanel.repaint();
    }

    public Vertex getContainingVertex(int x, int y)
    {
        return g.getContainingVertex(x, y);
    }

    public ArrayList<Edge> getIncidentalEdges(Vertex v)
    {
        return g.getIncidentalEdges(v);
    }
}

