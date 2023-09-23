package chess.engine.Moves;


import chess.engine.Board.Board;
import chess.engine.Board.Square;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.*;

import java.util.List;
import java.util.Objects;

public class Move {
    private Board board;
    private Piece piece;
    private Coordinate endingCoordinate = null;

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
        return (board.getTileAt(endingCoordinate).getPiece());
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

    public Move stringToMove(String moveString, Board board)
    {
        boolean captureMove = moveString.contains("x");
        return null;
    }

    public boolean isSquareCovered(Colour playerColour, Square square)
    {
        //will place each type piece at the square and see if it can capture an enemy piece of the same type. If it can, the square is covered
        Piece[] pieceArr = {
                new Queen(playerColour, square.getCoordinate()), new Pawn(playerColour, square.getCoordinate()),
                new Bishop(playerColour, square.getCoordinate()), new Rook(playerColour, square.getCoordinate()),
                new Knight(playerColour, square.getCoordinate()), new King(playerColour, square.getCoordinate())
        };

        for (Piece piece: pieceArr) {
            List<Move> moves = piece.getLegalMoves(board);
            for (Move move : moves) {
                if (move.isCapture()) {
                    if (Objects.equals(move.getEndingPiece().toString(), move.getPiece().toString()))
                    {
                        return true;
                    }
                }
            }

        }

        return false;
    }

}
