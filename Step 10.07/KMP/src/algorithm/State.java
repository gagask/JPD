package algorithm;

import graph.Graph;

public class State {
    final State prev;
    final AlgorithmState next;
    final Graph graph;

    public State()
    {
        prev = null;
        graph = null;
        next = null;
    }


    public State(State prev, Graph graph, AlgorithmState next)
    {
        this.prev = prev;
        this.graph = graph;
        this.next = next;
    }


    public Graph getGraph() {
        return graph;
    }

    public AlgorithmState getNext() {
        return next;
    }
}
