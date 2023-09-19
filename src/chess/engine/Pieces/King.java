package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Board.Moves.Move;
import chess.engine.Board.Moves.NormalMove;
import chess.engine.Board.Moves.attackMove;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static final Coordinate[] POSSIBLEMOVES = {
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

        for (Coordinate possibleMove: POSSIBLEMOVES)
        {
            possibleCoordinate = this.coordinate.add(possibleMove);
            if (possibleCoordinate.isValid())//coordinate within the chess board
            {
                Piece piece = board.getTileAt(possibleCoordinate).getPiece();
                if (piece == null)//the tile is empty
                {
                    legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                }
                else
                {
                    if (this.colour != piece.getColour())
                    {
                        legalMoves.add(new attackMove(board, this, piece, possibleCoordinate));
                    }
                    break;
                }
            }
        }
        return legalMoves;
    }


    @Override
    public void print() {
        System.out.printf("%2c", 'K');
    }
}
