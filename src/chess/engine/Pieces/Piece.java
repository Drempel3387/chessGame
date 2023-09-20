package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Board.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.List;

public abstract class Piece {
    protected boolean isAlive;//has the piece been captured
    protected final Colour colour;//Colour of the piece, BLACK or WHITE
    protected Coordinate coordinate;
    public Piece(final Colour colour, Coordinate coordinate)
    {
        this.coordinate =  coordinate;
        this.colour = colour;
        this.isAlive = true;
    }
    //getters
    Coordinate getCoordinate() {return coordinate;}
    public boolean getIsAlive() {return isAlive;}
    public Colour getColour() {return colour;}

    //setters
    void setIsAlive(boolean isAlive) {this.isAlive = isAlive;}
    void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;}

    public abstract List<Move> getLegalMoves(final Board board);
    public abstract String toString();
}

