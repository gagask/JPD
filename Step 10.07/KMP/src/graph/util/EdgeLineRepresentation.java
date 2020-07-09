package graph.util;

public class EdgeLineRepresentation
{
    private final Point startPoint;
    private final Point endPoint;

    private final Point textCoordinates;


    public EdgeLineRepresentation(Point startLine, Point endLine, Point textCoordinates) {
        this.startPoint = startLine;
        this.endPoint = endLine;
        this.textCoordinates = textCoordinates;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getTextCoordinates() {
        return textCoordinates;
    }
}
