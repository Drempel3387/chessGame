package GUI;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class guiSquare extends JPanel {
    private Coordinate coordinate;//coordinate associated with the square
    private Board board;//board which contains the square
    private final Dimension SQUARE_SIZE = new Dimension(20, 20);//size of the square

    private final Colour colour;

    public guiSquare(Coordinate coordinate, Board board, Colour colour)
    {
        this.coordinate = coordinate;
        this.board = board;
        this.colour = colour;

        if (colour == Colour.WHITE)
            setBackground(Color.WHITE);
        else
            setBackground(Color.decode("#4682B4"));

        setLayout(new GridBagLayout());
        setPreferredSize(SQUARE_SIZE);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }


    void addPieceIconToSquare()
    {

    }
}
