package algorithm;

import GUI.GraphPanel;
import GUI.MainWindow;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.util.VertexPair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Algorithm {
    private GraphPanel graphPanel;
    private Graph graph;

    private State state = new State(null, null, AlgorithmState.FirstVertex);


    public Algorithm(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.graph = graphPanel.getGraph();
    }

    public void initState()
    {
        state = new State(null, new Graph(graph), AlgorithmState.FirstVertex);
    }

    public void FirstVertex() {
        state = new State(state, new Graph(state.graph), AlgorithmState.MinEdge);

        Random random = new Random();

        int startVertexInd = random.nextInt(state.graph.getVertexList().size());
        int i = 0;
        Vertex startVertex = null;
        for (Vertex v : state.graph.getVertexList())
        {
            if (i == startVertexInd)
            {
                startVertex = v;
                break;
            }
            ++i;
        }
        startVertex.setColor(Color.GREEN);

        if (state.graph.getVertexList().size() == 1)
            state = new State(state.prev, new Graph(state.graph), AlgorithmState.End);
    }

    public void MinEdge() {
        state = new State(state, new Graph(state.graph), AlgorithmState.MinEdge);

        ArrayList<Vertex> curVertexList = new ArrayList<Vertex>();
        for (Vertex v : state.graph.getVertexList())
            if (v.getColor() == Color.GREEN)
                curVertexList.add(v);

        HashSet<Edge> curTreeEdge = new HashSet<Edge>();

        for (Vertex v : curVertexList) {
            ArrayList<Edge> tmpEdgeList = state.graph.getIncidentalEdges(v);

            for (Edge e : tmpEdgeList) {
                VertexPair curEdgeEndings = e.getEndings();
                Vertex curNewVertex = curEdgeEndings.from;
                if (v == curNewVertex)
                    curNewVertex = curEdgeEndings.to;

                if (!curVertexList.contains(curNewVertex))
                    curTreeEdge.add(e);
            }
        }

        Edge min = null;
        for (Edge e : curTreeEdge) {
            if (min == null || e.getWeight() < min.getWeight())
                min = e;
        }

        min.changeColor(Color.GREEN);
        VertexPair endings = min.getEndings();

        Vertex curNewVertex = endings.from;
        if (curVertexList.contains(curNewVertex))
            curNewVertex = endings.to;

        curNewVertex.setColor(Color.GREEN);
        curVertexList.add(curNewVertex);

        if (curVertexList.size() == graph.getVertexList().size())
            state = new State(state.prev, new Graph(state.graph), AlgorithmState.End);
    }

    public void nextStep()
    {
        if (state.next == AlgorithmState.FirstVertex) {
            initState();
            FirstVertex();
        }
        else if (state.next == AlgorithmState.MinEdge)
            MinEdge();

        graphPanel.setGraph(state.graph);
        graphPanel.repaint();
    }

    public void prevStep()
    {
        state = state.prev;
        graphPanel.setGraph(state.graph);
        graphPanel.repaint();
    }

    public void restart()
    {
        initState();
        graphPanel.setGraph(state.graph);
        graphPanel.repaint();
    }

    public void forceAns()
    {
        while (state.next != AlgorithmState.End)
            nextStep();
    }

    public boolean isEnd()
    {
        return state.next == AlgorithmState.End;
    }

    public boolean isStart()
    {
        return state.next == AlgorithmState.FirstVertex;
    }

    public Graph getDefaultGraph() {
        initState();
        return graph;
    }

    public void updateGraph(Graph graph)
    {
        this.graph = graph;
        initState();
    }
}
