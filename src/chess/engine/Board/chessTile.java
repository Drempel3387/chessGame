package chess.engine.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class chessTile {
    //private data members
    protected final Coordinate coordinate;//location of the chessTile
    protected Piece piece;//piece at the tile

    public chessTile(final Coordinate coordinate, Piece piece)
    {
        this.coordinate = coordinate;
        this.piece = piece;
    }//construct a chessTile

    //getters
    public boolean isOccupied() {
        return (piece != null);
    }
    public Piece getPiece() {return piece;}
    public Coordinate getCoordinate() {return coordinate;}

    //setters
    public void setPiece(Piece piece) {this.piece = piece;}

}
