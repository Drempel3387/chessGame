package chess.engine.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class normalMove extends Move{
    public normalMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove()
    {
        if (isCapture())
            board.getSquareAt(getEndingCoordinate()).getPiece().setIsAlive(false);
        board.getSquareAt(initialCoordinate()).setPiece(null);//square is now empty, null to signify
        board.getSquareAt(getEndingCoordinate()).setPiece(getMovingPiece());//move the current to the ending position
        getMovingPiece().setCoordinate(getEndingCoordinate());//update the moving pieces coordinate
    }


    @Override
    public void unMakeMove()
    {
        if (isCapture())
        {
            board.getSquareAt(getEndingCoordinate()).setPiece(getEndingPiece());//place the captured piece back on the board
            getEndingPiece().setIsAlive(true);//set status back to alive
        }
        else {
            board.getSquareAt(getEndingCoordinate()).setPiece(null);
        }
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());
        getMovingPiece().setCoordinate(initialCoordinate());

    }
}
