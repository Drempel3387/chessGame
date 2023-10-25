package chess.Pieces;

import chess.Board.Board;
import chess.Colour;
import chess.Coordinate;
import chess.Game.Game;
import chess.Moves.Move;
import chess.Pieces.PieceMoveType.slidingPiece;

import java.util.List;
/**
 * @author Devon R.
 *
 * Specialization of the slidingPiece class that represents a rook. A rook can move on open files and ranks.
 * A rook moves on a sliding scale
 */
public class Rook extends slidingPiece {
    /**
     * Creates a rook with a specific colour and starting coordinate
     * @param colour colour of the rook
     * @param coordinate starting coordinate of the rook
     */
    public Rook(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return (canAttackSquareOnFileOrRank(board, squarePosition));
    }
    public List<Move> getLegalMoves(final Game game)
    {
        return getLegalMoves(game, POSSIBLE_MOVES);
    }
    /**
     * Coordinate vectors for each file and rank direction that a rook can move on. up, down, left, and right
     */
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each direction (right) (up) (left) (down)
    @Override
    public String toString() { return "R"; }
}
