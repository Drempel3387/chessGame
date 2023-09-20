package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Board.Moves.Move;
import chess.engine.Board.Moves.NormalMove;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved;
    public static final Coordinate[] POSSIBLEMOVES = {
            new Coordinate(0,1), new Coordinate(0,2),
    };
    public Pawn(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board)
    {
        return null;
    }

    @Override
    public String toString() {
        return "P";
    }
}
