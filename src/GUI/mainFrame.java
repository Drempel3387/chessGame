package GUI;

import chess.Board.Board;
import chess.Board.Status;
import chess.Colour;
import chess.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class mainFrame extends JFrame {
    private final Dimension FRAME_SIZE = new Dimension(800, 800);
    private final leftPanel left;
    private final rightPanel right;
    private final topPanel top;
    private final bottomPanel bottom;
    private final guiBoard GuiBoard;
    public mainFrame(Board board)
    {
        setSize(FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setLayout(new BorderLayout());
        GuiBoard = new guiBoard(board, this);

        left = new leftPanel();
        right = new rightPanel();
        top = new topPanel(GuiBoard);
        bottom = new bottomPanel();


        add(GuiBoard, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);


        setVisible(true);
    }
    public class rightPanel extends JPanel {
        private final JPanel topPanel;
        private final JPanel bottomPanel;

        //this panel will keep track of pieces that have been captured
        public rightPanel()
        {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            topPanel = new JPanel(new GridLayout(8, 2));
            bottomPanel = new JPanel(new GridLayout(8, 2));
            topPanel.setBackground(Color.decode( "#F5F5F5"));
            bottomPanel.setBackground(Color.decode( "#F5F5F5"));
            topPanel.setPreferredSize(new Dimension(30, 30));
            bottomPanel.setPreferredSize(new Dimension(30, 30));
            setBackground(Color.decode( "#F5F5F5"));
            setPreferredSize(new Dimension(150, 150));
            setBorder(BorderFactory.createLoweredBevelBorder());

            JPanel gapPanel = new JPanel();
            gapPanel.setPreferredSize(new Dimension(60, 60)); // Set the desired gap size

            add(topPanel, BorderLayout.NORTH);
            add(gapPanel, BorderLayout.CENTER); // Add the gapPanel to the center
            add(bottomPanel, BorderLayout.SOUTH);
        }

        public void addPieceIconToCaptured(Piece piece, JPanel panel)
        {
            String filePath = "C:\\Users\\Devon\\IdeaProjects\\chessGame\\PiecePictures\\";

            if (piece.getColour() == Colour.WHITE)
                filePath += "W";
            else
                filePath += "B";

            filePath += piece.toString();
            filePath += ".png";
            BufferedImage myImage;
            try {
                myImage = ImageIO.read(new File(filePath));

                // Resize the image
                Image resizedImage = myImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);
                JLabel pieceImage = new JLabel(icon);
                panel.add(pieceImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void addIcons(List<Piece> Pieces, JPanel panel)
        {
            panel.removeAll();
            for (Piece piece: Pieces)
            {
                if (!piece.getIsAlive())
                {
                    addPieceIconToCaptured(piece, panel);
                }
            }
            panel.revalidate();
            panel.repaint();
        }

        public void updateRightPanel(Board board)
        {
            addIcons(board.getWhitePieces(), topPanel);
            addIcons(board.getBlackPieces(), bottomPanel);
        }
    }
    public class bottomPanel extends JPanel {
        //will keep track of the current status
        private Status status;
        private final JLabel currentStatus;
        private final String[] possibleStatus = {"Black Win", "White Win", "Active", "Stalemate"};
        public bottomPanel()
        {
            status = Status.ACTIVE;
            currentStatus = new JLabel();
            currentStatus.setText("Current game status: " + possibleStatus[status.ordinal()]);

            setBackground(Color.decode( "#F5F5F5"));
            setPreferredSize(new Dimension(150, 150));
            setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }

    public class topPanel extends JPanel {
        private final guiBoard board;
        public topPanel(guiBoard board)
        {
            this.board = board;
            setBackground(Color.decode( "#F5F5F5"));
            setPreferredSize(new Dimension(50, 50));
            add(getMenuBar("MENU"));
        }
        JMenuBar getMenuBar(String title)
        {
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(getMenu(title));
            return menuBar;
        }

        JMenu getMenu(String title)
        {
            JMenu menu = new JMenu(title);
            menu.add(makeExitItem());
            menu.add(makeResetItem());
            return menu;
        }
        JMenuItem makeExitItem()
        {
            JMenuItem exitItem = new JMenuItem("Exit App");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            return exitItem;
        }
        JMenuItem makeResetItem()
        {
            JMenuItem exitItem = new JMenuItem("Reset Board");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.getBackendBoard().reset();
                    board.updateBoard();
                    right.updateRightPanel(board.getBackendBoard());
                }
            });
            return exitItem;
        }


    }

    public class leftPanel extends JPanel {
        public leftPanel()
        {
            setBackground(Color.decode( "#F5F5F5"));
            setPreferredSize(new Dimension(50, 50));
            setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }

    public leftPanel getLeft() {
        return left;
    }

    public bottomPanel getBottom() {
        return bottom;
    }

    public rightPanel getRight() {
        return right;
    }

    public topPanel getTop() {
        return top;
    }

    public guiBoard getGuiBoard() {
        return GuiBoard;
    }
}
