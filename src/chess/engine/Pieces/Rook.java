package chess.engine.Pieces;

import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.slidingPiece;

public class Rook extends slidingPiece {
    public Rook(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each direction (right) (up) (left) (down)
    @Override
    public String toString() {
        return "R";
    }
}
