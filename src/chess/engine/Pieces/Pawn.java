package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends steppingPiece {
    private boolean firstMove;
    private final Coordinate POSSIBLE_JUMP = new Coordinate(0,1);
    private final Coordinate[] POSSIBLE_CAPTURES = {
            new Coordinate(1, 1), new Coordinate(1, -1)
    };
    private final Coordinate[] STANDING_BESIDE = {
            new Coordinate(1, 0), new Coordinate(-1, 0)
    };

    public Pawn(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
        this.firstMove = true;
    }

//    @Override
//    public List<Move> getLegalMoves(final Board board)
//    {
//        List<Move> legalMoves = new ArrayList<>();
//        Coordinate possibleNormalMove = this.coordinate;
//        int direction = (this.colour == Colour.WHITE)? -1: 1;
//
//        for (int i = 0; i < (hasMoved()? 1:2); i++) //if the piece has moved, only check for a single push forwards
//        {
//            possibleNormalMove = possibleNormalMove.add(POSSIBLE_JUMP.multiply(direction));//attempt to make a move one square in the direction of the piece colour
//            if (possibleNormalMove.isValid()) {
//                if (!board.getTileAt(possibleNormalMove).isOccupied())//if the square is empty
//                {
//                    legalMoves.add(new Move(board, this, possibleNormalMove));
//                }
//            }
//        }//forward pawn moves
//
//        for (Coordinate capture: POSSIBLE_CAPTURES)
//        {
//            Coordinate possibleCaptureMove = this.coordinate.add(capture.multiply(direction));
//            if (possibleCaptureMove.isValid())
//            {
//                if (board.getTileAt(possibleCaptureMove).isOccupied())
//                {
//                    if (board.getTileAt(possibleCaptureMove).getPiece().getColour() != this.colour)
//                    {
//                        legalMoves.add(new Move(board, this, possibleCaptureMove));
//                    }
//                }
//            }
//        }//pawn capture moves
//
//
//        return legalMoves;
//    }

    public boolean hasMoved()
    {
        if (this.colour == Colour.WHITE && this.coordinate.getRank() != Board.SECOND)
            return true;
        return this.colour == Colour.BLACK && this.coordinate.getRank() != Board.SEVENTH;
    }
    public boolean getFirstMove()
    {
        return firstMove;
    }
    public void setFirstMove(boolean firstMove)
    {
        this.firstMove = firstMove;
    }
    @Override
    public String toString() {
        return "P";
    }
}
