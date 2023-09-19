import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Board.chessTile;
import chess.engine.Pieces.Bishop;
import chess.engine.Pieces.Piece;
import chess.engine.Board.Moves.Move;
import chess.engine.Pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Rook rook = (Rook) board.getTileAt(new Coordinate(Board.FIRST, Board.EIGHTH)).getPiece();
        List<Move> list = rook.getLegalMoves(board);


        for (Move move: list)
        {
            System.out.println(move.getEndingCoordinate().toString());
        }//print out valid moves for the rook
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                System.out.print(board.getTileAt(new Coordinate(j, i)).getCoordinate().toString() + " ");
            }
            System.out.println();
        }//print coordinates of each square


        board.print();//print the pieces on the board
    }
}