package chess.engine.Pieces.PieceMoveType;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/*
This class defines the getLegalMoves function for pieces which move on a stepping scale.
These pieces are the King, the Pawn, and the Knight. POSSIBLE_MOVES will be a coordinate
array which holds all possible one square moves in valid directions for each piece.
This function does not cover edge cases for the King and Pawn moves, it is simply a starting point
to remove duplicate code.
*/
public abstract class steppingPiece extends Piece {
    public steppingPiece(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public List<Move> getLegalMoves(Board board, Coordinate[] POSSIBLE_MOVES) {
        Coordinate possibleCoordinate;//candidate move
        List<Move> legalMoves = new ArrayList<>();//list of all legal moves

        for (Coordinate possibleMove : POSSIBLE_MOVES) {
            possibleCoordinate = this.coordinate.add(possibleMove);//see if the current coordinate + the possibleMove is within the board
            if (possibleCoordinate.isValid()) {
                if (board.getTileAt(possibleCoordinate).getPiece() == null)//if tile not occupied
                {
                    legalMoves.add(new Move(board, this, possibleCoordinate));
                }
                else//if occupied
                {
                    if (board.getTileAt(possibleCoordinate).getPiece().getColour() != this.colour)//if the colour of the piece at the destination is not the current pieces colour
                        legalMoves.add(new Move(board, this, possibleCoordinate));
                }
            }
        }
        return legalMoves;
    }

}
