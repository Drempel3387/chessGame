package chess.GUI;
import chess.engine.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class boardGUI {
    private final JFrame chessBoard;
    private final boardPanel BoardPanel;
    private final Board actualBoard;
    private static final Dimension FRAME_SIZE = new Dimension(800, 800);
    public boardGUI()
    {
        this.actualBoard = new Board();
        //frame creation
        chessBoard = new JFrame();
        chessBoard.setSize(FRAME_SIZE);
        chessBoard.setLayout(new BorderLayout());
        chessBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //board panel
        BoardPanel = new boardPanel(actualBoard);
        chessBoard.add(BoardPanel, BorderLayout.CENTER);

        //menu creation
        JMenuBar boardMenuBar = new JMenuBar();
        createMenuBar(boardMenuBar);
        chessBoard.setJMenuBar(boardMenuBar);

        chessBoard.setVisible(true);
    }

    private void createMenuBar(JMenuBar boardMenuBar) {
        boardMenuBar.add(createFileMenu());
    }

    private JMenu createFileMenu() {
        final JMenu menu = new JMenu("File");
        final JMenuItem pgnFile = new JMenuItem( "Load PGN file");
        pgnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open PGN");
            }
        });

        final JMenuItem exitOption = new JMenuItem("Exit");
        exitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(pgnFile);
        menu.add(exitOption);
        return menu;
    }

}
