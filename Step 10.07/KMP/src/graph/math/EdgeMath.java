package graph.math;

import graph.Vertex;
import graph.util.*;

public class EdgeMath {
    private final Vertex from;
    private final Vertex to;

    private final int xc;
    private final int yc;

    protected final boolean isToEqualsFrom;


    public EdgeMath(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;

        xc = (from.getX() + to.getX() + 40) / 2;
        yc = (from.getY() + to.getY() + 40) / 2;

        isToEqualsFrom = to.equals(from);
    }

    public boolean isIncidental(Vertex v1) {
        return from == v1 || to == v1;
    }

    public VertexPair getEndings()
    {
        return new VertexPair(from, to);
    }

    public EdgeLineRepresentation lineRepresentation()
    {
        return new EdgeLineRepresentation(new Point(from.getX() + 20, from.getY() + 20),
                                          new Point(to.getX() + 20, to.getY() + 20),
                                          new Point(xc, yc));
    }

    public EdgeOvalRepresentation ovalRepresentation()
    {
        int ovalX = 0;
        int ovalY = from.getY();
        int ovalWidth = 70;
        int ovalHeight = 30;

        if (from.getX() + from.radius() + ovalWidth / 2 >= 480)
            ovalX = from.getX() - from.radius() - ovalWidth / 2;
        else
            ovalX = from.getX() + from.radius();

        OvalRepresentation ovalRepr = new OvalRepresentation(ovalX, ovalY, ovalHeight, ovalWidth);

        int distanceBetweenLetterAndOval = 3;
        int letterHeight = 5;

        int textX = ovalX + ovalWidth / 2;
        int textY = 0;
        if (from.getY() - distanceBetweenLetterAndOval <= letterHeight)
            textY = from.getY() + from.radius() * 2 + distanceBetweenLetterAndOval;
        else
            textY = from.getY() - distanceBetweenLetterAndOval;

        Point textCoordinates = new Point(textX, textY);

        return new EdgeOvalRepresentation(ovalRepr, textCoordinates);
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }
}
