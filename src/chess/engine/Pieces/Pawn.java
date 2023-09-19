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
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;

        for (Coordinate possibleMove : POSSIBLEMOVES) {
            possibleCoordinate = this.coordinate.add(possibleMove.multiply(this.colour.getDirection()));//move in the direction of the colour of the piece
            if (!possibleCoordinate.isValid())//if not a valid square, goto next iteration
                continue;

            if (possibleMove.areEqual(POSSIBLEMOVES[0]) && !board.getTileAt(possibleCoordinate).isOccupied())//single square move forwards to an empty square.
            {
                //more work to do here.
                legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                continue;
            }
            if (possibleMove.areEqual(POSSIBLEMOVES[1]) && !board.getTileAt(possibleCoordinate).isOccupied() && !this.hasMoved)//two square move forwards to an empty square
            {
                Coordinate before = this.coordinate.add(POSSIBLEMOVES[0].multiply(this.colour.getDirection()));//must check that the square before the piece destination is empty also
                if (!board.getTileAt(before).isOccupied()) {
                    legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                }
            }

        }
        return legalMoves;
    }

    @Override
    public void print() {
        System.out.printf("%2c", 'P');
    }
}
