import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    public void actionPerformed(ActionEvent e) {
        window.setButtonState(ButtonState.Start);
    }
}

class GraphPanelMouseListener implements MouseListener{
    private final GraphPanel panel;
    private final MainWindow window;

    public GraphPanelMouseListener(GraphPanel panel, MainWindow window){
        this.panel = panel;
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (window.getButtonState())
        {
            case addVertex:
                panel.addVertex(new Vertex(e.getX() - 20, e.getY() - 20, 40, Color.BLACK));
                window.setButtonState(ButtonState.noButton);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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