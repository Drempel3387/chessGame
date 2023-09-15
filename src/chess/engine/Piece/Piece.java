package chess.engine.Piece;
public abstract class Piece {
    private boolean isAlive;//has the piece been captured
    private final Colour colour;//Colour of the piece, BLACK or WHITE
    private int position;
    public Piece(final Colour colour, int position)
    {
        this.colour = colour;
        this.position = position;
        this.isAlive = true;
    }
    //getters
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}
    public int getPosition() {return position;}

    //setters
    void setIsAlive(boolean isAlive) {this.isAlive = isAlive;}
    void setPosition(int position) {this.position = position;}

    public abstract boolean isLegalmove();
}
