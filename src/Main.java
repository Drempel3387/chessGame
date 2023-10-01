import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Game.Game;
import chess.engine.Moves.Move;
import chess.engine.Pieces.*;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board);

        board.print();
        Piece piece = board.getWhiteKing();
        for (Move move: piece.getLegalMoves(board))
        {
            System.out.println(move.getEndingCoordinate());
        }

        piece.getLegalMoves(board).get(3).makeMove();
        board.print();


    }
}