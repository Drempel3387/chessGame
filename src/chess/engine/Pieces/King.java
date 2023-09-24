package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends steppingPiece {

    public static final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1),
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each diagonal, and straight direction
    public King(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    public boolean isKingChecked(Board board, Coordinate kingCoordinate)
    {
        //will place each type of piece at the kingCoordinate and see if it can capture an enemy piece of the same type. If it can, the square is covered, and the king is checked
        Piece[] pieceArr = {
                new Queen(this.colour, kingCoordinate), new Pawn(this.colour, kingCoordinate),
                new Bishop(this.colour, kingCoordinate), new Rook(this.colour, kingCoordinate),
                new Knight(this.colour, kingCoordinate)
        };

        for (Piece piece: pieceArr) {
            List<Move> moves = piece.getLegalMoves(board, POSSIBLE_MOVES);
            for (Move move : moves) {
                if (move.isCapture()) {//if the move is a capture, must check to see if the piece being captured is the same type
                    if (Objects.equals(move.getEndingPiece().toString(), move.getPiece().toString()))
                    {
                        return true;
                    }//if they are the same, the king is in check
                }
            }

        }

        return false;
    }

    @Override
    public String toString() {
        return "K";
    }

}
