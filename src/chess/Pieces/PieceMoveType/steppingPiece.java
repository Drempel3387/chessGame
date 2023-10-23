package chess.Pieces.PieceMoveType;

import chess.Board.Board;
import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Pieces.Piece;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.normalMove;

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
    public steppingPiece(Colour colour, Coordinate coordinate) { super(colour, coordinate); }

    protected List<Move> getLegalMoves(final Game game, Coordinate[] POSSIBLE_MOVES) {
        Coordinate possibleCoordinate;//candidate move
        List<Move> legalMoves = new ArrayList<>();//list of all legal moves

        for (Coordinate possibleMove : POSSIBLE_MOVES) {
            possibleCoordinate = getCoordinate().add(possibleMove);//see if the current coordinate + the possibleMove is within the board
            if (possibleCoordinate.isValid()) {
                if (!game.getBoard().getSquareAt(possibleCoordinate).isOccupied())//if tile not occupied
                    legalMoves.add(new normalMove(game.getBoard(), this, null, getCoordinate(), possibleCoordinate));
                else//if occupied
                    if (game.getBoard().getSquareAt(possibleCoordinate).getPiece().getColour() != getColour())//if the colour of the piece at the destination is not the current pieces colour
                        legalMoves.add(new normalMove(game.getBoard(), this, game.getBoard().getSquareAt(possibleCoordinate).getPiece(), getCoordinate(), possibleCoordinate));
            }
        }
        removeInvalidMoves(legalMoves, game.getBoard(), getColour());
        return legalMoves;
    }
    public boolean steppingPieceCanAttackSquare(Board board, Coordinate squarePosition, Coordinate[] POSSIBLE_MOVES)
    {
        if (!squarePosition.isValid())
            return false;

        //these values are used to see if it is sensible to check for attacking ability, if distance
        //of the pieces is greater than the reach of the piece, it cannot attack the target square

        double distanceBetween = squarePosition.distance(this.getCoordinate());
        //distance between the current piece and the square to check
        double attackReach = getCoordinate().distance(getCoordinate().add(POSSIBLE_MOVES[0]));
        //the reach of a piece "How far they can attack"

        if (distanceBetween > attackReach)
            return false;

        for (Coordinate possibleMove: POSSIBLE_MOVES) {
            if (getCoordinate().add(possibleMove).areEqual(squarePosition))
                return true;
            //if the current position of the square plus a valid move lands on the target square,
            //the piece can attack the square
        }

        return false;
    }
}
