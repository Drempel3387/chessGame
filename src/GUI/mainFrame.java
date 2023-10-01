package GUI;

import chess.engine.Board.Board;

import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {
    private final Dimension FRAME_SIZE = new Dimension(800, 800);

    public mainFrame(Board board)
    {
        setSize(FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        add(new guiBoard(board));


        setVisible(true);
    }
}
