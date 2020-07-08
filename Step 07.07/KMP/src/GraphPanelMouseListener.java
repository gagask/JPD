import panel.ButtonState;
import panel.GraphCommandPanel;
import graph.Edge;
import graph.GraphPanel;
import graph.Vertex;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GraphPanelMouseListener implements MouseListener{
    private final GraphPanel panel;
    private final GraphCommandPanel gcp;
    private final MainWindow window;
    private Vertex VertexSave = null;

    public GraphPanelMouseListener(GraphPanel panel, GraphCommandPanel gcp, MainWindow window){
        this.panel = panel;
        this.gcp = gcp;
        this.window = window;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (gcp.ButtonState())
        {
            case addVertex:
                addVertexCase(e);
                break;
            case addEdge:
                addEdgeCase(e);
                break;
            case removeEdge:
                removeEdgeCase(e);
                break;
            case removeVertex:
                removeVertexCase(e);
                break;
        }
    }

    private void removeVertexCase(MouseEvent e)
    {
        Vertex removeVertex = panel.getContainingVertex(e.getX(), e.getY());
        if (removeVertex != null)
        {
            ArrayList<Edge> result = panel.getIncidentalEdges(removeVertex);
            for (Edge edge : result)
                panel.removeEdge(edge);
            panel.removeVertex(removeVertex);
            gcp.setButtonState(ButtonState.noButton);
        }
    }

    private void removeEdgeCase(MouseEvent e)
    {
        Vertex removeEdgeResult = panel.getContainingVertex(e.getX(), e.getY());
        if (removeEdgeResult != null)
        {
            if (VertexSave != null) {
                panel.removeEdge(VertexSave, removeEdgeResult);
                gcp.setButtonState(ButtonState.noButton);
                VertexSave = null;
            }
            else
                VertexSave = removeEdgeResult;
        }
    }

    private void addEdgeCase(MouseEvent e)
    {
        Vertex addEdgeResult = panel.getContainingVertex(e.getX(), e.getY());
        if (addEdgeResult != null)
        {
            if (VertexSave != null) {

                WeightDialog dialog = new WeightDialog(window);
                dialog.setVisible(true);

                boolean dialogResult = dialog.isOk();
                if (!dialogResult)
                {
                    gcp.setButtonState(ButtonState.noButton);
                    VertexSave = null;
                    return;
                }

                panel.addEdge(new Edge(VertexSave, addEdgeResult, Color.BLACK, dialog.getWeight()));
                gcp.setButtonState(ButtonState.noButton);
                VertexSave = null;
            }
            else
                VertexSave = addEdgeResult;
        }
    }


    private void addVertexCase(MouseEvent e)
    {
        boolean result = panel.addVertex(new Vertex(e.getX() - 20, e.getY() - 20, 40, Color.BLACK));
        if (result)
            gcp.setButtonState(ButtonState.noButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}