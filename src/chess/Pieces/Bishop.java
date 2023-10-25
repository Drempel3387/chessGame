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
 *
 * Specialization of the slidingPiece class that represents a bishop. A bishop can move on open diagonals on a
 * sliding scale.
 */
public class Bishop extends slidingPiece {
    /**
     * creates a bishop
     * @param colour colour of the bishop
     * @param coordinate coordinate that the bishop lies on
     */
    public Bishop(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    @Override
    public boolean canAttackSquare(Board board,Coordinate squarePosition) {
        return canAttackSquareOnDiagonal(board, squarePosition);
    }

    public List<Move> getLegalMoves(final Game game)
    {
        return getLegalMoves(game, POSSIBLE_MOVES);
    }

    /**
     * Coordinate vectors for each diagonal that a bishop can move on. up-right, up-left, down-right, down-left
     */
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };//possible moves for each diagonal (right, up) (right, down) (left up) (left down)
    @Override
    public String toString() { return "B"; }
}
