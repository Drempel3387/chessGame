package chess.Board;
import chess.Coordinate;
import chess.Pieces.Piece;
/**
 * @author Devon R.
 *
 * Represents a singular square within a chessboard. This square maintains attributes for its coordinate(which is immutable),
 * and a piece that occupies it (if any). The square only performs simple gets and sets, and a check to see if it is
 * occupied.
 */
public class Square {
    //private data members
    /**
     * The coordinate on the chessboard which the square is assigned to
     */
    private final Coordinate coordinate;
    /**
     * The piece that occupies that squares if any does. Will be null if no piece has occupancy.
     */
    private Piece piece;

    /**
     * creates a Square
     * @param coordinate coordinate of the square
     * @param piece piece that starts off on the square(if any)
     */
    public Square(final Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }//construct a Square

    /**
     * will return whether a piece is currently occupying the square
     * @return boolean value indicating piece occupancy status
     */
    public boolean isOccupied() { return (piece != null);}//check if a square contains a piece
    //getters

    /**
     * will return a piece that is occupying the square
     * @return piece occupying the square
     */
    public Piece getPiece() {return piece;}

    /**
     * returns the coordinate that is assigned to the square
     * @return coordinate of the square
     */
    public Coordinate getCoordinate() {return coordinate;}

    /**
     * will update the piece that is now occupying the given square
     * @param piece that now occupies the square
     */
    //setters
    public void setPiece(Piece piece) {this.piece = piece;}

}
