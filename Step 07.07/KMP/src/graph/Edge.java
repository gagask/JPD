package graph;

import java.awt.*;

class VertexPair
{
    public final Vertex from;
    public final Vertex to;

    public VertexPair(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }
}

public class Edge {
    final Vertex from;
    final Vertex to;
    private Color color;

    private final int weight;
    private final int xc;
    private final int yc;

    public final boolean isToEqualsFrom;

    public Edge(Vertex from, Vertex to, Color color, int weight) {
        this.from = from;
        this.to = to;
        this.color = color;
        this.weight = weight;

        isToEqualsFrom = to.equals(from);

        xc = (from.getX() + to.getX()) / 2;
        yc = (from.getY() + to.getY()) / 2;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        String strWeight = String.valueOf(weight);

        if (!isToEqualsFrom) {
            g.drawLine(from.getX() + 20, from.getY() + 20, to.getX() + 20, to.getY() + 20);
            g.drawChars(strWeight.toCharArray(), 0, strWeight.length(), xc, yc);
        }
        else
        {
            int ovalX = 0;
            int ovalY = from.getY();
            int ovalWidth = 70;
            int ovalHeight = 30;

            if (from.getX() + from.radius() + ovalWidth / 2 >= 480)
                ovalX = from.getX() - from.radius() - ovalWidth / 2;
            else
                ovalX = from.getX() + from.radius();

            g.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);

            int distanceBetweenLetterAndOval = 3;
            int letterHeight = 5;

            int textX = ovalX + ovalWidth / 2 - strWeight.length() / 2;
            int textY = 0;
            if (from.getY() - distanceBetweenLetterAndOval <= letterHeight)
                textY = from.getY() + from.radius() * 2 + distanceBetweenLetterAndOval;
            else
                textY = from.getY() - distanceBetweenLetterAndOval;

            g.drawChars(strWeight.toCharArray(), 0, strWeight.length(), textX, textY);
        }
    }

    public boolean isIncidental(Vertex v1) {
        return from == v1 || to == v1;
    }

    public int getWeight() {
        return weight;
    }

    public VertexPair getEndings()
    {
        return new VertexPair(from, to);
    }

    public void changeColor(Color color)
    {
        this.color = color;
    }

}
