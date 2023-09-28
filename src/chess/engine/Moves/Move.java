package chess.engine.Moves;


import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.*;

public class Move {
    private final Board board;
    private final Piece piece;
    private final Coordinate endingCoordinate;

    public Move(Board board, Piece piece, Coordinate endingCoordinate)
    {
        this.board = board;
        this.piece = piece;
        this.endingCoordinate = endingCoordinate;
    }//constructor for non-capture move


    public Piece getPiece() {
        return piece;
    }
    public Coordinate initialCoordinate()
    {
        return piece.getCoordinate();
    }//initial coordinate of the piece which is making a move
    public Coordinate getEndingCoordinate()
    {
        return endingCoordinate;
    }//ending coordinate of the piece
    public boolean isCapture()
    {
        return (getEndingPiece() != null && (getEndingPiece().getColour() != piece.getColour()));
    }//is this a capture move?

    public Piece getEndingPiece()
    {
        return (board.getSquareAt(endingCoordinate).getPiece());
    }//get the piece at the ending coordinate

    @Override
    public String toString()
    {
        String move = "";
        if (piece instanceof Pawn)
        {
            move += piece.getCoordinate().fileToString();
        }//if a pawn move, use the file letter
        else
        {
            move += piece.toString();
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
