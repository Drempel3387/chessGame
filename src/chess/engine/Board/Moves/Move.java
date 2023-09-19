package chess.engine.Board.Moves;

import chess.engine.Board.Board;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

public abstract class Move {
    private Board board;
    private Piece piece;
    private Coordinate endingCoordinate;

    Move(Board board, Piece piece, Coordinate endingCoordinate)
    {
        this.board = board;
        this.piece = piece;
        this.endingCoordinate = endingCoordinate;
    }

    public Coordinate getEndingCoordinate() {return endingCoordinate;}
}
