package graph;

import graph.math.GraphMath;

import java.awt.*;

public class Graph extends GraphMath {

    public Graph()
    {
        super();
    }

    public Graph(Graph other)
    {
        super(other);
    }

    public void draw(Graphics grph) {
        for (Edge e : edgeList)
            e.draw(grph);

        for (Vertex v : vertexList)
            v.draw(grph);
    }


}
