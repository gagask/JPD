package graph.util;

public class OvalRepresentation
{
    private final int x;
    private final int y;
    private final int height;
    private final int width;


    public OvalRepresentation(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
