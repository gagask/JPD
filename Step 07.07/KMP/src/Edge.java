import java.awt.*;

public class Edge {
    private final Vertex from;
    private final Vertex to;
    private Color color;

    public Edge(Vertex from, Vertex to, Color color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawLine(from.getX() + 20, from.getY() + 20, to.getX() + 20, to.getY() + 20);
    }

    public boolean isIncidental(Vertex v1) {
        return from == v1 || to == v1;
    }
}
