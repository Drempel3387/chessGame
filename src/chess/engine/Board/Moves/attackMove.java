package chess.engine.Board.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public class attackMove extends Move{
    private Piece attacked;
    public attackMove(Board board, Piece piece, Piece attacked, Coordinate endingCoordinate) {
        super(board, piece, endingCoordinate);
        this.attacked = attacked;
    }
}
