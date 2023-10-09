import GUI.mainFrame;
import chess.Board.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        mainFrame frame = new mainFrame(board);
    }
}