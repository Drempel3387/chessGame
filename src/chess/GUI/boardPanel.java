package chess.GUI;

import chess.engine.Board.Board;
import chess.engine.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class boardPanel extends JPanel {
    public final static int RANKS = 8;
    public final static int FILES = 8;
    public final static int LENGTH = 600;
    public final static int WIDTH = 600;
    private final Board board;
    final private squarePanel[][] squares = new squarePanel[RANKS][FILES];
    boardPanel(Board board)
    {
        super(new GridLayout(RANKS, FILES));
        this.board = board;
        for (int i = RANKS -1; i >= 0; i--)
        {
            for (int j = 0; j < FILES; j++)
            {
                final squarePanel square = new squarePanel(this, new Coordinate(j, i), board);
                squares[i][j] = square;
                this.add(square);
            }
        }
        setPreferredSize(new Dimension(WIDTH, LENGTH));
        validate();
    }

    public squarePanel[][] getSquares() {
        return squares;
    }
}
