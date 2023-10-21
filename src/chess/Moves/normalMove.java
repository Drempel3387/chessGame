package chess.Moves;

import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;

public class normalMove extends Move{
    public normalMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }
    @Override
    public void makeMove()
    {
        if (isCapture())
            board.getSquareAt(getEndingCoordinate()).getPiece().setIsAlive(false);//set the captured piece to dead
        board.getSquareAt(initialCoordinate()).setPiece(null);//square is now empty, null to signify
        board.getSquareAt(getEndingCoordinate()).setPiece(getMovingPiece());//move the current to the ending position
        getMovingPiece().setCoordinate(getEndingCoordinate());//update the moving pieces coordinate
    }

    @Override
    public void unMakeMove()
    {
        if (isCapture())
            getEndingPiece().setIsAlive(true);//set captured piece back to alive
        board.getSquareAt(getEndingCoordinate()).setPiece(getEndingPiece());//place the captured piece back on the board (if there is one, will be null otherwise)
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());//place the moving piece back to its original square
        getMovingPiece().setCoordinate(initialCoordinate());//reset the moving pieces coordinate
    }
}
