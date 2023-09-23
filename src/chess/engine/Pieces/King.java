package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1),
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each diagonal, and straight direction
    public King(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public List<Move> getLegalMoves(Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;

        for (Coordinate possibleMove : POSSIBLE_MOVES) {
            possibleCoordinate = this.coordinate.add(possibleMove);//see if the current coordinate + a move is valid
            if (possibleCoordinate.isValid()) {
                if (board.getTileAt(possibleCoordinate).getPiece() == null)//if tile not occupied
                {
                    legalMoves.add(new Move(board, this, possibleCoordinate));
                }
                else//if occupied
                {
                    Piece destinationPiece = board.getTileAt(possibleCoordinate).getPiece();
                    if (destinationPiece.getColour() != this.colour)//if the colour of the piece at the destination is not the current pieces colour
                        legalMoves.add(new Move(board, this, possibleCoordinate));
                }
            }
        }
        return legalMoves;
    }


    @Override
    public String toString() {
        return "K";
    }

}
