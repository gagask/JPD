package graph.util;

public class EdgeOvalRepresentation
{
    private final OvalRepresentation ovalRepr;
    private final Point textCoordinates;


    public EdgeOvalRepresentation(OvalRepresentation ovalRepr, Point textCoordinates) {
        this.ovalRepr = ovalRepr;
        this.textCoordinates = textCoordinates;
    }

    public OvalRepresentation getOvalRepr() {
        return ovalRepr;
    }

    public Point getTextCoordinates() {
        return textCoordinates;
    }
}
