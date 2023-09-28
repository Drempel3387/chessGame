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
    protected List<Move> getPseudoLegalMoves(final Board board, final Coordinate[] POSSIBLE_MOVES)
    {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;//candidate move

        for (Coordinate possibleMove: POSSIBLE_MOVES)
        {
            possibleCoordinate = this.coordinate.add(possibleMove);//move one square for the current direction
            while(possibleCoordinate.isValid())//while the new possibleCoordinate is within the chessboard
            {
                if (!board.getSquareAt(possibleCoordinate).isOccupied()) //if no piece at the tile with possibleCoordinate, this is a legal move
                {
                    legalMoves.add(new Move(board, this, possibleCoordinate));
                }
                else {//if there is a piece at the tile with possibleCoordinate
                    if (this.colour != board.getSquareAt(possibleCoordinate).getPiece().getColour())
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
    protected boolean canAttackSquareOnDiagonal(Board board, Coordinate squarePosition)
    {
        if (!squarePosition.isValid())
            return false;

        int deltaX, deltaY;
        deltaX = this.coordinate.getFile() - squarePosition.getFile();
        deltaY = this.coordinate.getRank() - squarePosition.getRank();

        if (Math.abs(deltaX) != Math.abs(deltaY))//the square is not on the same diagonal as the piece
            return false;

        int stepX = (deltaX > 0? -1:1);//direction of movement in x (files) direction
        int stepY = (deltaY> 0?-1:1);//direction of movement in y (ranks) direction

        Coordinate stepInDirection = new Coordinate(stepX, stepY);
        return canAttackSquareInDirection(board, stepInDirection, squarePosition);
    }
    protected boolean canAttackSquareOnFileOrRank(Board board, Coordinate squarePosition)
    {
        if (!squarePosition.isValid())
            return false;

        int deltaX, deltaY;
        deltaX = this.coordinate.getFile() - squarePosition.getFile();
        deltaY = this.coordinate.getRank() - squarePosition.getRank();

        if (deltaX != 0 && deltaY !=0)
            return false;//must either be on the same file or rank to attack the given square

        int stepX, stepY;
        if (deltaX == 0)
        {
            stepX = 0;
            stepY = (deltaY> 0?-1:1);//direction of movement in y (ranks) direction
        }
        else
        {
            stepX = (deltaX > 0? -1:1);//direction of movement in x (files) direction
            stepY = 0;
        }
        Coordinate stepInDirection = new Coordinate(stepX, stepY);
        return canAttackSquareInDirection(board, stepInDirection, squarePosition);
    }
    protected boolean canAttackSquareInDirection(Board board, Coordinate stepInDirection, Coordinate squarePosition)
    {
        Coordinate currentCoordinate = this.coordinate.add(stepInDirection);

        while(currentCoordinate.isValid())
        {
            if (currentCoordinate.areEqual(squarePosition))
            {
                return true;
            }
            if (board.getSquareAt(currentCoordinate).getPiece() != null)
                return false;
            currentCoordinate = currentCoordinate.add(stepInDirection);//move one square in the current direction
        }
        return false;
    }
}



