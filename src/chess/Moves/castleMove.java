package chess.Moves;

import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;

public class castleMove extends Move {
    private final int ONE_SQUARE = 1;
    private final int ZERO = 0;
    public castleMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove() {
        board.getSquareAt(initialCoordinate()).setPiece(null);//set pieces at previous squares of rook and king to null
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(null);//captured piece represents the rook

        board.getSquareAt(endingCoordinate).setPiece(movingPiece);//moving piece represents the king
        movingPiece.setCoordinate(endingCoordinate);

        //if the distance between where the king lands and the rooks starting square is greater than one, this is queen-side
        //castling, otherwise king-side castling
        Coordinate rookEndingCoordinate;
        if (endingCoordinate.distance(capturedPiece.getCoordinate()) > ONE_SQUARE)
            rookEndingCoordinate = endingCoordinate.add(new Coordinate(1, 0));
        else
            rookEndingCoordinate = endingCoordinate.add(new Coordinate(-1, 0));

        board.getSquareAt(rookEndingCoordinate).setPiece(capturedPiece);
        capturedPiece.setCoordinate(rookEndingCoordinate);
    }

    @Override
    public void unMakeMove() {
        board.getSquareAt(getEndingCoordinate()).setPiece(null);//set the squares of where the king rook landed back to null
        board.getSquareAt(getEndingPiece().getCoordinate()).setPiece(null);

        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());//move the king back to starting square
        movingPiece.setCoordinate(initialCoordinate());

        if (initialCoordinate.getFile() - endingCoordinate.getFile() < ZERO)//negative is king-side castling
        {
            getEndingPiece().getCoordinate().setFile(Board.FIRST);
        }
        else
            getEndingPiece().getCoordinate().setFile(Board.EIGHTH);
        board.getSquareAt(getEndingPiece().getCoordinate()).setPiece(getEndingPiece());
    }
}
