package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Board.chessTile.Coordinate;
import chess.engine.Piece.Colour;
import chess.engine.Piece.Piece;

public class Rook extends Piece {
    public Rook(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public boolean isLegalmove(Board board, Coordinate start, Coordinate end) {
        return false;
    }
}
