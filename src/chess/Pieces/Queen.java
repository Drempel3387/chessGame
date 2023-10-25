package chess.Pieces;

import chess.Board.Board;
import chess.Colour;
import chess.Coordinate;
import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Pieces.PieceMoveType.slidingPiece;

import java.util.List;
/**
 * @author Devon R.
 */
public class Queen extends slidingPiece {
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1),
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };
    public Queen(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    public List<Move> getLegalMoves(final Game game)
    {
        return getLegalMoves(game, POSSIBLE_MOVES);
    }
    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return (canAttackSquareOnFileOrRank(board, squarePosition) || canAttackSquareOnDiagonal(board, squarePosition));
    }

    @Override
    public String toString() { return "Q"; }
}
