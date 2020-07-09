package GUI.listeners;

import GUI.ButtonState;
import GUI.GraphCommandPanel;
import GUI.GraphPanel;
import GUI.MainWindow;
import GUI.dialog.NameDialog;
import GUI.dialog.WeightDialog;
import graph.Edge;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GraphPanelMouseListener extends MouseAdapter{
    private final GraphPanel graphPanel;
    private final GraphCommandPanel gcp;
    private final MainWindow window;
    private Vertex VertexSave = null;

    public GraphPanelMouseListener(GraphPanel graphPanel, GraphCommandPanel gcp, MainWindow window){
        this.graphPanel = graphPanel;
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
        Vertex removeVertex = graphPanel.getContainingVertex(e.getX(), e.getY());
        if (removeVertex != null)
        {
            graphPanel.remove(removeVertex);
            gcp.setButtonState(ButtonState.noButton);
        }
    }

    private void removeEdgeCase(MouseEvent e)
    {
        Vertex removeEdgeResult = graphPanel.getContainingVertex(e.getX(), e.getY());
        if (removeEdgeResult != null)
        {
            if (VertexSave != null) {
                graphPanel.remove(VertexSave, removeEdgeResult);
                gcp.setButtonState(ButtonState.noButton);
                VertexSave = null;
            }
            else
                VertexSave = removeEdgeResult;
        }
    }

    private void addEdgeCase(MouseEvent e)
    {
        Vertex addEdgeResult = graphPanel.getContainingVertex(e.getX(), e.getY());
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

                graphPanel.add(new Edge(VertexSave, addEdgeResult, Color.BLACK, dialog.getWeight()));
                gcp.setButtonState(ButtonState.noButton);
                VertexSave = null;
            }
            else
                VertexSave = addEdgeResult;
        }
    }


    private void addVertexCase(MouseEvent e)
    {
        if (!graphPanel.getGraph().isVertexCoordinatesCorrect(new Vertex(e.getX(), e.getY(), 40, 'a', Color.BLACK)))
            return;

        NameDialog dialog = new NameDialog(window);
        dialog.setVisible(true);

        boolean dialogResult = dialog.isOk();
        if (dialogResult) {
            char name = dialog.getVertexName();
            if (graphPanel.getGraph().contains(name))
            {
                JOptionPane.showMessageDialog(window, "Вершина с данным именем уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                gcp.setButtonState(ButtonState.noButton);
                return;
            }

            graphPanel.add(new Vertex(e.getX() - 20, e.getY() - 20, 40, name, Color.BLACK));
            gcp.setButtonState(ButtonState.noButton);
        }
    }
}