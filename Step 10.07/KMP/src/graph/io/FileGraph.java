package graph.io;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FileGraph {

    Graph graph = new Graph();

    public IOResult loadGraph(){

        String vertexDataPattern = ".x\\d{3}y\\d{3}";
        String edgeDataPattern = "w\\d+f.t.";
        HashMap<Character, Vertex> d = new HashMap<Character, Vertex>();

        FileReader fileReader;
        try {
            fileReader = new FileReader("save.txt");
        }
        catch (IOException e) {
            return IOResult.loadError;
        }

        Scanner scanner = new Scanner(fileReader);
        Graph graph = new Graph();
        while (scanner.hasNextLine())
        {
            String cur = scanner.nextLine();
            if (cur.equals("."))
                break;

            if (cur.matches(vertexDataPattern))
            {
                try {
                    Vertex vertex = new Vertex(Integer.parseInt(cur.substring(2, 5)),
                                               Integer.parseInt(cur.substring(6, 9)), 40, cur.toCharArray()[0], Color.BLACK);
                    d.put(cur.toCharArray()[0], vertex);
                    graph.addVertex(vertex);
                }
                catch (NumberFormatException e)
                {
                    return IOResult.loadDataError;
                }
            }
            else {
                return IOResult.loadDataError;
            }
        }

        while (scanner.hasNextLine())
        {
            String cur = scanner.nextLine();

            if (cur.matches(edgeDataPattern))
            {
                try {
                    char nameFrom = cur.toCharArray()[cur.length() - 3];
                    char nameTo = cur.toCharArray()[cur.length() -1];

                    if (!d.containsKey(nameFrom) || !d.containsKey(nameTo))
                        return IOResult.loadDataError;

                    graph.addEdge(new Edge(d.get(nameFrom), d.get(nameTo), Color.BLACK, Integer.parseInt(cur.substring(1, cur.length() - 4))));
                }
                catch (NumberFormatException e)
                {
                    return IOResult.loadDataError;
                }
            }
        }

        this.graph = graph;
        return IOResult.loadSuccess;
    }

    public IOResult writeGraph(Graph graph) {
        FileWriter wr;
        try {
            wr = new FileWriter("save.txt");
        }
        catch (IOException e) {
            return IOResult.writeError;
        }

        HashSet<Vertex> vertices = graph.getVertexList();
        StringBuilder graphInfo = new StringBuilder();
        for (Vertex v : vertices) {
            StringBuilder vertexInfo = new StringBuilder();
            String xCoordinate = coordinateConverter(v.getX());
            String yCoordinate = coordinateConverter(v.getY());

            vertexInfo.append(v.getName());
            vertexInfo.append("x");
            vertexInfo.append(xCoordinate);
            vertexInfo.append("y");
            vertexInfo.append(yCoordinate);
            vertexInfo.append("\n");

            graphInfo.append(vertexInfo);
        }
        graphInfo.append(".\n");

        HashSet<Edge> edges = graph.getEdgeList();
        for (Edge e : edges)
        {
            StringBuilder edgeInfo =  new StringBuilder();

            edgeInfo.append("w");
            edgeInfo.append(e.getWeight());
            edgeInfo.append("f");
            edgeInfo.append(e.getFrom().getName());
            edgeInfo.append("t");
            edgeInfo.append(e.getTo().getName());
            edgeInfo.append("\n");

            graphInfo.append(edgeInfo);
        }

        try {
            wr.write(graphInfo.toString());
            wr.close();
        }
        catch (IOException e)
        {
            return IOResult.writeError;
        }

        return IOResult.writeSuccess;
    }

    private String coordinateConverter(int n) {
        String nStringRepresentation = String.valueOf(n);
        StringBuilder result = new StringBuilder();

        int zerosCnt = 3 - nStringRepresentation.length();
        while (zerosCnt != 0)
        {
            result.append(0);
            --zerosCnt;
        }

        result.append(nStringRepresentation);

        return result.toString();

    }

    public Graph getGraph() {
        return graph;
    }
}
