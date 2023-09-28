package chess.engine.Pieces;
import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Move> getLegalMoves(final Board board)
    {
        List<Move> legalMoves = new ArrayList<>();
        int direction = (this.colour == Colour.WHITE)? -1: 1;

        getJumpMoves(board, legalMoves, direction);
        getCaptureMoves(board, legalMoves, direction);
        getEnPassantMove(board, legalMoves, direction);

        return legalMoves;
    }//base logic for stepping pieces is not sufficient for pawn moves, must implement separately

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_CAPTURES);
    }

    private void getJumpMoves(Board board, List<Move> legalMoves, int direction)
    {
        Coordinate possibleNormalMove = this.coordinate;
        for (int i = 0; i < (firstMove? 2:1); i++) //if the piece has moved, only check for a single push forwards
        {
            possibleNormalMove = possibleNormalMove.add(POSSIBLE_JUMP.multiply(direction));//attempt to make a move one square in the direction of the piece colour
            if (possibleNormalMove.isValid()) {
                if (!board.getSquareAt(possibleNormalMove).isOccupied())//if the square is empty
                {
                    legalMoves.add(new Move(board, this, possibleNormalMove));
                }
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
                        legalMoves.add(new Move(board, this, possibleCaptureMove));
                    }
                }
            }
        }//pawn capture moves
    }

    private void getEnPassantMove(Board board, List<Move> legalMoves, int direction)
    {
        if (((this.coordinate.getRank() != Board.FOURTH) && (this.colour != Colour.WHITE)) ||
                ((this.coordinate.getRank() != Board.FIFTH) && (this.colour != Colour.BLACK)))
            return;//en passant not possible, white pawn must be on the fourth rank, black pawn must be on the fifth
        //for en passant to be valid
        for (Coordinate beside: STANDING_BESIDE)
        {
            Coordinate possibleEnPassantMove = this.coordinate.add(beside);
            Piece piece = board.getSquareAt(possibleEnPassantMove).getPiece();
            if (board.getSquareAt(possibleEnPassantMove).isOccupied() && piece.getColour() != this.colour)
            {
                if (piece instanceof Pawn)//if both pieces pawns
                {
                    if (((Pawn) piece).getFirstMove())
                    {
                        legalMoves.add(new Move(board, this, possibleEnPassantMove.add(POSSIBLE_JUMP.multiply(direction))));
                    }//if it is the first move
                }
            }
        }
    }//not "getEnPassantMoves", rules of chess strictly disallow "double" en passant

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
