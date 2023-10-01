package chess.engine.Moves;


import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.*;

public abstract class Move {
    protected final Board board;
    protected final Piece movingPiece;
    protected final Piece capturedPiece;
    protected final Coordinate endingCoordinate;
    protected final Coordinate initialCoordinate;

    public Move(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate)
    {
        this.board = board;
        this.movingPiece = movingPiece;
        this.capturedPiece = capturedPiece;
        this.initialCoordinate = initialCoordinate;
        this.endingCoordinate = endingCoordinate;
    }//constructor for non-capture move


    public Piece getMovingPiece() {
        return movingPiece;
    }
    public Coordinate initialCoordinate()
    {
        return initialCoordinate;
    }//initial coordinate of the piece which is making a move
    public Coordinate getEndingCoordinate()
    {
        return endingCoordinate;
    }//ending coordinate of the piece
    public boolean isCapture()
    {
        return (getEndingPiece() != null && (getEndingPiece().getColour() != movingPiece.getColour()));
    }//is this a capture move?

    public Piece getEndingPiece()
    {
        return (capturedPiece);
    }//get the piece at the ending coordinate

    public Colour getMovingPieceColour()
    {
        return movingPiece.getColour();
    }

    public abstract void makeMove();
    public abstract void unMakeMove();

    @Override
    public String toString()
    {
        String move = "";
        if (movingPiece instanceof Pawn)
        {
            move += movingPiece.getCoordinate().fileToString();
        }//if a pawn move, use the file letter
        else
        {
            move += movingPiece.toString();
        }//otherwise use the piece
        if (isCapture())
        {
            move += "x";
        }//if a capturing move, add an x, if the piece being c

        move += getEndingCoordinate().fileToString();
        move += endingCoordinate.getRank() + 1;
        return move;
    }


}
