package graph;

import java.awt.*;
import java.util.ArrayList;

public class Graph {
    ArrayList<Vertex> vertexList;
    ArrayList<Edge> edgeList;

    Graph()
    {
        vertexList = new ArrayList<Vertex>();
        edgeList = new ArrayList<Edge>();
    }

    public void addVertex(Vertex v)
    {
        vertexList.add(v);
    }

    public void addEdge(Edge e) {
        edgeList.add(e);
    }

    public void deleteEdge(Edge e) {
        edgeList.remove(e);
    }

    public void deleteVertex(Vertex v)
    {
        vertexList.remove(v);
    }

    public void draw(Graphics grph) {
        for (Vertex v : vertexList)
            v.draw(grph);

        for (Edge e : edgeList)
            e.draw(grph);
    }

    public ArrayList<Edge> getIncidentalEdges(Vertex v)
    {
        ArrayList<Edge> result = new ArrayList<>();
        for (Edge e : edgeList) {
            if (e.isIncidental(v))
                result.add(e);
        }

        return result;

    }

    public Edge getEdge(Vertex v1, Vertex v2) {
        for (Edge e : edgeList) {
            if ((e.isIncidental(v1) && e.isIncidental(v2) && v1 != v2) || (e.isToEqualsFrom && e.isIncidental(v1) && v1 == v2))
                return e;
        }

        return null;
    }

    public Vertex getContainingVertex(int x, int y) {
        for (Vertex v : vertexList) {
            if (v.contains(x, y))
                return v;
        }

        return null;
    }

    public boolean isVertexCoordinatesCorrect(Vertex vertex)
    {
        for (Vertex cur : vertexList)
            if (cur.touch(vertex))
                return false;

        return true;

    }
}
