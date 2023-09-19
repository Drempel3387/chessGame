package chess.engine.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.*;

public class Board {
    //the actual "chess board" 8x8 array of chessTiles
    private chessTile[][] tiles = new chessTile[RANKS][FILES];

    //number of ranks and files
    public static final int FILES = 8;
    public static final int RANKS = 8;

    //rank and file numbers for the board
    public static final int FIRST = 0;
    public static int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    public static final int SIXTH = 5;
    public static final int SEVENTH = 6;
    public static final int EIGHTH = 7;

    public Board()
    {
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    private void initSquares()
    {
        for (int rank = FIRST; rank < RANKS; rank++)
        {
            for (int file = FIRST; file < FILES; file++)
            {
                tiles[rank][file] = new chessTile(new Coordinate(file, rank), null);
            }
        }
    }
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

        //add the black king to the eight rank
        tiles[EIGHTH][FIFTH].setPiece(new King(Colour.BLACK,  new Coordinate(FIFTH, EIGHTH)));

        //add the black queen to the eight rank
        tiles[EIGHTH][FOURTH].setPiece(new Queen(Colour.BLACK,  new Coordinate(FOURTH, EIGHTH)));
    }
    private void initWhitePieces()//initial all the white pieces
    {
        for (int i = 0; i < FILES; i++)
        {
            tiles[SECOND][i].setPiece(new Pawn(Colour.WHITE,  new Coordinate(i, SEVENTH)));
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
    public void print()//print the board to the console window (debug use mainly)
    {
        for (int rank = FIRST; rank < RANKS; rank++)
        {
            for (int file = FIRST; file < FILES; file++)
            {
                try
                {
                    tiles[rank][file].getPiece().print();
                }
                catch(NullPointerException e) //Squares without pieces will be null, print "E" to signify empty
                {
                    System.out.printf("%2c", 'E');
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public chessTile getTileAt(Coordinate coordinate)
    {
        return (tiles[coordinate.getRank()][coordinate.getFile()]);
    }

    public chessTile[][] getTiles() {
        return tiles;
    }
}
