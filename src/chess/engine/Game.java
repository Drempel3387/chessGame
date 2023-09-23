package chess.engine;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;

public class Game {
    private final Board board;
    private gameStatus status;
    private boolean whitesTurn;

    public Game(Board board)
    {
        this.board = board;
        this.whitesTurn = true;
        status = gameStatus.ACTIVE;
    }
    public boolean isWhitesTurn() {return whitesTurn;}
    public gameStatus getStatus() {return status;}


}
