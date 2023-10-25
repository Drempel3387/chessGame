package chess.Pieces;

import chess.Board.Board;
import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.castleMove;
import chess.Pieces.PieceMoveType.steppingPiece;

import java.util.List;

/**
 * @author Devon R.
 *
 * A king is a specialization of the steppingPiece class. A king can move to any square in a one square radius of its
 * current position. A king is a special piece given that it determines if the game has been won or not. If a king
 * is attacked by a piece, it must move, or the attack must be stopped in some way. Additionally, a king has a special
 * move called castling where it may move to either side of the board if the squares between the rook and it are clear,
 * and the king and rook have not moved yet.
 */
public class King extends steppingPiece {
    /**
     * These coordinates represent the left and right directions that will be used to check to see if a king can castle
     */
    private final Coordinate[] CASTLE_CHECK_MOVES = {
            new Coordinate(1, 0), new Coordinate(-1, 0) };
    /**
     * These coordinates represent every one square direction that a king can possibly move to.
     */
    private final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1),
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1) };
    public King(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public List<Move> getLegalMoves(final Game game) { //extension of base logic for stepping piece must account for castling
        List<Move> legalMoves = getLegalMoves(game, POSSIBLE_MOVES);
        getCastleMoves(game, legalMoves);
        return legalMoves;
    }
    @Override
    public boolean canAttackSquare(final Board board,final Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }

    /**
     * This method will update the list of legal moves adding any valid castle moves.
     * @param game game that the king belongs to
     * @param allLegalMoves the current list of legal moves for the king
     */
    private void getCastleMoves(final Game game, final List<Move> allLegalMoves) {
        if (isKingChecked(game.getBoard()))//if the king is checked, castling is not possible
            return;
        if (game.getGameMoves().hasPieceMoved(this))//if king has moved, cannot castle
            return;
        Coordinate currentSquareCoord = getCoordinate();
        for (Coordinate coordinate: CASTLE_CHECK_MOVES) {
            currentSquareCoord = getCoordinate().add(coordinate);//move one square in the current direction
            while (currentSquareCoord.isValid()) {
                if (game.getBoard().getSquareAt(currentSquareCoord).isOccupied()) {
                    Piece piece = game.getBoard().getSquareAt(currentSquareCoord).getPiece();
                    if (piece instanceof Rook && piece.getColour() == getColour() && !game.getGameMoves().hasPieceMoved(piece)) {
                        if (coordinate.areEqual(new Coordinate(1, 0)))
                            //king-side castling
                            allLegalMoves.add(new castleMove(game.getBoard(), this, piece,getCoordinate(),currentSquareCoord.add(coordinate.multiply(-1))));
                        else
                            //queen-side castling
                            allLegalMoves.add(new castleMove(game.getBoard(), this, piece,getCoordinate(),currentSquareCoord.add(coordinate.multiply(-2))));
                    }
                    /*the king will not be moving to the square the rook is on, instead it will either be the square
                    before that one or the one two squares before, so we must step back before adding the move
                    */
                    else//if the piece is not a rook, cannot castle
                        break;
                }
                else//if not occupied, have to see if the square would place the king into check
                    if (isKingChecked(game.getBoard()))//cannot castle through a square that would place the king into checl
                        break;
                currentSquareCoord = currentSquareCoord.add(coordinate);//move one square further in the current direction
            }
        }
    }

    /**
     * Returns whether the king is in check in the current position on the board.
     * @param board the board which the piece belongs to
     * @return boolean indicating whether the king is checked
     */
    public boolean isKingChecked(final Board board) {
        List<Piece> pieces = (getColour() == Colour.WHITE? board.getBlackPieces(): board.getWhitePieces());
        for (Piece piece: pieces) {
            if (!piece.getIsAlive())
                continue;
            if (piece.canAttackSquare(board, getCoordinate()))
                return true;
        }
        return false;
    }
    @Override
    public String toString() { return "K"; }

}
