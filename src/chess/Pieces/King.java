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


public class King extends steppingPiece {
    private final Coordinate[] CASTLE_CHECK_MOVES = {
            new Coordinate(1, 0), new Coordinate(-1, 0) };
    private final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1),
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1) };//possible moves for each diagonal, and straight direction
    public King(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    @Override
    public List<Move> getLegalMoves(final Game game) { //extension of base logic for stepping piece, must account for checks, castling, etc
        List<Move> legalMoves = getLegalMoves(game, POSSIBLE_MOVES);
        getCastleMoves(game, legalMoves);
        return legalMoves;
    }
    @Override
    public boolean canAttackSquare(final Board board,final Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }
    private void getCastleMoves(final Game game, final List<Move> allLegalMoves) {
        if (isKingChecked(game.getBoard()))//if the king is checked, castling is not possible
            return;
        if (game.getGameMoves().hasPieceMoved(this))//if king has moved, cannot castle
            return;
        Coordinate currentSquareCoord, startingCoordinate = getCoordinate();
        for (Coordinate coordinate: CASTLE_CHECK_MOVES) {
            currentSquareCoord = getCoordinate().add(coordinate);//move one square in the current direction
            while (currentSquareCoord.isValid()) {
                setCoordinate(currentSquareCoord);
                if (game.getBoard().getSquareAt(currentSquareCoord).isOccupied()) {
                    Piece piece = game.getBoard().getSquareAt(currentSquareCoord).getPiece();
                    if (piece instanceof Rook && piece.getColour() == getColour() && !game.getGameMoves().hasPieceMoved(piece)) {
                        if (coordinate.areEqual(new Coordinate(1, 0)))
                            allLegalMoves.add(new castleMove(game.getBoard(), this, piece,startingCoordinate,currentSquareCoord.add(coordinate.multiply(-1))));
                        else
                            allLegalMoves.add(new castleMove(game.getBoard(), this, piece,startingCoordinate,currentSquareCoord.add(coordinate.multiply(-2))));
                    }
                    /*the king will not be moving to the square the rook is on, instead it will be the square
                    before that one, so must step back one square before adding the move
                    */
                    else
                        break;
                }
                else
                    if (isKingChecked(game.getBoard()))
                        break;
                currentSquareCoord = currentSquareCoord.add(coordinate);
            }
            setCoordinate(startingCoordinate);
        }
        setCoordinate(startingCoordinate);
    }
    public boolean isKingChecked(Board board) {
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
