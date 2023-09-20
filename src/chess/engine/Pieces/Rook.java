package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Board.Moves.Move;
import chess.engine.Board.Moves.NormalMove;
import chess.engine.Board.Moves.attackMove;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    public static final Coordinate[] POSSIBLEMOVES = {
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each direction (right) (up) (left) (down)
    @Override
    public List<Move> getLegalMoves(final Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;//candidate move

        for (Coordinate possibleMove: POSSIBLEMOVES)//for each directional move
        {
            possibleCoordinate = this.coordinate.add(possibleMove);//move possibleCoordinate one square in the current direction
            while (possibleCoordinate.isValid())//if within the bounds of the chessboard
            {
                if (board.getTileAt(possibleCoordinate).getPiece() == null)//if the square is empty, this is a valid NormalMove
                {
                    legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                }
                else //if not empty, need to check the piece colour
                {
                    if (board.getTileAt(possibleCoordinate).getPiece().getColour() != this.colour)
                    {
                        legalMoves.add(new attackMove(board, this, board.getTileAt(possibleCoordinate).getPiece(), possibleCoordinate));
                    }//if opposite colour, this is a valid AttackMove. Stop looking any further, cannot move through enemy pieces
                    break;//same idea, if same colour, stop looking. Cannot move through own pieces.
                }
                possibleCoordinate = possibleCoordinate.add(possibleMove);
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return "R";
    }
}
