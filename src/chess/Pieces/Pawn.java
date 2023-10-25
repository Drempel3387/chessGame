package chess.Pieces;
import chess.Board.Board;
import chess.Game.Game;
import chess.Moves.Move;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.enPassantMove;
import chess.Moves.normalMove;
import chess.Moves.promotionMove;
import chess.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Devon
 *
 * Implementation of
 */
public class Pawn extends steppingPiece {
    private final Coordinate POSSIBLE_JUMP = new Coordinate(0,1);
    private final Coordinate[] POSSIBLE_CAPTURES = {
            new Coordinate(1, 1), new Coordinate(-1, 1)
    };
    private final Coordinate[] STANDING_BESIDE = {
            new Coordinate(1, 0), new Coordinate(-1, 0)
    };
    public Pawn(Colour colour, Coordinate coordinate) { super(colour, coordinate); }
    public List<Move> getLegalMoves(final Game game) {
        List<Move> legalMoves = new ArrayList<>();
        int direction = (getColour() == Colour.WHITE)? -1: 1;

        getEnPassantMove(game, legalMoves, direction);
        getJumpMoves(game, legalMoves, direction);
        getCaptureMoves(game, legalMoves, direction);
        removeInvalidMoves(legalMoves, game.getBoard(), getColour());

        return legalMoves;
    }//base logic for stepping pieces is not sufficient for pawn moves, must implement separately
    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_CAPTURES);
    }
    private void getJumpMoves(final Game game, final List<Move> legalMoves, final int direction) {
        Coordinate possibleMove = getCoordinate();
        for (int i = 0; i < (getFirstMove()? 2:1); i++) { //if the piece has moved, only check for a single push forwards
            possibleMove = possibleMove.add(POSSIBLE_JUMP.multiply(direction));//attempt to make a move one square in the direction of the piece colour
            if (possibleMove.isValid()) {
                if (!game.getBoard().getSquareAt(possibleMove).isOccupied()) { //if the square is empty
                    if (isPromotion(possibleMove)) {
                        Queen queen = new Queen(getColour(), possibleMove);
                        legalMoves.add(new promotionMove(game.getBoard(), this, null, queen, getCoordinate(), possibleMove));
                    }
                    else
                        legalMoves.add(new normalMove(game.getBoard(), this, null, getCoordinate(), possibleMove));
                }
                else
                    break;
            }
        }
    }
    private void getCaptureMoves(final Game game, final List<Move> legalMoves, final int direction) {
        for (Coordinate capture: POSSIBLE_CAPTURES) {
            Coordinate possibleCaptureMove = getCoordinate().add(capture.multiply(direction));
            if (possibleCaptureMove.isValid()) {
                if (game.getBoard().getSquareAt(possibleCaptureMove).isOccupied()) {
                    if (game.getBoard().getSquareAt(possibleCaptureMove).getPiece().getColour() != getColour()) {
                        if (isPromotion(possibleCaptureMove)) { //if the capture is a promotion
                            Queen queen = new Queen(getColour(), possibleCaptureMove);
                            legalMoves.add(new promotionMove(game.getBoard(), this, game.getBoard().getSquareAt(possibleCaptureMove).getPiece(), queen, getCoordinate(), possibleCaptureMove));
                        }
                        else
                            legalMoves.add(new normalMove(game.getBoard(), this, game.getBoard().getSquareAt(possibleCaptureMove).getPiece(), getCoordinate(), possibleCaptureMove));
                    }
                }
            }
        }//pawn capture moves
    }
    private void getEnPassantMove(final Game game, final List<Move> legalMoves, final int direction)
    {
        if (getColour() == Colour.WHITE && getCoordinate().getRank() != Board.FIFTH)
            return;
        if (getColour() == Colour.BLACK && getCoordinate().getRank() != Board.FOURTH)
            return;

        for (Coordinate beside: STANDING_BESIDE) {
            Coordinate possibleEnPassantMove = getCoordinate().add(beside);
            if (!possibleEnPassantMove.isValid())
                continue;
            Piece piece = game.getBoard().getSquareAt(possibleEnPassantMove).getPiece();
            if (piece != null && piece.getColour() != getColour()) {
                if (piece instanceof Pawn && lastMovePawnPushByTwo(game, piece)) { //if both pieces pawns
                    legalMoves.add(new enPassantMove(game.getBoard(), this, piece , getCoordinate(), possibleEnPassantMove.add(POSSIBLE_JUMP.multiply(direction))));
                    return;
                }//*** NOT COMPLETE LOGIC
            }
        }
    }//not "getEnPassantMoves", rules of chess strictly disallow "double" en passant
    public boolean isPromotion(Coordinate position) {
        if (getColour() == Colour.WHITE && position.getRank() != Board.EIGHTH)
            return false;
        return getColour() != Colour.BLACK || position.getRank() == Board.FIRST;
    }//check to see if a piece is a pawn, and either on the first or eighth rank based on colour
    private boolean getFirstMove() {
        if (getColour() == Colour.WHITE && getCoordinate().getRank() != Board.SECOND)
            return false;
        return getColour() != Colour.BLACK || getCoordinate().getRank() == Board.SEVENTH;
    }
    private boolean getFirstMove(final Coordinate coordinate, final Colour colour) {
        if (colour == Colour.WHITE && coordinate.getRank() != Board.SECOND)
            return false;
        return colour != Colour.BLACK || coordinate.getRank() == Board.SEVENTH;
    }
    private boolean lastMovePawnPushByTwo(final Game game, final Piece piece)
    {
        if (game.getGameMoves().getLast().getMovingPiece() != piece)
            return false;
        return (getFirstMove(game.getGameMoves().getLast().initialCoordinate(), piece.getColour()));
    }
    @Override
    public String toString() {
        return "P";
    }
}
