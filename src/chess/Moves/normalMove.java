package chess.Moves;
import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;
/**
 * @author Devon R.
 *
 * Specialization of the Move Class. A normal move represents any capture, or non capture move that is not castling,
 * enPassant, or promotion.
 */
public class normalMove extends Move {
    /**
     * Creates a normalMove
     * @param board board on which the move is played
     * @param movingPiece Piece that is moving
     * @param capturedPiece Piece that is being captured
     * @param initialCoordinate Starting coordinate of the moving piece
     * @param endingCoordinate Ending coordinate of the moving piece
     */
    public normalMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }
    /**
     * Used to make a normalMove on a chessboard
     */
    @Override
    public void makeMove() {
        if (isCapture())
            board.getSquareAt(getEndingCoordinate()).getPiece().setIsAlive(false);//set the captured piece to dead
        board.getSquareAt(initialCoordinate()).setPiece(null);//square is now empty, null to signify
        board.getSquareAt(getEndingCoordinate()).setPiece(getMovingPiece());//move the current to the ending position
        getMovingPiece().setCoordinate(getEndingCoordinate());//update the moving pieces coordinate
    }
    /**
     * Used to unMake a normalMove on a chessboard
     */
    @Override
    public void unMakeMove() {
        if (isCapture())
            getCapturedPiece().setIsAlive(true);//set captured piece back to alive
        board.getSquareAt(getEndingCoordinate()).setPiece(getCapturedPiece());//place the captured piece back on the board (if there is one, will be null otherwise)
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());//place the moving piece back to its original square
        getMovingPiece().setCoordinate(initialCoordinate());//reset the moving pieces coordinate
    }
}
