package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.PieceMoveType.slidingPiece;

import java.util.List;

public class Rook extends slidingPiece {
    public Rook(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        return getPseudoLegalMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return (canAttackSquareOnFileOrRank(board, squarePosition));
    }

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each direction (right) (up) (left) (down)

    @Override
    public String toString() {
        return "R";
    }
}
