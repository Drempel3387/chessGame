package GUI;


import chess.Board.Board;
import chess.Game.Game;
import chess.Game.Status;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.Move;
import chess.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @author Devon R.
 */
public class guiSquare extends JPanel {
    private final Coordinate coordinate;//coordinate associated with the square
    private final Game game;
    private final mainFrame MainFrame;
    private final Dimension SQUARE_SIZE = new Dimension(20, 20);//size of the square
    private final Colour colour;

    public guiSquare(Coordinate coordinate, Game game, mainFrame MainFrame, Colour colour)
    {
        this.coordinate = coordinate;
        this.game = game;
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
                if (game.getBoard().getSquareAt(coordinate).isOccupied() && MainFrame.getGuiBoard().getClickedOn() == null)
                {
                    Piece clickedPiece = game.getBoard().getSquareAt(coordinate).getPiece();
                    if (clickedPiece.getColour() != game.getCurrentPlayerColour())
                        return;
                    MainFrame.getGuiBoard().setClickedOn(clickedPiece);
                    highlightLegalMoves(clickedPiece);
                }
                else if (MainFrame.getGuiBoard().getClickedOn() != null)
                {
                    Colour colour = game.getCurrentPlayerColour();
                    Status status = Status.ACTIVE;
                    for (Move move: MainFrame.getGuiBoard().getClickedOn().getLegalMoves(game)) {
                        if (move.getEndingCoordinate().areEqual(coordinate)) {
                            status = (colour == Colour.WHITE? game.whiteTurn(move):game.blackTurn(move));
                            MainFrame.getRight().getListPanel().addMove(move);
                            break;
                        }
                    }
                    MainFrame.getGuiBoard().updateBoard();
                    MainFrame.getGuiBoard().setClickedOn(null);
                    game.setStatus(status);
                    MainFrame.getBottom().setStatus(status);

                    MainFrame.getRight().updateRightPanel(game.getBoard());
                    MainFrame.getBottom().updateBottomPanel();

                }

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


    void highlightLegalMoves(Piece piece)
    {
        for (Move move: piece.getLegalMoves(game))
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
    public void addPieceIconToSquare() {
        removeAll();

        // if square empty → no piece → no image
        if (!game.getBoard().getSquareAt(getCoordinate()).isOccupied()) {
            return;
        }

        Piece piece = game.getBoard().getSquareAt(getCoordinate()).getPiece();

        // build classpath resource path (NOT filesystem path)
        StringBuilder path = new StringBuilder("/GUI/PiecePictures/");
        path.append(piece.getColour() == Colour.WHITE ? "W" : "B");
        path.append(piece.toString()).append(".png");

        try {
            // load from classpath
            var imageUrl = getClass().getResource(path.toString());
            if (imageUrl == null) {
                throw new IOException("Image resource not found: " + path);
            }

            BufferedImage img = ImageIO.read(imageUrl);

            // resize to fit square
            Image scaled = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaled));
            add(label);

        } catch (IOException e) {
            System.err.println("Failed to load piece image: " + path);
            e.printStackTrace();
        }
    }

    Coordinate getCoordinate()
    {
        return coordinate;
    }

}
