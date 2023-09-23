import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Game;
import chess.engine.Moves.Move;
import chess.engine.Pieces.Pawn;
import chess.engine.Pieces.Piece;
import chess.engine.Pieces.Queen;
import chess.engine.Player;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(Colour.WHITE);
        Board board = new Board();
        board.print();


    }
}