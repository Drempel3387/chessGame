package chess.engine.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Moves.Move;
import chess.engine.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    //the actual "chess board" 8x8 array of Squares
    private final Square[][] squares = new Square[RANKS][FILES];

    //lists of the pieces for white and black
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    //black and white king
    private King whiteKing;
    private King blackKing;

    //status of the current board (checkmate? stalemate?, active?)
    private Status status;

    //is it currently whites turn to play?
    private Colour currentPlayerColour;

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
        currentPlayerColour = Colour.WHITE;
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    public void reset() {
        currentPlayerColour = Colour.WHITE;
        // Clear the lists to remove any existing pieces
        whitePieces.clear();
        blackPieces.clear();
        initSquares();
        initBlackPieces();
        initWhitePieces();
        status = Status.ACTIVE;
    }
    private void initSquares()
    {
        for (int rank = 0; rank < RANKS; rank++)
        {
            for (int file = 0; file < FILES; file++)
            {
                squares[rank][file] = new Square(new Coordinate(file, RANKS - rank - 1), null);
            }//initialize each squares in the array with the correct coordinate, and no Piece yet
        }
    }//a square with a null piece signifies it is empty
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

    public Square getSquareAt(Coordinate coordinate)
    {
        return (squares[coordinate.getRank()][coordinate.getFile()]);
    }//return the tile at a specific coordinate

    public Square[][] getSquares() {
        return squares;
    }//return the entire board

    public void print()
    {
        for (int i = 0; i < RANKS; i++)
        {
            System.out.print(" " + (RANKS - i));
            for (int j = 0; j < FILES; j++)
            {
                try {
                    System.out.print(" " + squares[i][j].getPiece().toString());
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

    public List<Piece> getWhitePieces()
    {
        return whitePieces;
    }

    public List<Piece> getBlackPieces()
    {
        return blackPieces;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public Status getStatus() {return status;}
    public void updateStatus(Status status) {this.status = status;}

    public Colour getCurrentPlayerColour()
    {
        return currentPlayerColour;
    }

    public void setCurrentPlayerColour(Colour currentPlayerColour)
    {
        this.currentPlayerColour = currentPlayerColour;
    }


    private List<Move> preventCheckMoves(King king, Colour side) {
        List<Piece> pieces = side == Colour.WHITE ? getWhitePieces() : getBlackPieces();
        List<Move> movesToPreventCheck = new ArrayList<>();

        for (Piece piece : pieces) {
            if (!piece.getIsAlive())//if piece is not alive, skip
                continue;
            for (Move move : piece.getLegalMoves(this)) {
                move.makeMove();
                if (!king.isKingChecked(this)) {
                    movesToPreventCheck.add(move);
                }
                move.unMakeMove();
            }//if a move will stop the check, add to the list
        }//any moves which are not king moves

        movesToPreventCheck.addAll(king.getLegalMoves(this));//add any king moves which avoid the check

        return movesToPreventCheck;
    }

    public Status whiteTurn(Move whiteMove) {
        whiteMove.makeMove();
        setCurrentPlayerColour(Colour.BLACK);

        if (isCheckMate(Colour.WHITE))
            return Status.WHITE_WIN;
        if (isStaleMate(Colour.WHITE))
            return Status.STALEMATE;
        return Status.ACTIVE;
    }

    public Status blackTurn(Move blackMove)
    {
        blackMove.makeMove();
        setCurrentPlayerColour(Colour.WHITE);

        if (isCheckMate(Colour.BLACK))
            return Status.BLACK_WIN;
        if (isStaleMate(Colour.BLACK))
            return Status.STALEMATE;
        return Status.ACTIVE;
    }

    public boolean isCheckMate(Colour side)
    {
        King king = (side == Colour.WHITE? getWhiteKing(): getBlackKing());
        if (!king.isKingChecked(this))//if the king is not checked
        {
            return false;
        }
        return (preventCheckMoves(king, side).isEmpty());//no moves to prevent the check, so checkmate
    }
    public boolean isStaleMate(Colour side)
    {
        King king = (side == Colour.WHITE? getWhiteKing(): getBlackKing());
        if (king.isKingChecked(this))//if the king is checked, it is not a stalemate
            return false;
        if (!king.getLegalMoves(this).isEmpty())//if the king does have moves, not a stalemate
        {
            return false;
        }
        return noMoreMoves(side, king);//if there are no more moves on the board, stalemate
    }

    private boolean noMoreMoves(Colour side, King king) {
        List<Piece> pieceList = (side == Colour.WHITE? getWhitePieces(): getBlackPieces());
        //get list of allied pieces
        for (Piece piece: pieceList)
        {
            if (!piece.getLegalMoves(this).isEmpty())
                return false;
        }
        return true;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
