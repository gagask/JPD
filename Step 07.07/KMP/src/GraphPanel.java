import javax.swing.*;
import java.awt.*;
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

    @Override
    public void paint(Graphics g) {
        for (Vertex v : vertexList)
            v.draw(g);
    }
}
