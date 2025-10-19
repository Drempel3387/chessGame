package chess.Game;

import chess.Moves.Move;
import chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Devon R.
 *
 * This class represents a list of moves that are associated with a given game of chess.
 * A list of moves, and a mapping of Piece and Move are maintained as attributes. The list represents a linear
 * ordering of chessmoves that have been completed in a given game. The map allows quick identification of whether
 * a piece has moved or not. Functions for adding moves to the list and map, getting the last move played, checking
 * whether a piece has moved, getting the number of moves in the list, and clearing the list are present.
 */
public class moveList {
    /**
     * A list containing all moves played in the current game.
     */
    private final List<Move> gameMoves;
    /**
     * A mapping of Piece to Move which allows a quick check of whether a piece has moved.
     */
    private final Map<Piece, Move> pieceMoves;

    /**
     * Will create a moveList by initialize gameMoves to an arrayList, and pieceMoves to a hashMap
     */
    public moveList() {
        gameMoves = new ArrayList<>();
        pieceMoves = new HashMap<>();
    }

    /**
     * will add a move to gameMoves. This move will be the last move played in the game.
     * @param move last played move
     */
    public void addMoveToList(Move move) { gameMoves.add(move); }

    /**
     * Will add a key-value pair to the pieceMoves map
     * @param move last played move
     * @param piece piece that is moving
     */
    public void addMoveToList(Move move, Piece piece) {pieceMoves.put(piece, move);}

    /**
     * Will return a move at a specific index within gameMoves
     * @param number index of requested move
     * @return Move at the index
     */
    public Move getMoveAt(int number) { return gameMoves.get(number); }

    /**
     * Will return the last played move in the list of moves
     * @return Move at the final position in the list of moves
     */
    public Move getLast() {return gameMoves.get(gameMoves.size() - 1);}

    /**
     * Will return whether a piece has moved or not
     * @param piece being checked for moving status
     * @return boolean indicating moving status
     */
    public boolean hasPieceMoved(Piece piece) {
        return (pieceMoves.get(piece) != null);//if a piece is within the map, it MUST have moved.
    }

    /**
     * Will return the size of the list of moves
     * @return int size of lize
     */
    public int getNumberOfMoves() { return gameMoves.size(); }

    /**
     * Will remove all entries from the list of moves and the hashMap
     */
    public void clear() {
        gameMoves.clear();
        pieceMoves.clear();
    }
}
