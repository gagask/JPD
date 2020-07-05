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
    private final Vertex from;
    private final Vertex to;
    private Color color;

    private final int weight;
    private final int xc;
    private final int yc;

    public Edge(Vertex from, Vertex to, Color color, int weight) {
        this.from = from;
        this.to = to;
        this.color = color;
        this.weight = weight;

        xc = (from.getX() + to.getX()) / 2;
        yc = (from.getY() + to.getY()) / 2;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawLine(from.getX() + 20, from.getY() + 20, to.getX() + 20, to.getY() + 20);

        String strWeight = String.valueOf(weight);
        g.drawChars(strWeight.toCharArray(), 0, strWeight.length(), xc, yc);
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
