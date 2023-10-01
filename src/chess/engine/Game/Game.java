package chess.engine.Game;

import chess.engine.Board.Board;
import chess.engine.Board.Square;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.King;
import chess.engine.Pieces.Pawn;
import chess.engine.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private gameStatus status;
    private final Colour blackSide;
    private final Colour whiteSide;

    public Game(Board board)
    {
        this.board = board;
        status = gameStatus.ACTIVE;
        this.blackSide = Colour.BLACK;
        this.whiteSide = Colour.WHITE;
    }
    public gameStatus getStatus() {return status;}
    public void updateStatus(gameStatus status) {this.status = status;}


    boolean isPromotion(Piece piece)
    {
        if (!(piece instanceof Pawn))
            return false;
        Coordinate coordinate = piece.getCoordinate();

        if (piece.getColour() == Colour.WHITE && coordinate.getRank() != Board.EIGHTH)
            return false;
        if (piece.getColour() == Colour.BLACK && coordinate.getRank() != Board.FIRST)
            return false;
        return true;
    }//check to see if a piece is a pawn, and either on the first or eighth rank based on colour

    void promoteTo(Piece promoting, Piece promotingTo)
    {
        board.getSquareAt(promoting.getCoordinate()).setPiece(promotingTo);
        promotingTo.setCoordinate(promoting.getCoordinate());

        List<Piece> pieces = promoting.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces();
        pieces.add(promotingTo);
        promoting.setIsAlive(false);
        promotingTo.setIsAlive(true);
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

    public boolean isCheckMate(Colour side)
    {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (!king.isKingChecked(board))//if the king is not checked
        {
            return false;
        }
        return (preventCheckMoves(king, side).isEmpty());//no moves to prevent the check, so checkmate
    }

    public boolean isStaleMate(Colour side)
    {
        King king = (side == Colour.WHITE? board.getWhiteKing(): board.getBlackKing());
        if (king.isKingChecked(board))//if the king is checked, it is not a stalemate
            return false;
        if (!king.getLegalMoves(board).isEmpty())//if the king does have moves, not a stalemate
        {
            return false;
        }
        return noMoreMoves(side, king);//if there are no more moves on the board, stalemate
    }

    private boolean noMoreMoves(Colour side, King king) {
        List<Piece> pieceList = (side == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces());
        //get list of allied pieces
        for (Piece piece: pieceList)
        {
            if (!piece.getLegalMoves(board).isEmpty())
                return false;
        }
        return true;
    }
}

