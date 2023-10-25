package chess.Board;
import chess.Game.Status;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Pieces.*;
import chess.Colour;
import chess.Coordinate;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Devon R.
 *
 * Represents the 8X8 collection of squares and collection of white and black pieces that make up a chess board.
 * The board will maintain lists of white + black pieces, an array of the squares contained within it, and final int's
 * that represent the rank/file coordinates of squares within the board. The board will be in charge of initialization,
 * resetting back to the starting position, printing a console representation, and providing access to lists of
 * white and black pieces.
 */
public class Board {
    /**
     * 2D array representing the squares of a chessboard.
     */
    private final Square[][] squares = new Square[RANKS][FILES];
    /**
     * List of all white pieces on the board
     */
    private final List<Piece> whitePieces = new ArrayList<>();
    /**
     * List of all black pieces on the board
     */
    private final List<Piece> blackPieces = new ArrayList<>();
    /**
     * White's king. Stored separately because it allows quick access when seeing if checkmate or stalemate is present
     */
    private King whiteKing;
    /**
     * Black's king. Stored separately because it allows quick access when seeing if checkmate or stalemate is present
     */
    private King blackKing;
    /**
     * Number of files on a chessboard. AKA "Columns"
     */
    public static final int FILES = 8;
    /**
     * Number of ranks on a chessboard. AKA "Rows"
     */
    public static final int RANKS = 8;
    /**
     * Rank and File number constants that are used when accessing a specific square within the squares array
     */
    public static final int FIRST = 7;
    public static int SECOND = 6;
    public static final int THIRD = 5;
    public static final int FOURTH = 4;
    public static final int FIFTH = 3;
    public static final int SIXTH = 2;
    public static final int SEVENTH = 1;
    public static final int EIGHTH = 0;

