import com.mxgraph.view.mxGraph;

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

class MainWindowMouseListener implements MouseListener{
    private mxGraph graph;
    private MainWindow window;

    public MainWindowMouseListener(mxGraph a, MainWindow b){
        graph = a;
        window = b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(window.getButtonState() == ButtonState.addVertex){
            graph.insertVertex(graph.getDefaultParent(),null, "someNew", e.getX(),e.getY(),80,30);
            window.setButtonState(ButtonState.noButton);
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