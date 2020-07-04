import java.awt.*;

public class Edge {
    private int x_to;
    private int y_to;
    private int x_from;
    private int y_from;
    private Color color;

    public Edge(int x_to, int y_to, int x_from, int y_from, Color color)
    {
        this.x_to = x_to;
        this.x_from = x_from;
        this.y_to = y_to;
        this.y_from = y_from;
        this.color = color;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawLine(x_from, y_from, x_to, y_to);
    }
}
