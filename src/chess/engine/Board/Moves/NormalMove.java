package chess.engine.Board.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class NormalMove extends Move{
    public NormalMove(Board board, Piece piece, Coordinate endingCoordinate) {
        super(board, piece, endingCoordinate);
    }
}
