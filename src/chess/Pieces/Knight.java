package chess.Pieces;

import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Board.Board;
import chess.Colour;
import chess.Coordinate;
import chess.Pieces.PieceMoveType.steppingPiece;

import java.util.List;
/**
 * @author Devon R.
 *
 * Specialization of the steppingPiece class that represents a knight. A knight can move in an L shape and jump over other pieces.
 * This move is made up of every combination of all 2 and 1 coordinates. So (2, 1), (1, 2), (-2, 1), (2, -1),
 * (-2, -1), (-1, 2), (1, -2), (-1, -2). A knight moves on a stepping scale
 */
public class Knight extends steppingPiece {
    /**
     * Coordinate vectors for each possible move that a knight has.
     */
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1,2),new Coordinate(-1, 2) , new Coordinate(1,-2),
            new Coordinate(-1,-2), new Coordinate(2, 1), new Coordinate(2, -1),
            new Coordinate(-2, 1), new Coordinate(-2, -1)
    };
    /**
     * creates a knight with a colour and a coordinate
     * @param colour colour of the knight
     * @param coordinate starting coordinate of the knight
     */
    public Knight(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    @Override
    public List<Move> getLegalMoves(final Game game) { return getLegalMoves(game, POSSIBLE_MOVES); }
    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }
    @Override
    public String toString() { return "N"; }
}
