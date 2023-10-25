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

/**
 * @author Devon R.
 *
 * This class defines the getLegalMoves function for pieces that move on a sliding scale.
 * these pieces are the Bishop, the Queen, and the Rook. POSSIBLE_MOVES will be a coordinate
 * array which holds all possible one square moves in valid directions for each piece. The class also
 * validates if a sliding piece can attack a square.
*/
public abstract class slidingPiece extends Piece {
    /**
     * initializes a slidingPiece
     * @param colour colour of the slidingPiece
     * @param coordinate starting coordinate of the slidingPiece
     */
    public slidingPiece(Colour colour, Coordinate coordinate) { super(colour, coordinate); }

    /**
     * returns the list of legalMoves that a slidingPiece has.
     * @param game game which the slidingPiece belongs to
     * @param POSSIBLE_MOVES possible move directions which a slidingPiece has
     * @return list<Move>
     */
    public List<Move> getLegalMoves(final Game game, final Coordinate[] POSSIBLE_MOVES) {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;//candidate move
        for (Coordinate possibleMove: POSSIBLE_MOVES) {
            possibleCoordinate = getCoordinate().add(possibleMove);//move one square for the current direction
            while(possibleCoordinate.isValid()) { //while the new possibleCoordinate is within the chessboard
                if (!game.getBoard().getSquareAt(possibleCoordinate).isOccupied()) { //if no piece at the tile with possibleCoordinate, this is a legal move
                    legalMoves.add(new normalMove(game.getBoard(), this, null,getCoordinate(), possibleCoordinate));
                }
                else {//if there is a piece at the tile with possibleCoordinate
                    if (getColour() != game.getBoard().getSquareAt(possibleCoordinate).getPiece().getColour())
                    {
                        legalMoves.add(new normalMove(game.getBoard(), this, game.getBoard().getSquareAt(possibleCoordinate).getPiece(), getCoordinate(), possibleCoordinate));
                    }//if not own colour, add the move, and don't look further. (cannot move through other pieces)
                    break;
                }
                possibleCoordinate = possibleCoordinate.add(possibleMove);//move one square in the current direction
            }
        }
        removeInvalidMoves(legalMoves, game.getBoard(), getColour());
        return legalMoves;
    }

    /**
     * Returns whether a piece is on the same diagonal as a square and is able to attack the square.
     * @param board board which a slidingPiece belongs to
     * @param squarePosition coordinate to be checked for attacking ability
     * @return boolean indicating attacking ability
     */
    protected boolean canAttackSquareOnDiagonal(final Board board, final Coordinate squarePosition) {
        if (!squarePosition.isValid())
            return false;

        int deltaX, deltaY;
        deltaX = getCoordinate().getFile() - squarePosition.getFile();
        deltaY = getCoordinate().getRank() - squarePosition.getRank();

        if (Math.abs(deltaX) != Math.abs(deltaY))//the square is not on the same diagonal as the piece
            return false;

        int stepX = (deltaX > 0? -1:1);//direction of movement in x (files) direction
        int stepY = (deltaY> 0?-1:1);//direction of movement in y (ranks) direction

        Coordinate stepInDirection = new Coordinate(stepX, stepY);
        return canAttackSquareInDirection(board, stepInDirection, squarePosition);
    }

    /**
     * returns whether a piece is on the same rank or file as a coordinate, and can attack the given coordinate.
     * @param board board which the slidingPiece belongs to
     * @param squarePosition coordinate of square to be checked for attacking ability
     * @return boolean indicating attacking ability
     */
    protected boolean canAttackSquareOnFileOrRank(final Board board, final Coordinate squarePosition) {
        if (!squarePosition.isValid())
            return false;

        int deltaX, deltaY;
        deltaX = getCoordinate().getFile() - squarePosition.getFile();
        deltaY = getCoordinate().getRank() - squarePosition.getRank();

        if (deltaX != 0 && deltaY !=0)
            return false;//must either be on the same file or rank to attack the given square

        int stepX, stepY;
        if (deltaX == 0) {
            stepX = 0;
            stepY = (deltaY> 0?-1:1);//direction of movement in y (ranks) direction
        }
        else {
            stepX = (deltaX > 0? -1:1);//direction of movement in x (files) direction
            stepY = 0;
        }
        Coordinate stepInDirection = new Coordinate(stepX, stepY);
        return canAttackSquareInDirection(board, stepInDirection, squarePosition);
    }

    /**
     * This function is called when it has been validated that a stepping piece is able to attack a given square.
     * This means that we only need to move in one direction, and check if there are any obstacles to attacking. (pieces
     * in the way). This functions validates that the square can actually be attacked.
     * @param board board which the steppingPiece belongs to
     * @param stepInDirection direction to check
     * @param squarePosition position of square to check for attacking ability
     * @return
     */
    protected boolean canAttackSquareInDirection(final Board board, final Coordinate stepInDirection, final Coordinate squarePosition) {
        Coordinate currentCoordinate = getCoordinate().add(stepInDirection);

        while(currentCoordinate.isValid()) {
            if (currentCoordinate.areEqual(squarePosition))
                return true;

            if (board.getSquareAt(currentCoordinate).getPiece() != null)
                return false;
            currentCoordinate = currentCoordinate.add(stepInDirection);//move one square in the current direction
        }
        return false;
    }
}



