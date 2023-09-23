package chess.engine.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.*;
import chess.engine.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    //the actual "chess board" 8x8 array of Squares
    private final Square[][] tiles = new Square[RANKS][FILES];

    //number of ranks and files
    public static final int FILES = 8;
    public static final int RANKS = 8;

    //rank and file numbers for the board
    public static final int FIRST = 7;
    public static int SECOND = 6;
    public static final int THIRD = 5;
    public static final int FOURTH = 4;
    public static final int FIFTH = 3;
    public static final int SIXTH = 2;
    public static final int SEVENTH = 1;
    public static final int EIGHTH = 0;

    public Board()
    {
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    private void initSquares()
    {
        for (int rank = 0; rank < RANKS; rank++)
        {
            for (int file = 0; file < FILES; file++)
            {
                tiles[rank][file] = new Square(new Coordinate(file, RANKS - rank - 1), null);
            }//initialize each squares in the array with the correct coordinate, and no Piece yet
        }
    }//a square with a null piece signifies it is empty
    private void initBlackPieces()//initialize all the black pieces
    {
       for (int i = 0; i < FILES; i++)
       {
           tiles[SEVENTH][i].setPiece(new Pawn(Colour.BLACK,  new Coordinate(i , SEVENTH)));
       }//set all pieces in 7th rank to black pawns

        //add both black rooks to the eighth rank
        tiles[EIGHTH][FIRST].setPiece(new Rook(Colour.BLACK,  new Coordinate(FIRST, EIGHTH)));
        tiles[EIGHTH][EIGHTH].setPiece(new Rook(Colour.BLACK,  new Coordinate(EIGHTH, EIGHTH)));

        //add both black bishops to the eighth rank
        tiles[EIGHTH][THIRD].setPiece(new Bishop(Colour.BLACK,  new Coordinate(THIRD, EIGHTH)));
        tiles[EIGHTH][SIXTH].setPiece(new Bishop(Colour.BLACK,  new Coordinate(SIXTH, EIGHTH)));

        //add both black knights to the eighth rank
        tiles[EIGHTH][SECOND].setPiece(new Knight(Colour.BLACK,  new Coordinate(SECOND, EIGHTH)));
        tiles[EIGHTH][SEVENTH].setPiece(new Knight(Colour.BLACK, new Coordinate(SEVENTH, EIGHTH)));

        //add the black king to the eighth rank
        tiles[EIGHTH][FIFTH].setPiece(new King(Colour.BLACK,  new Coordinate(FIFTH, EIGHTH)));

        //add the black queen to the eight rank
        tiles[EIGHTH][FOURTH].setPiece(new Queen(Colour.BLACK,  new Coordinate(FOURTH, EIGHTH)));
    }
    private void initWhitePieces()//initial all the white pieces
    {
        for (int i = 0; i < FILES; i++)
        {
            tiles[SECOND][i].setPiece(new Pawn(Colour.WHITE,  new Coordinate(i, SECOND)));
        }//set all pieces in 2nd rank to black pawns


        //add both black rooks to the eighth rank
        tiles[FIRST][FIRST].setPiece(new Rook(Colour.WHITE,  new Coordinate(FIRST, FIRST)));
        tiles[FIRST][EIGHTH].setPiece(new Rook(Colour.WHITE,  new Coordinate(EIGHTH, FIRST)));

        //add both black bishops to the eighth rank
        tiles[FIRST][THIRD].setPiece(new Bishop(Colour.WHITE,  new Coordinate(THIRD, FIRST)));
        tiles[FIRST][SIXTH].setPiece(new Bishop(Colour.WHITE,  new Coordinate(SIXTH, FIRST)));

        //add both black knights to the eighth rank
        tiles[FIRST][SECOND].setPiece(new Knight(Colour.WHITE,  new Coordinate(SECOND, FIRST)));
        tiles[FIRST][SEVENTH].setPiece(new Knight(Colour.WHITE, new Coordinate(SEVENTH, FIRST)));

        //add the black king to the eight rank
        tiles[FIRST][FIFTH].setPiece(new King(Colour.WHITE,  new Coordinate(FIFTH, FIRST)));

        //add the black queen to the eight rank
        tiles[FIRST][FOURTH].setPiece(new Queen(Colour.WHITE,  new Coordinate(FOURTH, FIRST)));
    }

    public Square getTileAt(Coordinate coordinate)
    {
        return (tiles[coordinate.getRank()][coordinate.getFile()]);
    }//return the tile at a specific coordinate

    public Square[][] getTiles() {
        return tiles;
    }//return the entire board

    public void print()
    {
        for (int i = 0; i < RANKS; i++)
        {
            System.out.print(" " + (RANKS - i));
            for (int j = 0; j < FILES; j++)
            {
                try {
                    System.out.print(" " + tiles[i][j].getPiece().toString());
                }
                catch(NullPointerException e)
                {
                    System.out.print(" E");
                }

            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++)
        {
            System.out.print(" " + (char)(Coordinate.STARTING_FILE + i));
        }
        System.out.println();
    }//print the entire board to the console


}
