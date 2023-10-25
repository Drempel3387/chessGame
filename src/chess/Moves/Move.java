package chess.Moves;
import chess.Board.Board;
import chess.Game.moveList;
import chess.Colour;
import chess.Coordinate;
import chess.Pieces.Pawn;
import chess.Pieces.Piece;
/**
 * @author Devon R.
 */
public abstract class Move {
    protected final Board board;//board which the move will be made on
    protected final Piece movingPiece;//the piece which will be moving
    protected final Piece capturedPiece;//the piece which is being captured, if there is one
    protected final Coordinate endingCoordinate;//ending coordinate of the moving piece
    protected final Coordinate initialCoordinate;//starting coordinate of the moving piece

    public Move(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        this.board = board;
        this.movingPiece = movingPiece;
        this.capturedPiece = capturedPiece;
        this.initialCoordinate = initialCoordinate;
        this.endingCoordinate = endingCoordinate;
    }


    public Piece getMovingPiece() { return movingPiece; }
    public Coordinate initialCoordinate() { return initialCoordinate; }//initial coordinate of the piece which is making a move
    public Coordinate getEndingCoordinate() { return endingCoordinate; }//ending coordinate of the piece
    public boolean isCapture() {
        return (getEndingPiece() != null && (getEndingPiece().getColour() != movingPiece.getColour()));
    }//is this a capture move?

    public Piece getEndingPiece() { return (capturedPiece); }//get the piece at the ending coordinate

    public Colour getMovingPieceColour() { return movingPiece.getColour(); }
    protected void addToMoveList(moveList MoveList) { MoveList.addMoveToList(this); }

    public abstract void makeMove();
    public abstract void unMakeMove();

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
    //will turn a move into it's string representation.

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
