package graph;

import graph.math.EdgeMath;
import graph.util.EdgeLineRepresentation;
import graph.util.EdgeOvalRepresentation;

import java.awt.*;

public class Edge extends EdgeMath {
    private Color color;
    private final int weight;

    public Edge(Vertex from, Vertex to, Color color, int weight) {
        super(from, to);

        this.color = color;
        this.weight = weight;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        String strWeight = String.valueOf(weight);

        if (!isToEqualsFrom) {
            EdgeLineRepresentation edgeLineRep = lineRepresentation();

            g.drawLine(edgeLineRep.getStartPoint().getX(), edgeLineRep.getStartPoint().getY(),
                    edgeLineRep.getEndPoint().getX(), edgeLineRep.getEndPoint().getY());

            g.setColor(Color.BLACK);
            g.drawChars(strWeight.toCharArray(), 0, strWeight.length(), edgeLineRep.getTextCoordinates().getX(),
                    edgeLineRep.getTextCoordinates().getY());
        }
        else
        {
            EdgeOvalRepresentation edgeOvalRepr = ovalRepresentation();

            g.drawOval(edgeOvalRepr.getOvalRepr().getX(), edgeOvalRepr.getOvalRepr().getY(),
                       edgeOvalRepr.getOvalRepr().getWidth(), edgeOvalRepr.getOvalRepr().getHeight());

            g.drawChars(strWeight.toCharArray(), 0, strWeight.length(), edgeOvalRepr.getTextCoordinates().getX(),
                    edgeOvalRepr.getTextCoordinates().getY());

        }
    }

    public void changeColor(Color color)
    {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}
