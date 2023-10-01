package chess.engine.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class enPassantMove extends Move{
    public enPassantMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove() {

    }

    @Override
    public void unMakeMove() {

    }
}
