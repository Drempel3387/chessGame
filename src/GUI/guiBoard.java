package GUI;

import chess.engine.Board.Board;
import chess.engine.Board.Square;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class guiBoard extends JPanel {
    private final int RANKS = 8;
    private final int FILES = 8;
    private final mainFrame MainFrame;
    private final Board board;
    private Piece clickedOn;
    private final guiSquare[][] boardSquares = new guiSquare[RANKS][FILES];

    public guiBoard(Board board, mainFrame MainFrame)
    {
        this.board = board;
        this.MainFrame = MainFrame;
        setLayout(new GridLayout(RANKS, FILES));

        for (int rank = 0; rank < RANKS; rank++)
        {
            for (int file = 0; file < FILES; file++)
            {
                Colour squareColour = ((rank+file)%2 == 0?Colour.WHITE:Colour.BLACK);
                guiSquare square  = new guiSquare(new Coordinate(file ,  rank), board, MainFrame ,squareColour);
                boardSquares[rank][file] = square;
                add(square);
            }
        }
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    public void resetBoardColors() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                Colour squareColour = ((i+j)%2 == 0?Colour.WHITE:Colour.BLACK);
                boardSquares[i][j].setSquareColour(squareColour);
            }
        }
    }

    public void updateBoard()
    {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                boardSquares[i][j].addPieceIconToSquare();
                boardSquares[i][j].revalidate();
                boardSquares[i][j].repaint();
            }
        }
    }

    public guiSquare[][] getBoardSquares() {
        return boardSquares;
    }
    public Piece getClickedOn()
    {
        return clickedOn;
    }

    public void setClickedOn(Piece clickedOn)
    {
        this.clickedOn = clickedOn;
    }

    public Board getBackendBoard()
    {
        return board;
    }
}
