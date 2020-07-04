import javax.swing.*;
import java.awt.*;

public class Vertex{
    private int x;
    private int y;
    private int diameter;
    private Color color;

    public Vertex(int x, int y, int diameter, Color color)
    {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public boolean contains(int x, int y)
    {
        return Math.pow(x - (20 + this.x), 2) + Math.pow(y - (20 + this.y), 2) <= Math.pow(diameter, 2) / 4;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


}
