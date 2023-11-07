package chess.Game;

import chess.Board.Board;
import chess.Colour;
import chess.Moves.Move;
import chess.Pieces.King;
import chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Devon R.
 */
public class Game {
    /**
     * The Board which the game will be played on
     */
    private final Board board;
    /**
     * A list of moves that have been played during the game
     */
    private final moveList gameMoves;
    /**
     * Current status of the game.
     */
    private Status status;
    /**
     * The colour of the player whose turn it is to move
     */
    private Colour currentPlayerColour;

    /**
     * Creates a Game object
     * @param board which the game will be played on
     */
    public Game(final Board board) {
        this.board = board;
        this.gameMoves = new moveList();
        this.status = Status.ACTIVE;
        this.currentPlayerColour = Colour.WHITE;
    }

    /**
     * returns the board associated with the game
     * @return board
     */
    public Board getBoard() { return board; }

    /**
     * returns the list of moves that were played in the game
     * @return gameMoves
     */
    public moveList getGameMoves() { return gameMoves; }

    /**
     * returns the status of the game
     * @return status
     */
    public Status getStatus() { return status; }

    /**
     * returns the player whose turn it is to move
     * @return currentPlayerColour
     */
    public Colour getCurrentPlayerColour() { return currentPlayerColour; }

    /**
     * updates the status of the game
     * @param status new game status
     */
    public void setStatus(final Status status) {this.status = status;}

    /**
     * updates the colour of the current player
     * @param updatedColour colour of the new current player
     */
    public void updateCurrentPlayerColour(final Colour updatedColour) {currentPlayerColour = updatedColour;}

    /**
     * Represents a turn for a current player of white. Will make a valid move, check for new game status,
     * and add the move to the move-list if the game will continue. Returns the status after the move is played
     * @param whiteMove move of the white player
     * @return Status of the game
     */
    public Status whiteTurn(final Move whiteMove) {
        whiteMove.makeMove();
        updateCurrentPlayerColour(Colour.BLACK);

        if (isCheckMate(Colour.BLACK))
            return Status.WHITE_WIN;
        if (isStaleMate(Colour.BLACK))
            return Status.STALEMATE;

        gameMoves.addMoveToList(whiteMove);
        gameMoves.addMoveToList(whiteMove, whiteMove.getMovingPiece());
        return Status.ACTIVE;
    }

    /**
     * Represents a turn for a current player of black. Will make a valid move, check for new game status,
     * and add the move to the move-list if the game will continue. Returns the status after the move is played
     * @param blackMove move of the black player
     * @return Status of the game
     */
    public Status blackTurn(final Move blackMove) {
        blackMove.makeMove();
        updateCurrentPlayerColour(Colour.WHITE);

        if (isCheckMate(Colour.WHITE))
            return Status.BLACK_WIN;
        if (isStaleMate(Colour.WHITE))
            return Status.STALEMATE;

        gameMoves.addMoveToList(blackMove);
        gameMoves.addMoveToList(blackMove, blackMove.getMovingPiece());
        return Status.ACTIVE;
    }

    /**
     * Will calculate and return a list of any moves that will prevent/escape a check. Assumes that the king of the
     * given colour is in check.
     * @param king king that is in check
     * @return List<Move>
     */
    private List<Move> preventCheckMoves(final King king) {
        List<Piece> pieces = king.getColour() == Colour.WHITE ? board.getWhitePieces() : board.getBlackPieces();
        List<Move> movesToPreventCheck = new ArrayList<>();

        for (Piece piece : pieces) {
            if (!piece.getIsAlive())//if piece is not alive, skip
                continue;
            for (Move move : piece.getLegalMoves(this)) {
                move.makeMove();
                if (!king.isKingChecked(board))
                    movesToPreventCheck.add(move);
                move.unMakeMove();
            }//if a move will stop the check, add to the list
        }//any moves which are not king moves

        movesToPreventCheck.addAll(king.getLegalMoves(this));//add any king moves which avoid the check
        return movesToPreventCheck;
    }

    /**
     * Returns whether a colour is in checkmate
     * @param side colour to verify checkmate status
     * @return boolean indicating checkmate status
     */
    public boolean isCheckMate(final Colour side) {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (!king.isKingChecked(board))//if the king is not checked
            return false;
        return (preventCheckMoves(king).isEmpty());//no moves to prevent the check, so checkmate
    }

    /**
     * Returns whether a colour is stalemated
     * @param side colour to verify for stalemate status
     * @return boolean indicating stalemate status
     */
    public boolean isStaleMate(final Colour side) {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (king.isKingChecked(board))//if the king is checked, it is not a stalemate
            return false;
        if (!king.getLegalMoves(this).isEmpty())//if the king does have moves, not a stalemate
            return false;
        return noMoreMoves(king);//if there are no more moves on the board for the current player, stalemate
    }

    /**
     * used to determine if there are any moves left on a chessboard for a king
     * @param king king to determine moving status
     * @return boolean indicating king moving ability status
     */
    private boolean noMoreMoves(final King king) {
        List<Piece> pieceList = (king.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces());
        //get list of allied pieces
        for (Piece piece: pieceList) {
            if (!piece.getIsAlive())
                continue;
            if (!piece.getLegalMoves(this).isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Used to reset a Game back to it's starting conditions.
     */
    public void reset() {
        board.reset();
        gameMoves.clear();
        status = Status.ACTIVE;
        currentPlayerColour = Colour.WHITE;
    }

}
