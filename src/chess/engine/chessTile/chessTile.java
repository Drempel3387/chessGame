package chess.engine.chessTile;
import chess.engine.Piece.Piece;
public class chessTile {
    //private data members
    protected final int coordinate;//location of the chessTile
    protected Piece piece;//piece at the tile

    public chessTile(final int coordinate, Piece piece)
    {
        this.coordinate = coordinate;
        this.piece = piece;
    }//construct a chessTile

    //getters
    public boolean isOccupied() {
        if (piece == null)
            return false;
        return true;

    }
    public Piece getPiece() {return piece;}
    public int getCoordinate() {return coordinate;}

    //setters
    public void setPiece(Piece piece) {this.piece = piece;}

}
