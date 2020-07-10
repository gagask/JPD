package test;

import graph.Vertex;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class VertexMathTest {

    @Test
    void contains()
    {
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);

        assertEquals(v1.contains(110, 100), false);
        assertEquals(v1.contains(115, 110), true);
        assertEquals(v1.contains(200, 200), false);
    }

    @Test
    void touch() {
        Vertex v1 = new Vertex(100, 100, 40, 'a', Color.BLACK);
        Vertex v2 = new Vertex(110, 110, 40, 'b', Color.BLACK);

        assertEquals(v1.touch(v2), true);
        assertEquals(v2.touch(v1), true);
    }
}