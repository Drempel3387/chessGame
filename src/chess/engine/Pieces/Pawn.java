package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.enPassantMove;
import chess.engine.Moves.normalMove;
import chess.engine.Moves.promotionMove;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends steppingPiece {
    private final Coordinate POSSIBLE_JUMP = new Coordinate(0,1);
    private final Coordinate[] POSSIBLE_CAPTURES = {
            new Coordinate(1, 1), new Coordinate(-1, 1)
    };
    private final Coordinate[] STANDING_BESIDE = {
            new Coordinate(1, 0), new Coordinate(-1, 0)
    };

    public Pawn(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    public List<Move> getLegalMoves(final Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        int direction = (this.colour == Colour.WHITE)? -1: 1;

        getJumpMoves(board, legalMoves, direction);
        getCaptureMoves(board, legalMoves, direction);
        getEnPassantMove(board, legalMoves, direction);
        removeInvalidMoves(legalMoves, board, this.colour);

        return legalMoves;
    }//base logic for stepping pieces is not sufficient for pawn moves, must implement separately

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_CAPTURES);
    }

    private void getJumpMoves(Board board, List<Move> legalMoves, int direction)
    {
        Coordinate possibleMove = this.coordinate;
        for (int i = 0; i < (getFirstMove()? 2:1); i++) //if the piece has moved, only check for a single push forwards
        {
            possibleMove = possibleMove.add(POSSIBLE_JUMP.multiply(direction));//attempt to make a move one square in the direction of the piece colour
            if (possibleMove.isValid()) {
                if (!board.getSquareAt(possibleMove).isOccupied())//if the square is empty
                {
                    if (isPromotion(possibleMove))
                        legalMoves.add(new promotionMove(board, this, null, this.coordinate, possibleMove));
                    else
                        legalMoves.add(new normalMove(board, this, null, this.coordinate, possibleMove));
                }
                else
                    break;
            }
        }
    }
    private void getCaptureMoves(Board board, List<Move> legalMoves, int direction)
    {
        for (Coordinate capture: POSSIBLE_CAPTURES)
        {
            Coordinate possibleCaptureMove = this.coordinate.add(capture.multiply(direction));
            if (possibleCaptureMove.isValid())
            {
                if (board.getSquareAt(possibleCaptureMove).isOccupied())
                {
                    if (board.getSquareAt(possibleCaptureMove).getPiece().getColour() != this.colour)
                    {
                        if (isPromotion(possibleCaptureMove))//if the capture is a promotion
                            legalMoves.add(new promotionMove(board, this, board.getSquareAt(possibleCaptureMove).getPiece(), this.coordinate, possibleCaptureMove));
                        else
                            legalMoves.add(new normalMove(board, this, board.getSquareAt(possibleCaptureMove).getPiece(), this.coordinate, possibleCaptureMove));
                    }
                }
            }
        }//pawn capture moves
    }

    private void getEnPassantMove(Board board, List<Move> legalMoves, int direction)
    {
        if (((this.coordinate.getRank() == Board.FOURTH) && (this.colour != Colour.BLACK)) ||
                ((this.coordinate.getRank() == Board.FIFTH) && (this.colour != Colour.WHITE)))
            return;//en passant not possible, white pawn must be on the fourth rank, black pawn must be on the fifth
        //for en passant to be valid

        for (Coordinate beside: STANDING_BESIDE)
        {
            Coordinate possibleEnPassantMove = this.coordinate.add(beside);
            if (!possibleEnPassantMove.isValid())
                continue;
            Piece piece = board.getSquareAt(possibleEnPassantMove).getPiece();
            if (piece != null && piece.getColour() != this.colour)
            {
                if (piece instanceof Pawn)//if both pieces pawns
                {

                    if (!piece.getHasMoved())
                    {
                        legalMoves.add(new enPassantMove(board, this, piece , this.coordinate, possibleEnPassantMove.add(POSSIBLE_JUMP.multiply(direction))));
                    }//if it is the first move
                }
            }
        }
    }//not "getEnPassantMoves", rules of chess strictly disallow "double" en passant
    public boolean isPromotion(Coordinate position)
    {
        if (getColour() == Colour.WHITE && position.getRank() != Board.EIGHTH)
            return false;
        if (getColour() == Colour.BLACK && position.getRank() != Board.FIRST)
            return false;
        return true;
    }//check to see if a piece is a pawn, and either on the first or eighth rank based on colour

    public boolean getFirstMove()
    {
        if (this.colour == Colour.WHITE && this.coordinate.getRank() != Board.SECOND)
            return false;
        if (this.colour == Colour.BLACK && this.coordinate.getRank() != Board.SEVENTH)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "P";
    }
}
