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
 * Specialization of the slidingPiece class that represents a chess Queen. A queen can move on open ranks, file, and diagonals.
 * So, a queen has the moving capabilities of a bishop, and also a rook. A queen moves on a sliding scale
 */
public class Queen extends slidingPiece {
    /**
     * Coordinate vectors for each rank, file, and diagonal that a queen can move on. Combination of bishop and
     * rook move coordinates
     */
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1),
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };

    /**
     * creates a queen with a specific colour and starting coordinate
     * @param colour colour of the queen
     * @param coordinate coordinate of the queen
     */
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
