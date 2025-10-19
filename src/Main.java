import GUI.mainFrame;
import chess.Board.Board;
import chess.Game.Game;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board);
        mainFrame frame = new mainFrame(game);
    }
}