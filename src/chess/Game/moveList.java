package chess.Game;

import chess.Moves.Move;
import chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class moveList {
    private final List<Move> gameMoves;
    private final Map<Piece, Move> pieceMoves;
    public moveList() {
        gameMoves = new ArrayList<>();
        pieceMoves = new HashMap<>();
    }
    public void addMoveToList(Move move) { gameMoves.add(move); }
    public void addMoveToList(Move move, Piece piece) {pieceMoves.put(piece, move);}
    public Move getMoveAt(int number) { return gameMoves.get(number); }
    public Move getLast() {return gameMoves.get(gameMoves.size() - 1);}
    public boolean hasPieceMoved(Piece piece) {
        return (pieceMoves.get(piece) != null);//if a piece is within the map, it MUST have moved.
    }
    public int getNumberOfMoves() { return gameMoves.size(); }
    public void clear() { gameMoves.clear(); }
}
