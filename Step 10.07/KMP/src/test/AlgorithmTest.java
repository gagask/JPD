package test;

import algorithm.Algorithm;
import algorithm.AlgorithmState;
import algorithm.State;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {

    @Test
    public void FirstVertex() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'a', Color.BLACK);

        graph.addVertex(v1);

        Algorithm algorithm = new Algorithm(graph);

        algorithm.initState();
        algorithm.FirstVertex();
        State actual = algorithm.state();
        assertEquals(AlgorithmState.End, actual.getNext());

        graph.addVertex(v2);
        graph.addEdge(new Edge(v1, v2, Color.BLACK, 10));

        algorithm.initState();
        algorithm.FirstVertex();
        actual = algorithm.state();
        assertEquals(AlgorithmState.MinEdge, actual.getNext());
    }

    @Test
    void MinEdge() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'a', Color.BLACK);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(new Edge(v1, v2, Color.BLACK, 10));

        Algorithm algorithm = new Algorithm(graph);
        algorithm.initState();
        algorithm.MinEdge();
        State actual = algorithm.state();
        assertEquals(AlgorithmState.MinEdge, actual.getNext());
    }

    @Test
    void restart() {
        Graph graph = new Graph();
        Algorithm algorithm = new Algorithm(graph);

        algorithm.initState();
        algorithm.restart();
        State actual = algorithm.state();
        assertEquals(AlgorithmState.FirstVertex, actual.getNext());
    }
}