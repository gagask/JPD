package graph.math;

import graph.Edge;
import graph.Vertex;
import graph.util.VertexPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class GraphMath {
    protected HashSet<Vertex> vertexList;
    protected HashSet<Edge> edgeList;

    public GraphMath()
    {
        vertexList = new HashSet<Vertex>();
        edgeList = new HashSet<Edge>();
    }

    public GraphMath(GraphMath other)
    {
        vertexList = new HashSet<Vertex>();
        edgeList = new HashSet<Edge>();

        if (other.vertexList.size() == 0)
            return;

        HashMap<Vertex, Vertex> d = new HashMap<Vertex, Vertex>(other.vertexList.size());

        for (Vertex v : other.vertexList)
        {
            Vertex copyV = new Vertex(v.getX(), v.getY(), v.radius() * 2, v.getName(), v.getColor());
            this.addVertex(copyV);
            d.put(v, copyV);
        }

        for (Edge e : other.edgeList)
        {
            VertexPair vertexPair = e.getEndings();
            this.addEdge(new Edge(d.get(vertexPair.from), d.get(vertexPair.to), e.getColor(), e.getWeight()));
        }

        int lox = 0;
    }

    public void addVertex(Vertex vertex)
    {
        vertexList.add(vertex);
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    public void removeEdge(Edge e) {
        edgeList.remove(e);
    }

    public void removeVertex(Vertex vertex)
    {
        ArrayList<Edge> result = getIncidentalEdges(vertex);
        for (Edge edge : result)
            edgeList.remove(edge);

        vertexList.remove(vertex);
    }

    public boolean isConnected() {
        if (vertexList.size() == 1)
            return true;

        Vertex v = (Vertex) (vertexList.toArray()[0]);
        HashSet<Vertex> result = new HashSet<>();
        getAchievableVertices(v, result);

        return vertexList.size() == result.size();
    }

    private void getAchievableVertices(Vertex v, HashSet<Vertex> result)
    {
        ArrayList<Edge> edges = getIncidentalEdges(v);

        for (Edge e : edges)
        {
            VertexPair vertexPair =  e.getEndings();
            Vertex newVertex = vertexPair.from;
            if (vertexPair.from == v)
                newVertex = vertexPair.to;
            if (!result.contains(newVertex)) {
                result.add(newVertex);
                getAchievableVertices(newVertex, result);
            }

        }


    }

    public boolean isEmpty()
    {
        return vertexList.isEmpty();
    }

    public ArrayList<Edge> getIncidentalEdges(Vertex v) {
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

    public HashSet<Vertex> getVertexList() {
        return vertexList;
    }

    public HashSet<Edge> getEdgeList() {
        return edgeList;
    }

    public boolean contains(char name) {
        for (Vertex v : vertexList)
            if (v.getName() == name)
                return true;


        return false;
    }
}
