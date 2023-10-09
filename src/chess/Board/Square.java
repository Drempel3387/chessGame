package chess.Board;
import chess.Coordinate;
import chess.Pieces.Piece;

public class Square {
    //private data members
    protected final Coordinate coordinate;//location of the Square
    protected Piece piece;//piece at the square

    public Square(final Coordinate coordinate, Piece piece)
    {
        this.coordinate = coordinate;
        this.piece = piece;
    }//construct a Square

    public boolean isOccupied() {
        return (piece != null);
    }//check if a square contains a piece

    //getters
    public Piece getPiece() {return piece;}
    public Coordinate getCoordinate() {return coordinate;}

    //setters
    public void setPiece(Piece piece) {this.piece = piece;}

}
