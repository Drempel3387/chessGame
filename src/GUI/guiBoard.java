package GUI;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;

import javax.swing.*;
import java.awt.*;

public class guiBoard extends JPanel {
    private final int RANKS = 8;
    private final int FILES = 8;
    private final Board board;
    private final guiSquare[][] boardSquares = new guiSquare[RANKS][FILES];

    public guiBoard(Board board)
    {
        this.board = board;
        setLayout(new GridLayout(RANKS, FILES));

        for (int rank = 0; rank < RANKS; rank++)
        {
            for (int file = 0; file < FILES; file++)
            {
                Colour squareColour = ((rank+file)%2 == 0?Colour.WHITE:Colour.BLACK);
                add(new guiSquare(new Coordinate(file, RANKS - rank - 1), board, squareColour));
            }
        }
    }
}
