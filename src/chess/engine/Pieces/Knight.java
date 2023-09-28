package chess.engine.Pieces;

import chess.engine.Moves.Move;
import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
public class Knight extends steppingPiece {

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1,2),new Coordinate(-1, 2) , new Coordinate(1,-2),
            new Coordinate(-1,-2), new Coordinate(2, 1), new Coordinate(2, -1),
            new Coordinate(-2, 1), new Coordinate(-2, -1)
    };//possible knight jump coordinates
    public Knight(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        return getPseudoLegalMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }

    @Override
    public String toString() {
        return "N";
    }
}
