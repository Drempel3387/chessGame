package chess.Game;

import chess.Moves.Move;

import java.util.ArrayList;
import java.util.List;


public class moveList {
    private final List<Move> gameMoves;
    public moveList() { gameMoves = new ArrayList<>(); }
    public void addMoveToList(Move move) { gameMoves.add(move); }
    public Move getMoveAt(int number) { return gameMoves.get(number); }
    public int getNumberOfMoves() { return gameMoves.size(); }
    public void clear() { gameMoves.clear(); }
}
