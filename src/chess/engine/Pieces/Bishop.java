package chess.engine.Pieces;

import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.slidingPiece;


public class Bishop extends slidingPiece {
    public Bishop(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };//possible moves for each diagonal (right, up) (right, down) (left up) (left down)
    @Override
    public String toString() {
        return "B";
    }
}
