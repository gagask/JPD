package graph;

import graph.math.VertexMath;

import java.awt.*;

public class Vertex extends VertexMath
{
    private Color color;
    private char[] name;

    public Vertex(int x, int y, int diameter, char name, Color color)
    {
        super(x, y, diameter);
        this.color = color;
        this.name = new char[]{name};
    }

    public void setColor(Color yellow) {
        this.color = yellow;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(getX(), getY(), radius() * 2, radius() * 2);
        g.setColor(Color.WHITE);
        g.drawChars(name, 0, 1, getX() + 17, getY() + 25);
    }

    public char getName() {
        return name[0];
    }

    public Color getColor() {
        return color;
    }
}
