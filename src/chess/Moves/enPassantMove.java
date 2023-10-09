package chess.Moves;

import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;

public class enPassantMove extends Move{
    public enPassantMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove() {
        //empty the squares of the pawn being captured and the capturing pawn
        capturedPiece.setIsAlive(false);
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(null);
        board.getSquareAt(initialCoordinate).setPiece(null);

        //move the pawn to correct en passant square
        board.getSquareAt(endingCoordinate).setPiece(movingPiece);
        movingPiece.setCoordinate(endingCoordinate);
    }

    @Override
    public void unMakeMove() {
        //place capturing pawn back on it's starting square
        board.getSquareAt(movingPiece.getCoordinate()).setPiece(null);
        board.getSquareAt(initialCoordinate).setPiece(movingPiece);
        movingPiece.setCoordinate(initialCoordinate);

        //place captured pawn back on the board
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(capturedPiece);
        capturedPiece.setIsAlive(true);
    }
}
