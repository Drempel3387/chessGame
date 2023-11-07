package chess.Moves;
import chess.Board.Board;
import chess.Game.moveList;
import chess.Colour;
import chess.Coordinate;
import chess.Pieces.Pawn;
import chess.Pieces.Piece;
/**
 * @author Devon R.
 *
 * Move class represents a singular move that is made in a chess game. A move is made on a board, it has a moving piece, captured piece, starting coordinate,
 * and ending coordinate.
 */
public abstract class Move {
    /**
     * The board which the move will be played on
     */
    protected final Board board;
    /**
     * The piece which is moving
     */
    protected final Piece movingPiece;
    /**
     * The piece which is being captured (if any)
     */
    protected final Piece capturedPiece;
    /**
     * Ending coordinate of the piece that is moving
     */
    protected final Coordinate endingCoordinate;
    /**
     * Starting coordinate of the piece that is moving
     */
    protected final Coordinate initialCoordinate;

    /**
     * Creates a move
     * @param board board on which the move is played
     * @param movingPiece Piece that is moving
     * @param capturedPiece Piece that is being captured
     * @param initialCoordinate Starting coordinate of the moving piece
     * @param endingCoordinate Ending coordinate of the moving piece
     */
    public Move(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        this.board = board;
        this.movingPiece = movingPiece;
        this.capturedPiece = capturedPiece;
        this.initialCoordinate = initialCoordinate;
        this.endingCoordinate = endingCoordinate;
    }

    /**
     * returns the piece that is moving
     * @return movingPiece
     */
    public Piece getMovingPiece() { return movingPiece; }

    /**
     * returns that initial coordinate of the moving piece
     * @return initialCoordinate
     */
    public Coordinate initialCoordinate() { return initialCoordinate; }

    /**
     * Returns the ending coordinate of the moving piece
     * @return endingCoordinate
     */
    public Coordinate getEndingCoordinate() { return endingCoordinate; }

    /**
     * will return a boolean that checks to see if a move is a capture. This is done by checking if the endingPiece
     * is not null
     * @return boolean indicating capture status
     */
    public boolean isCapture() {
        return (getCapturedPiece() != null);
    }

    /**
     * returns the piece that is at the ending coordinate. null if none
     * @return capturedPiece
     */
    public Piece getCapturedPiece() { return (capturedPiece); }

    /**
     * Used to actually make a move on a chessboard. Will be implemented in children classes and will cover the moving
     * of a piece from one square to another.
     */
    public abstract void makeMove();

    /**
     * Used to reverse the actions taken when a move is made. If a move is made, then unMakeMove is called on that same
     * move, the board will return to its original position before the last move was made. Will also be implemented in
     * the children classes.
     */
    public abstract void unMakeMove();

    /**
     * Will take a move and parse it into string format. This will take care of captures, checks, castle moves, etc.
     * @return string representing a move
     */
    @Override
    public String toString() {
        String move = "";
        if (!(movingPiece instanceof Pawn)) {
            move += movingPiece.toString();
            if (multipleAttacking())
                move += initialCoordinate.fileToString();
        }//otherwise use the piece
        if (movingPiece instanceof Pawn && isCapture())
            move+= initialCoordinate().fileToString();

        if (isCapture()) //if a capturing move, add an x
            move += "x";

        move +=  movingPiece.getCoordinate().fileToString();
        move += Board.RANKS- endingCoordinate.getRank();
        return move;
    }

    /**
     * This function will check to see if two pieces are attacking the same square. This is helpful for move to string
     * parsing because if two pieces of the same colour and type are able to attack the same square, the string
     * must be able to differentiate which of the pieces is capturing the square.
     * @return boolean indicating if multiple pieces are attacking the same square
     */
    private boolean multipleAttacking() {
        for (Piece piece : movingPiece.getColour() == Colour.WHITE ? board.getWhitePieces() : board.getBlackPieces()) {
            if (!piece.getIsAlive())
                continue;
            if (piece != movingPiece && piece.getClass().equals(movingPiece.getClass())) // Check if the pieces are of the same type (e.g., both rooks)
                if (piece.canAttackSquare(board, endingCoordinate))
                    return true;
        }
        return false;
    }

}
