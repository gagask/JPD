package test;

import graph.Edge;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EdgeMathTest {

    @Test
    void isIncidental()
    {
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'b', Color.BLACK);
        Edge e = new Edge(v1, v1, Color.BLACK, 10);

        assertEquals( true, e.isIncidental(v1));

        Edge e2 = new Edge(v1, v2, Color.BLACK, 10);

        assertEquals(true, e2.isIncidental(v2));
    }

    @Test
    void getEndings() {
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(200, 200, 40, 'b', Color.BLACK);
        Edge e = new Edge(v1, v1, Color.BLACK, 10);

        assertEquals(e.getEndings().from, e.getFrom());
    }
}