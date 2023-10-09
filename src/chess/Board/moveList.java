package chess.Board;

import chess.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class moveList {
    private final Board board;
    private final List<Move> gameMoves = new ArrayList<>();
    public moveList(Board board)
    {
        this.board = board;
    }

    public void addMoveToList(Move move)
    {
        gameMoves.add(move);
    }
    public Move getMoveAt(int number)
    {
        return gameMoves.get(number);
    }
    public int getNumberOfMoves()
    {
        return gameMoves.size();
    }

}
