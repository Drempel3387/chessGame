package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved;
    private final Coordinate POSSIBLE_JUMP = new Coordinate(0,1);

    private final Coordinate[] POSSIBLE_CAPTURES = {
            new Coordinate(1, 1), new Coordinate(1, -1)
    };

    public Pawn(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleNormalMove = this.coordinate;
        int direction = (this.colour == Colour.WHITE)? -1: 1;

        for (int i = 0; i < (hasMoved? 1:2); i++) //if the piece has moved, only check for a single push forwards
        {
            possibleNormalMove = possibleNormalMove.add(POSSIBLE_JUMP.multiply(direction));//attempt to make a move one square in the direction of the piece colour
            if (possibleNormalMove.isValid()) {
                if (!board.getTileAt(possibleNormalMove).isOccupied())//if the square is empty
                {
                    legalMoves.add(new Move(board, this, possibleNormalMove));
                }
            }
        }//forward pawn moves

        for (Coordinate capture: POSSIBLE_CAPTURES)
        {
            Coordinate possibleCaptureMove = this.coordinate.add(capture.multiply(direction));
            if (possibleCaptureMove.isValid())
            {
                if (board.getTileAt(possibleCaptureMove).isOccupied())
                {
                    if (board.getTileAt(possibleCaptureMove).getPiece().getColour() != this.colour)
                    {
                        legalMoves.add(new Move(board, this, possibleCaptureMove));
                    }
                }
            }
        }//pawn capture moves

        return legalMoves;
    }

    @Override
    public String toString() {
        return "P";
    }
}
