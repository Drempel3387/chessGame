package chess.engine.Pieces;

import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.slidingPiece;

public class Queen extends slidingPiece {
    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1),
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };
    public Queen(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public String toString() {
        return "Q";
    }



}
