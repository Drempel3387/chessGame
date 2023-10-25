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
 *  Specialization of the steppingPiece class that represents a chess pawn. A pawn can move forwards by two squares if
 *  it has not moved yet. Otherwise the pawn can move forward by one square, and capture pieces that are in front of it
 *  on the diagonals(on square away). The pawn has a special move called enPassant where if an enemy pawn moves two squares
 *  forward and lands beside a differently coloured pawn, it may be captured on the move directly after.
 */
public class Pawn extends steppingPiece {
    /**
     * Possible one square jump forwards
     */
    private final Coordinate POSSIBLE_JUMP = new Coordinate(0,1);
    /**
     * Possible capture moves for a pawn.
     */
    private final Coordinate[] POSSIBLE_CAPTURES = {
            new Coordinate(1, 1), new Coordinate(-1, 1)
    };
    /**
     * Used for enPassant validation. Used to check if the squares beside a pawn have an occupying piece.
     */
    private final Coordinate[] STANDING_BESIDE = {
            new Coordinate(1, 0), new Coordinate(-1, 0)
    };

    /**
     * Creates a pawn with a specific colour and coordinate
     * @param colour colour of the pawn
     * @param coordinate starting coordinate of the pawn
     */
    public Pawn(Colour colour, Coordinate coordinate) { super(colour, coordinate); }

    /**
     * Override of getLegalMoves function for steppingPiece. The base logic is not sufficient to model pawn move behaviour.
     * This account for captures, jumps, enPassant, and deletion of illegalMoves
     * @param game game which the piece belongs to
     * @return
     */
    public List<Move> getLegalMoves(final Game game) {
        List<Move> legalMoves = new ArrayList<>();
        int direction = (getColour() == Colour.WHITE)? -1: 1;

        getEnPassantMove(game, legalMoves, direction);
        getJumpMoves(game, legalMoves, direction);
        getCaptureMoves(game, legalMoves, direction);
        removeInvalidMoves(legalMoves, game.getBoard(), getColour());

        return legalMoves;
    }
    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_CAPTURES);
    }

    /**
     * Will return any possible jumps that a pawn has. I define a jump as a one or two square move forwards.
     * @param game game that the pawn belongs to
     * @param legalMoves list of legalMoves to add the move/s to.
     * @param direction direction which the pawn is moving.
     */
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

    /**
     *
     * @param game game that the pawn belongs to
     * @param legalMoves list of legalMoves to add the move/s to.
     * @param direction direction of pawn movement.
     */
    private void getCaptureMoves(final Game game, final List<Move> legalMoves, final int direction) {
        for (Coordinate capture: POSSIBLE_CAPTURES) {
            Coordinate possibleCaptureMove = getCoordinate().add(capture.multiply(direction));//attempt to move the pawn to a capture coordinate
            if (possibleCaptureMove.isValid()) {
                if (game.getBoard().getSquareAt(possibleCaptureMove).isOccupied()) {//if there is a piece at the capture coordinate
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

    /**
     * will return any valid enPassant moves that a pawn has.
     * @param game game which the pawn belongs to
     * @param legalMoves list of moves to add the enPassant move to.
     * @param direction direction of pawn movement
     */
    private void getEnPassantMove(final Game game, final List<Move> legalMoves, final int direction)
    {
        if (getColour() == Colour.WHITE && getCoordinate().getRank() != Board.FIFTH)
            return;//if pawn is white and not on fifth rank, cannot enPassant
        if (getColour() == Colour.BLACK && getCoordinate().getRank() != Board.FOURTH)
            return;//if pawn is black and not on fourth rank, cannot enPassant

        for (Coordinate beside: STANDING_BESIDE) {
            Coordinate possibleEnPassantMove = getCoordinate().add(beside);
            if (!possibleEnPassantMove.isValid())
                continue;
            Piece piece = game.getBoard().getSquareAt(possibleEnPassantMove).getPiece();
            if (piece != null && piece.getColour() != getColour()) {//if a piece is standing beside and not the same colour
                if (piece instanceof Pawn && lastMovePawnPushByTwo(game, piece)) { //if both pieces pawns
                    legalMoves.add(new enPassantMove(game.getBoard(), this, piece , getCoordinate(), possibleEnPassantMove.add(POSSIBLE_JUMP.multiply(direction))));
                    return;
                }
            }
        }
    }//not "getEnPassantMoves", rules of chess strictly disallow "double" en passant

    /**
     * will return whether a pawn move corresponds to a promotion
     * @param position coordinate on the chessboard
     * @return boolean indicating if a move is a promotion
     */
    public boolean isPromotion(final Coordinate position) {
        if (getColour() == Colour.WHITE && position.getRank() != Board.EIGHTH)
            return false;
        return getColour() != Colour.BLACK || position.getRank() == Board.FIRST;
    }//check to see if a piece is a pawn, and either on the first or eighth rank based on colour

    /**
     * will verify if a pawn has yet to move (is on its starting square)
     * @return boolean indicating first move status
     */
    private boolean getFirstMove() {
        if (getColour() == Colour.WHITE && getCoordinate().getRank() != Board.SECOND)
            return false;
        return getColour() != Colour.BLACK || getCoordinate().getRank() == Board.SEVENTH;
    }
    /**
     * will verify if a pawn has yet to move (is on its starting square) based on a given coordinate
     * @return boolean indicating first move status
     */
    private boolean getFirstMove(final Coordinate coordinate, final Colour colour) {
        if (colour == Colour.WHITE && coordinate.getRank() != Board.SECOND)
            return false;
        return colour != Colour.BLACK || coordinate.getRank() == Board.SEVENTH;
    }

    /**
     * Will validate if the last move on the board was a pawn push by two squares. Used for enPassant validation
     * @param game game the pawn belongs to
     * @param piece the last piece which moved.
     * @return boolean indicating if last move was a push by two.
     */
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
