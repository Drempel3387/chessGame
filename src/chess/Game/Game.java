package chess.Game;

import chess.Board.Board;
import chess.Colour;
import chess.Moves.Move;
import chess.Pieces.King;
import chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final moveList gameMoves;
    private Status status;
    private Colour currentPlayerColour;


    public Game(Board board) {
        this.board = board;
        this.gameMoves = new moveList();
        this.status = Status.ACTIVE;
        this.currentPlayerColour = Colour.WHITE;
    }

    public Board getBoard() { return board; }

    public moveList getGameMoves() { return gameMoves; }
    public Status getStatus() { return status; }
    public Colour getCurrentPlayerColour() { return currentPlayerColour; }

    public void setStatus(Status status) {this.status = status;}
    public void updateCurrentPlayerColour(Colour updatedColour) {currentPlayerColour = updatedColour;}

    public Status whiteTurn(Move whiteMove) {
        whiteMove.makeMove();
        updateCurrentPlayerColour(Colour.BLACK);
        System.out.println(whiteMove);

        if (isCheckMate(Colour.WHITE))
            return Status.WHITE_WIN;
        if (isStaleMate(Colour.WHITE))
            return Status.STALEMATE;

        gameMoves.addMoveToList(whiteMove);
        return Status.ACTIVE;
    }

    public Status blackTurn(Move blackMove) {
        blackMove.makeMove();
        updateCurrentPlayerColour(Colour.WHITE);
        System.out.println(blackMove);

        if (isCheckMate(Colour.BLACK))
            return Status.BLACK_WIN;
        if (isStaleMate(Colour.BLACK))
            return Status.STALEMATE;

        gameMoves.addMoveToList(blackMove);
        return Status.ACTIVE;
    }

    private List<Move> preventCheckMoves(King king, Colour side) {
        List<Piece> pieces = side == Colour.WHITE ? board.getWhitePieces() : board.getBlackPieces();
        List<Move> movesToPreventCheck = new ArrayList<>();

        for (Piece piece : pieces) {
            if (!piece.getIsAlive())//if piece is not alive, skip
                continue;
            for (Move move : piece.getLegalMoves(board)) {
                move.makeMove();
                if (!king.isKingChecked(board)) {
                    movesToPreventCheck.add(move);
                }
                move.unMakeMove();
            }//if a move will stop the check, add to the list
        }//any moves which are not king moves

        movesToPreventCheck.addAll(king.getLegalMoves(board));//add any king moves which avoid the check
        return movesToPreventCheck;
    }

    public boolean isCheckMate(Colour side) {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (!king.isKingChecked(board))//if the king is not checked
        {
            return false;
        }
        return (preventCheckMoves(king, side).isEmpty());//no moves to prevent the check, so checkmate
    }
    public boolean isStaleMate(Colour side) {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (king.isKingChecked(board))//if the king is checked, it is not a stalemate
            return false;
        if (!king.getLegalMoves(board).isEmpty())//if the king does have moves, not a stalemate
        {
            return false;
        }
        return noMoreMoves(side, king);//if there are no more moves on the board for the current player, stalemate
    }

    private boolean noMoreMoves(Colour side, King king) {
        List<Piece> pieceList = (side == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces());
        //get list of allied pieces
        for (Piece piece: pieceList)
        {
            if (!piece.getIsAlive())
                continue;
            if (!piece.getLegalMoves(board).isEmpty())
                return false;
        }
        return true;
    }

    public void reset() {
        board.reset();
        gameMoves.clear();
        status = Status.ACTIVE;
        currentPlayerColour = Colour.WHITE;
    }

}
