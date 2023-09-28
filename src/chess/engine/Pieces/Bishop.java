package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.PieceMoveType.slidingPiece;

import java.util.List;


public class Bishop extends slidingPiece {
    public Bishop(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        return getPseudoLegalMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public boolean canAttackSquare(Board board ,Coordinate squarePosition) {
        return canAttackSquareOnDiagonal(board, squarePosition);
    }

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };//possible moves for each diagonal (right, up) (right, down) (left up) (left down)
    @Override
    public String toString() {
        return "B";
    }
}
