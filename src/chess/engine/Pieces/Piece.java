package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.List;

public abstract class Piece {
    protected boolean isAlive;//has the piece been captured
    protected final Colour colour;//Colour of the piece, BLACK or WHITE
    protected Coordinate coordinate;//Coordinate on the chessboard of the piece
    public Piece(final Colour colour, Coordinate coordinate)
    {
        this.coordinate =  coordinate;
        this.colour = colour;
        this.isAlive = true;
    }
    //getters
    public Coordinate getCoordinate() {return coordinate;}
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}

    //setters
    public void setIsAlive(boolean isAlive) {this.isAlive = isAlive;}
    public void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;}

    public abstract List<Move> getLegalMoves(final Board board, final Coordinate[] POSSIBLE_MOVES);//a list of all legal moves for a piece
    public abstract String toString();//print the piece to the screen, used to play console chess
}

