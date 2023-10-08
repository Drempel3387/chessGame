package GUI;


import chess.engine.Board.Board;
import chess.engine.Board.Status;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class guiSquare extends JPanel {
    private final Coordinate coordinate;//coordinate associated with the square
    private final Board board;
    private final mainFrame MainFrame;
    private final Dimension SQUARE_SIZE = new Dimension(20, 20);//size of the square
    private final Colour colour;

    public guiSquare(Coordinate coordinate, Board board, mainFrame MainFrame, Colour colour)
    {
        this.coordinate = coordinate;
        this.board = board;
        this.colour = colour;
        this.MainFrame = MainFrame;

        setSquareColour(colour);
        setLayout(new GridBagLayout());
        setPreferredSize(SQUARE_SIZE);
        addPieceIconToSquare();
        addMoveListener();

    }

    void addMoveListener()
    {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (board.getSquareAt(coordinate).isOccupied() && MainFrame.getGuiBoard().getClickedOn() == null)
                {
                    Piece clickedPiece = board.getSquareAt(coordinate).getPiece();
                    if (clickedPiece.getColour() != board.getCurrentPlayerColour())
                        return;
                    MainFrame.getGuiBoard().setClickedOn(clickedPiece);
                    highlightLegalMoves(clickedPiece);
                }
                else if (MainFrame.getGuiBoard().getClickedOn() != null)
                {
                    Colour colour = board.getCurrentPlayerColour();
                    Status status = Status.ACTIVE;
                    for (Move move: MainFrame.getGuiBoard().getClickedOn().getLegalMoves(board)) {
                        if (move.getEndingCoordinate().areEqual(coordinate)) {
                            status = colour == Colour.WHITE? board.whiteTurn(move):board.blackTurn(move);
                        }
                    }
                    MainFrame.getGuiBoard().resetBoardColors();
                    MainFrame.getGuiBoard().setClickedOn(null);
                    board.setStatus(status);
                    MainFrame.getLeft().updateLeftPanel(board);
                }
                MainFrame.getGuiBoard().updateBoard();
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    // ... (existing methods remain unchanged)

    void highlightLegalMoves(Piece piece)
    {
        for (Move move: piece.getLegalMoves(board))
        {
            MainFrame.getGuiBoard().getBoardSquares()[move.getEndingCoordinate().getRank()][move.getEndingCoordinate().getFile()].setBackground(Color.red);
        }//highlight the legal move squares
    }

    public void setSquareColour(Colour colour)
    {
        if (colour == Colour.WHITE)
            setBackground(Color.WHITE);
        else
            setBackground(Color.decode("#4682B4"));
    }

    public void addPieceIconToSquare()
    {
        removeAll();
        if (!board.getSquareAt(getCoordinate()).isOccupied())
            return;

        String filePath = "C:\\Users\\Devon\\IdeaProjects\\chessGame\\PiecePictures\\";

        if (board.getSquareAt(getCoordinate()).getPiece().getColour() == Colour.WHITE)
            filePath += "W";
        else
            filePath += "B";

        filePath += board.getSquareAt(getCoordinate()).getPiece().toString();
        filePath += ".png";
        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel pieceImage = new JLabel(new ImageIcon(myImage));
        add(pieceImage);
    }
    Coordinate getCoordinate()
    {
        return coordinate;
    }

}