    /**
     * Will create a chessboard by initializing all squares, white pieces, and black pieces
     */
    public Board() {
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    /**
     * Will reset a chessboard to its original position by resetting the lists of pieces, and re-initializing the board.
     */
    public void reset() {
        // Clear the lists to remove any existing pieces
        whitePieces.clear();
        blackPieces.clear();
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    /**
     * Will initialize each square in the squares array to the correct rank and file, and set the occupying piece to null
     */
    private void initSquares() {
        for (int rank = 0; rank < RANKS; rank++)
            for (int file = 0; file < FILES; file++)
                squares[rank][file] = new Square(new Coordinate(file, RANKS - rank - 1), null);
            //initialize each squares in the array with the correct coordinate, and no Piece yet
    }//a square with a null piece signifies it is empty\

    /**
     * initializes all black pieces on the chessboard. Will place the correct number of pieces in their designated square
     * for a standardized chess board. This will also add each piece to the piece list.
     */
    private void initBlackPieces() {
        for (int i = 0; i < FILES; i++) {
            Pawn pawn = new Pawn(Colour.BLACK, new Coordinate(i, SEVENTH));
            squares[SEVENTH][i].setPiece(pawn);
            blackPieces.add(pawn);
        }//set all pieces in 7th rank to black pawns

        //Add both black rooks to the eighth rank
        Rook blackRook1 = new Rook(Colour.BLACK, new Coordinate(FIRST, EIGHTH));
        Rook blackRook2 = new Rook(Colour.BLACK, new Coordinate(EIGHTH, EIGHTH));
        squares[EIGHTH][FIRST].setPiece(blackRook1);
        squares[EIGHTH][EIGHTH].setPiece(blackRook2);
        blackPieces.add(blackRook1);
        blackPieces.add(blackRook2);

        //Add both black bishops to the eighth rank
        Bishop blackBishop1 = new Bishop(Colour.BLACK, new Coordinate(THIRD, EIGHTH));
        Bishop blackBishop2 = new Bishop(Colour.BLACK, new Coordinate(SIXTH, EIGHTH));
        squares[EIGHTH][THIRD].setPiece(blackBishop1);
        squares[EIGHTH][SIXTH].setPiece(blackBishop2);
        blackPieces.add(blackBishop1);
        blackPieces.add(blackBishop2);

        //Add both black knights to the eighth rank
        Knight blackKnight1 = new Knight(Colour.BLACK, new Coordinate(SECOND, EIGHTH));
        Knight blackKnight2 = new Knight(Colour.BLACK, new Coordinate(SEVENTH, EIGHTH));
        squares[EIGHTH][SECOND].setPiece(blackKnight1);
        squares[EIGHTH][SEVENTH].setPiece(blackKnight2);
        blackPieces.add(blackKnight1);
        blackPieces.add(blackKnight2);

        //Add the black king to the eighth rank
        blackKing = new King(Colour.BLACK, new Coordinate(FOURTH, EIGHTH));
        squares[EIGHTH][FOURTH].setPiece(blackKing);

        //Add the black queen to the eight rank
        Queen blackQueen = new Queen(Colour.BLACK, new Coordinate(FIFTH, EIGHTH));
        squares[EIGHTH][FIFTH].setPiece(blackQueen);
        blackPieces.add(blackQueen);
    }
    /**
     * initializes all white pieces on the chessboard. Will place the correct number of pieces in their designated square
     * for a standardized chess board. This will also add each piece to the piece list.
     */
    private void initWhitePieces() {
        for (int i = 0; i < FILES; i++) {
            Pawn pawn = new Pawn(Colour.WHITE, new Coordinate(i, SECOND));
            squares[SECOND][i].setPiece(pawn);
            whitePieces.add(pawn);
        }//Set all pieces in 2nd rank to white pawns

        //Add both white rooks to the first rank
        Rook whiteRook1 = new Rook(Colour.WHITE, new Coordinate(FIRST, FIRST));
        Rook whiteRook2 = new Rook(Colour.WHITE, new Coordinate(EIGHTH, FIRST));
        squares[FIRST][FIRST].setPiece(whiteRook1);
        squares[FIRST][EIGHTH].setPiece(whiteRook2);
        whitePieces.add(whiteRook1);
        whitePieces.add(whiteRook2);

        //Add both white bishops to the first rank
        Bishop whiteBishop1 = new Bishop(Colour.WHITE, new Coordinate(THIRD, FIRST));
        Bishop whiteBishop2 = new Bishop(Colour.WHITE, new Coordinate(SIXTH, FIRST));
        squares[FIRST][THIRD].setPiece(whiteBishop1);
        squares[FIRST][SIXTH].setPiece(whiteBishop2);
        whitePieces.add(whiteBishop1);
        whitePieces.add(whiteBishop2);

        //Add both white knights to the first rank
        Knight whiteKnight1 = new Knight(Colour.WHITE, new Coordinate(SECOND, FIRST));
        Knight whiteKnight2 = new Knight(Colour.WHITE, new Coordinate(SEVENTH, FIRST));
        squares[FIRST][SECOND].setPiece(whiteKnight1);
        squares[FIRST][SEVENTH].setPiece(whiteKnight2);
        whitePieces.add(whiteKnight1);
        whitePieces.add(whiteKnight2);

        //Add the white king to the first rank
        whiteKing = new King(Colour.WHITE, new Coordinate(FOURTH, FIRST));
        squares[FIRST][FOURTH].setPiece(whiteKing);

        //Add the white queen to the first rank
        Queen whiteQueen = new Queen(Colour.WHITE, new Coordinate(FIFTH, FIRST));
        squares[FIRST][FIFTH].setPiece(whiteQueen);
        whitePieces.add(whiteQueen);
    }

    /**
     * will return a square of a desired coordinate on a chessboard.
     * @param coordinate of the desired square
     * @return a square at the given coordinate
     */
    public Square getSquareAt(Coordinate coordinate) { return (squares[coordinate.getRank()][coordinate.getFile()]); }
    /**
     *Will return the 2D array of squares, AKA "the actual board"
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * used to print a chessboard to the console. Will print the toString value of a piece, or "E" to signify an empty\
     * square
     */
    public void print() {
        for (int i = 0; i < RANKS; i++) {
            System.out.print(" " + (RANKS - i));
            for (int j = 0; j < FILES; j++) {
                try { System.out.print(" " + squares[i][j].getPiece().toString()); }
                catch(NullPointerException e) { System.out.print(" E");}
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++)
            System.out.print(" " + (char)(Coordinate.STARTING_FILE + i));
        System.out.println();
    }

    /**
     * will return the list of white pieces relating to a board
     * @return whitePieces list of white pieces
     */
    public List<Piece> getWhitePieces() { return whitePieces; }
    /**
     * will return the list of black pieces relating to a board
     * @return blackPieces list of black pieces
     */
    public List<Piece> getBlackPieces() { return blackPieces; }

    /**
     * Will return the blackKing relating to a chessboard
     * @return blackKing the black king
     */
    public King getBlackKing() { return blackKing; }

    /**
     * Will return the whiteKing relating to a chessboard
     * @return whiteKing the white king
     */
    public King getWhiteKing() { return whiteKing; }
}
