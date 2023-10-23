package chess.Pieces;

import chess.Board.Board;
import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.enPassantMove;
import chess.Moves.normalMove;
import chess.Moves.promotionMove;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private boolean isAlive;//has the piece been captured
    private final Colour colour;//Colour of the piece, BLACK or WHITE
    private Coordinate coordinate;//Coordinate on the chessboard of the piece
    public Piece(final Colour colour, final Coordinate coordinate) {
        this.coordinate =  coordinate;
        this.colour = colour;
        this.isAlive = true;
    }
    //getters
    public Coordinate getCoordinate() {return coordinate;}
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}
    //setters
    public void setIsAlive(final boolean isAlive) {this.isAlive = isAlive;}
    public void setCoordinate(final Coordinate coordinate) {this.coordinate = coordinate;}
    protected void removeInvalidMoves(final List<Move> moves, final Board board, Colour currentColour) {
        King king = currentColour == Colour.WHITE? board.getWhiteKing(): board.getBlackKing();
        List<Move> illegalMoves = new ArrayList<>();
        for (Move move: moves) {
            move.makeMove();//make each current possible move
            if (king.isKingChecked(board))//if this move places the king into check, the piece is pinned
            {
                illegalMoves.add(move);
            }
            move.unMakeMove();//return back to original position
        }
        for (Move move: illegalMoves) {
            moves.remove(move);
        }//remove any illegal moves
    }//remove moves which place the king into check
    public abstract List<Move> getLegalMoves(final Game game);//a list of all legal moves for a piece
    public abstract boolean canAttackSquare(final Board board, final Coordinate squarePosition);
    public Boolean hasMoved(final moveList list)
    {
        for (int i = 0; i < list.getNumberOfMoves(); i++)
            if (list.getMoveAt(i).getMovingPiece() == this)
                return true;
        return false;
    }
    @Override
    public abstract String toString();//print the piece to the screen, used to play console chess
}

