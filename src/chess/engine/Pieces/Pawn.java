package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Piece.Colour;
import chess.engine.Piece.Piece;
import chess.engine.Board.chessTile.Coordinate;

public class Pawn extends Piece {

    public Pawn(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public boolean isLegalmove(Board board, Coordinate start, Coordinate end) {
        return false;
    }
}
