package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.PieceMoveType.slidingPiece;

import java.util.List;

public class Queen extends slidingPiece {
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1),
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };
    public Queen(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        return getPseudoLegalMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return (canAttackSquareOnFileOrRank(board, squarePosition) || canAttackSquareOnDiagonal(board, squarePosition));
    }

    @Override
    public String toString() {
        return "Q";
    }



}
