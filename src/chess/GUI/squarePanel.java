package chess.GUI;

import chess.engine.Board.Board;
import chess.engine.Board.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class squarePanel extends JPanel {
    private static final Dimension SQUAREDIMENSION = new Dimension(10, 10);
    private final Coordinate squareLocation;
    private final Board board;
    private final boardPanel BoardPanel;
    squarePanel(final boardPanel BoardPanel, final Coordinate squareLocation, final Board board) {
        super(new GridBagLayout());
        this.BoardPanel = BoardPanel;
        this.board = board;
        this.squareLocation = squareLocation;
        setPreferredSize(SQUAREDIMENSION);
        setTileColour();
        addPieceIcon(board);

    }
    private void addPieceIcon(final Board board){
        this.removeAll();
        String piecePath = "C:\\Users\\Devon\\IdeaProjects\\chessGame\\PiecePictures\\";
        if (board.getTileAt(this.squareLocation).isOccupied())
        {
            try {
                final Image image = ImageIO.read(new File(piecePath + board.getTileAt(this.squareLocation).getPiece().getColour().toString().charAt(0) +
                        board.getTileAt(this.squareLocation).getPiece().toString() + ".png"));
                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void setTileColour() {
        if ((squareLocation.getRank()+1)%2 == 0)
        {
            setBackground((squareLocation.getFile() + 1)%2 == 0 ?Color.blue : Color.WHITE);
        }
        else
            setBackground((squareLocation.getFile() + 1)%2 != 0 ?Color.blue : Color.WHITE);
    }
}
