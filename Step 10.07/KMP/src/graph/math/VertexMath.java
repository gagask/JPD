package graph.math;

public class VertexMath
{
    private final int x;
    private final int y;
    private final int diameter;

    public VertexMath(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public int radius()
    {
        return diameter / 2;
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

    public boolean touch(VertexMath other)
    {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));

        return distance <= radius() * 2 + other.radius();
    }
}
