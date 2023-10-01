package chess.engine.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class castleMove extends Move {
    private final int ONE_SQUARE = 1;
    public castleMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove() {
        board.getSquareAt(initialCoordinate).setPiece(null);//set previous pieces at previous squares of rook and king to null
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(null);//captured piece represents the rook

        board.getSquareAt(endingCoordinate).setPiece(movingPiece);//moving piece represents the king
        movingPiece.setCoordinate(endingCoordinate);

        //if the distance between where the king lands and the rooks starting square is greater than one, this is queen-side
        //castling, otherwise king-side castling
        if (endingCoordinate.distance(capturedPiece.getCoordinate()) > ONE_SQUARE)
        {
            Coordinate rookEndingCoordinate = endingCoordinate.add(new Coordinate(1, 0));
            board.getSquareAt(rookEndingCoordinate).setPiece(capturedPiece);
            capturedPiece.setCoordinate(rookEndingCoordinate);
        }
        else
        {
            Coordinate rookEndingCoordinate = endingCoordinate.add(new Coordinate(-1, 0));
            board.getSquareAt(rookEndingCoordinate).setPiece(capturedPiece);
            capturedPiece.setCoordinate(rookEndingCoordinate);
        }
    }

    @Override
    public void unMakeMove() {

    }
}
