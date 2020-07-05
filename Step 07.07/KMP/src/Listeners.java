
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

abstract class ListenerBase implements ActionListener{
    protected MainWindow window;

    public ListenerBase(MainWindow a){
        window = a;
    }
}

class addVertexButtonListener extends ListenerBase {


    public addVertexButtonListener(MainWindow a) {
        super(a);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setButtonState(ButtonState.addVertex);
    }
}

class addEdgeButtonListener extends ListenerBase{

    public addEdgeButtonListener(MainWindow a) {
        super(a);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setButtonState(ButtonState.addEdge);
    }
}

class removeVertexButtonListener extends ListenerBase{

    public removeVertexButtonListener(MainWindow a) {
        super(a);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setButtonState(ButtonState.removeVertex);
    }
}

class StartButtonListener extends ListenerBase{

    public StartButtonListener(MainWindow a) {
        super(a);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            window.getGraph().AlgorithPrima();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}

class removeEdgeButtonListener extends ListenerBase{

    public removeEdgeButtonListener(MainWindow a) {
        super(a);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setButtonState(ButtonState.removeEdge);
    }
}

class TextAreaActionListener extends ListenerBase
{
    WeightDialog dialog;

    public TextAreaActionListener(MainWindow a, WeightDialog dialog)
    {
        super(a);
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.loadWeight();
        dialog.setVisible(false);
    }
}

class WeightDialogWindowListener extends WindowAdapter
{
    private WeightDialog dialog;

    public  WeightDialogWindowListener(WeightDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dialog.notOk();
        super.windowClosing(e);
    }
}


class GraphPanelMouseListener implements MouseListener{
    private final GraphPanel panel;
    private final MainWindow window;
    private Vertex VertexSave = null;

    public GraphPanelMouseListener(GraphPanel panel, MainWindow window){
        this.panel = panel;
        this.window = window;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (window.getButtonState())
        {
            case addVertex:
                panel.addVertex(new Vertex(e.getX() - 20, e.getY() - 20, 40, Color.RED));
                window.setButtonState(ButtonState.noButton);
                break;
            case addEdge:
                Vertex addEdgeResult = panel.getContainingVertex(e.getX(), e.getY());
                if (addEdgeResult != null)
                {
                    if (VertexSave != null) {

                        WeightDialog dialog = new WeightDialog(window);
                        dialog.setVisible(true);

                        boolean dialogResult = dialog.isOk();
                        if (!dialogResult)
                        {
                            window.setButtonState(ButtonState.noButton);
                            VertexSave = null;
                            return;
                        }

                        panel.addEdge(new Edge(VertexSave, addEdgeResult, Color.RED, dialog.getWeight()));
                        window.setButtonState(ButtonState.noButton);
                        VertexSave = null;
                    }
                    else
                        VertexSave = addEdgeResult;
                }
                break;
            case removeEdge:
                Vertex removeEdgeResult = panel.getContainingVertex(e.getX(), e.getY());
                if (removeEdgeResult != null)
                {
                    if (VertexSave != null) {
                        panel.removeEdge(VertexSave, removeEdgeResult);
                        window.setButtonState(ButtonState.noButton);
                        VertexSave = null;
                    }
                    else
                        VertexSave = removeEdgeResult;
                }
                break;
            case removeVertex:
                Vertex removeVertex = panel.getContainingVertex(e.getX(), e.getY());
                if (removeVertex != null)
                {
                    ArrayList<Edge> result = panel.getIncidentalEdges(removeVertex);
                    for (Edge edge : result)
                        panel.removeEdge(edge);
                    panel.removeVertex(removeVertex);
                    window.setButtonState(ButtonState.noButton);
                }


        }
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