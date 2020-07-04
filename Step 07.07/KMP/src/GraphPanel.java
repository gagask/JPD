import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList = new ArrayList<Edge>();

    private final JPanel MainWindowPanel;

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

        for (Edge e : edgeList)
            e.draw(g);
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
    }
}
