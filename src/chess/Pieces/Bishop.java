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
public class Bishop extends slidingPiece {
    public Bishop(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    @Override
    public boolean canAttackSquare(Board board ,Coordinate squarePosition) {
        return canAttackSquareOnDiagonal(board, squarePosition);
    }

    public List<Move> getLegalMoves(final Game game)
    {
        return getLegalMoves(game, POSSIBLE_MOVES);
    }
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };//possible moves for each diagonal (right, up) (right, down) (left up) (left down)
    @Override
    public String toString() { return "B"; }
}
