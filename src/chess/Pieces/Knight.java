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
 */
public class Knight extends steppingPiece {

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1,2),new Coordinate(-1, 2) , new Coordinate(1,-2),
            new Coordinate(-1,-2), new Coordinate(2, 1), new Coordinate(2, -1),
            new Coordinate(-2, 1), new Coordinate(-2, -1)
    };//possible knight jump coordinates
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
