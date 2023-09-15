package chess.engine.Piece;
import chess.engine.chessTile.Coordinate;

public abstract class Piece {
    private boolean isAlive;//has the piece been captured
    private final Colour colour;//Colour of the piece, BLACK or WHITE
    private Coordinate coordinate;
    public Piece(final Colour colour, Coordinate coordinate)
    {
        this.colour = colour;
        this.coordinate = coordinate;
        this.isAlive = true;
    }
    //getters
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}
    public Coordinate getCoordinate() {return coordinate;}

    //setters
    void setIsAlive(boolean isAlive) {this.isAlive = isAlive;}
    void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;}

    public abstract boolean isLegalmove();
}
