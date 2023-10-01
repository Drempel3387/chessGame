package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected boolean isAlive;//has the piece been captured
    protected final Colour colour;//Colour of the piece, BLACK or WHITE
    protected Coordinate coordinate;//Coordinate on the chessboard of the piece
    protected boolean hasMoved;
    public Piece(final Colour colour, final Coordinate coordinate)
    {
        this.coordinate =  coordinate;
        this.colour = colour;
        this.isAlive = true;
        this.hasMoved = false;
    }
    //getters
    public Coordinate getCoordinate() {return coordinate;}
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}

    //setters
    public void setIsAlive(final boolean isAlive) {this.isAlive = isAlive;}
    public void setCoordinate(final Coordinate coordinate) {this.coordinate = coordinate;}
    public void setHasMoved(final boolean hasMoved)
    {
        this.hasMoved = hasMoved;
    }
    public boolean getHasMoved()
    {return hasMoved;}

    protected void removeInvalidMoves(final List<Move> moves, final Board board, Colour currentColour)//remove moves which place the king into check
    {
        King king = currentColour == Colour.WHITE? board.getWhiteKing(): board.getBlackKing();
        List<Move> illegalMoves = new ArrayList<>();
        for (Move move: moves)
        {
            move.makeMove();//make each current possible move
            if (king.isKingChecked(board))//if this move places the king into check, the piece is pinned
                illegalMoves.add(move);

            move.unMakeMove();//return back to original position
        }

        for (Move move: illegalMoves)
        {
            moves.remove(move);
        }//remove any illegal moves
    }

    public abstract List<Move> getLegalMoves(final Board board);//a list of all legal moves for a piece
    public abstract boolean canAttackSquare(final Board board, final Coordinate squarePosition);
    public abstract String toString();//print the piece to the screen, used to play console chess

}

