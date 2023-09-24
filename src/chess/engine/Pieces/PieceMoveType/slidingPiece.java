package chess.engine.Pieces.PieceMoveType;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/*
This class defines the getLegalMoves function for pieces that move on a sliding scale.
these pieces are the Bishop, the Queen, and the Rook. POSSIBLE_MOVES will be a coordinate
array which holds all possible one square moves in valid directions for each piece.
*/
public abstract class slidingPiece extends Piece {
    public slidingPiece(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    public List<Move> getLegalMoves(final Board board, final Coordinate[] POSSIBLE_MOVES)
    {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;//candidate move

        for (Coordinate possibleMove: POSSIBLE_MOVES)
        {
            possibleCoordinate = this.coordinate.add(possibleMove);//move one square for the current direction
            while(possibleCoordinate.isValid())//while the new possibleCoordinate is within the chessboard
            {
                if (board.getTileAt(possibleCoordinate).getPiece() == null) //if no piece at the tile with possibleCoordinate, this is a legal move
                {
                    legalMoves.add(new Move(board, this, possibleCoordinate));
                }
                else {//if there is a piece at the tile with possibleCoordinate
                    if (this.colour != board.getTileAt(possibleCoordinate).getPiece().getColour())
                    {
                        legalMoves.add(new Move(board, this, possibleCoordinate));
                    }//if not own colour, add the move, and don't look further. (cannot move through other pieces)
                    break;
                }
                possibleCoordinate = possibleCoordinate.add(possibleMove);//move one square in the current direction
            }
        }
        return legalMoves;
    }
}
