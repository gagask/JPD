import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import java.util.List;

public class GraphPanel extends JPanel {
    private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList = new ArrayList<Edge>();

    private final JPanel MainWindowPanel;
    private final static long REFRESH_LIST_PERIOD=10 * 60 * 1000; //10 minutes

    public GraphPanel(JPanel MainWindowPanel)
    {
        this.MainWindowPanel = MainWindowPanel;
    }


    public void addVertex(Vertex vertex)
    {
        vertexList.add(vertex);
        MainWindowPanel.repaint();
    }

    public void addEdge(Edge edge)
    {
        edgeList.add(edge);
        MainWindowPanel.repaint();
    }

    @Override
    public void paint(Graphics g) {
        for (Vertex v : vertexList)
            v.draw(g);

        for (Edge e : edgeList) {
            e.draw(g);

        }
    }

    public Vertex getContainingVertex(int x, int y)
    {
        for (Vertex v : vertexList)
        {
            if (v.contains(x, y))
                return v;
        }

        return null;
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        removeEdge(getEdge(v1, v2));
    }

    public void removeEdge(Edge e)
    {
        if (e != null) {
            edgeList.remove(e);
            MainWindowPanel.repaint();
        }

    }

    public Edge getEdge(Vertex v1, Vertex v2)
    {
        for (Edge e : edgeList)
        {
            if (e.isIncidental(v1) && e.isIncidental(v2))
                return e;
        }

        return null;
    }

    public ArrayList<Edge> getIncidentalEdges(Vertex v)
    {
        ArrayList<Edge> result = new ArrayList<Edge>();
        for (Edge e : edgeList) {
            if (e.isIncidental(v))
                result.add(e);
        }

        return result;

    }

    public void removeVertex(Vertex v) {
        vertexList.remove(v);
        MainWindowPanel.repaint();
    }

    public void AlgorithPrima() throws InterruptedException {
        Random random = new Random();

        int startVertexInd = random.nextInt(vertexList.size());

        HashSet<Vertex> resultVertexSet = new HashSet<Vertex>();
        resultVertexSet.add(vertexList.get(startVertexInd));

        Timer timer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ea) {
                vertexList.get(startVertexInd).changeColor(Color.GREEN);
                MainWindowPanel.repaint();
            }
        });
        timer.start();
        Thread.sleep(7000);

        HashSet<Edge> resultEdgeSet = new HashSet<Edge>();
        HashSet<Edge> curTreeEdge = new HashSet<Edge>();

        while (resultVertexSet.size() != vertexList.size())
        {

            for (Vertex v : resultVertexSet)
            {
                ArrayList<Edge> tmpEdgeList = getIncidentalEdges(v);

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
                if (min == null || e.getWeight() < min.getWeight())
                    min = e;
            }

            VertexPair endings = min.getEndings();
            resultEdgeSet.add(min);

            min.changeColor(Color.GREEN);

            Vertex curNewVertex = endings.from;
            if (resultVertexSet.contains(curNewVertex))
                curNewVertex = endings.to;

            curNewVertex.changeColor(Color.GREEN);

            resultVertexSet.add(curNewVertex);

            curTreeEdge.clear();
        }

        edgeList = new ArrayList<Edge>(resultEdgeSet);
    }
}
