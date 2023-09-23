package chess.engine.Pieces;

import chess.engine.Moves.Move;
import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public List<Move> getLegalMoves(Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        Rook rook = new Rook(this.colour, this.coordinate);
        Bishop bishop = new Bishop(this.colour, this.coordinate);

        legalMoves.addAll(rook.getLegalMoves(board));
        legalMoves.addAll(bishop.getLegalMoves(board));

        return legalMoves;
    }//a queen moves the same as a rook and a bishop, so just reuse the code for this. Simulate a rook and a bishop in the same position as the queen

    @Override
    public String toString() {
        return "Q";
    }



}
