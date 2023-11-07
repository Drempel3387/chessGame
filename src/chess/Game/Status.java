package chess.Game;
/**
 * @author Devon R.
 *
 * This enumeration relates to the current Status of the chessboard. It holds values to signify a black win,
 * a white win, a stalemate, and if the game is still going.
 */
public enum Status {
    /**
     * Black has won the game by checkmate
     */
    BLACK_WIN,
    /**
     * White has won the game by checkmate
     */
    WHITE_WIN,
    /**
     * The game is currently active, meaning that after a move has been made, the other side may continue to make a move.
     */
    ACTIVE,
    /**
     * The game is in a stalemate, meaning that after a move has been made, the current player has no legal moves.
     */
    STALEMATE,
}
