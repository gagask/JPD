package test;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void isConnected() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'b', Color.BLACK);
        Vertex v3 = new Vertex(200, 200, 40, 'b', Color.BLACK);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        boolean actual = graph.isConnected();
        assertEquals(false, actual);

        graph.addEdge(new Edge(v1, v2, Color.GREEN, 10));
        graph.removeVertex(v3);

        actual = graph.isConnected();
        assertEquals(true, actual);
    }

    @Test
    void isEmpty()
    {
        Graph graph = new Graph();
        boolean actual = graph.isEmpty();
        assertEquals(true, actual);

        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        graph.addVertex(v1);
        graph.addEdge(new Edge(v1, v1, Color.GREEN, 10));
        actual = graph.isEmpty();
        assertEquals(false, actual);

        graph.removeVertex(v1);
        actual = graph.isEmpty();
        assertEquals(true, actual);

    }

    @Test
    void getIncidentalEdges()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'b', Color.BLACK);

        graph.addVertex(v1);
        graph.addVertex(v2);

        ArrayList<Edge> actual = graph.getIncidentalEdges(v1);
        assertEquals( true, actual.isEmpty());

        graph.addEdge(new Edge(v1, v1, Color.GREEN, 10));

        actual = graph.getIncidentalEdges(v1);
        assertEquals( false, actual.isEmpty());

        graph.addEdge(new Edge(v1, v2, Color.GREEN, 10));

        actual = graph.getIncidentalEdges(v2);
        assertEquals( false, actual.isEmpty());
    }

    @Test
    void contains()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'b', Color.BLACK);

        boolean actual = graph.contains('a');
        assertEquals(false, actual);

        graph.addVertex(v1);
        graph.addVertex(v2);

        actual = graph.contains('a');
        assertEquals(true, actual);

    }
}