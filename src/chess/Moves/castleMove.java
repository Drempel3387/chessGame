package chess.Moves;

import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;
/**
 * @author Devon R.
 *
 * Specialization of the Move class. A castleMove represents a king being castled in a chessGame. Castling involves a king
 * which has not been moved, an unmoved rook, and an empty space between the king and rook. This may take place on the
 * kingside(side of board which the king is closest to), or the queenside(side of board which the queen is closest to).
 */
public class castleMove extends Move {
    /**
     * Create a castleMove
     * @param board board which the move is played
     * @param movingPiece piece that is moving
     * @param capturedPiece captured piece(if any)
     * @param initialCoordinate initial coordinate of the movingPiece
     * @param endingCoordinate ending coordinate of the moving piece
     */
    public castleMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    /**
     * Used to make a castling move on a chessboard
     */
    @Override
    public void makeMove() {
        board.getSquareAt(initialCoordinate()).setPiece(null);//set pieces at previous squares of rook and king to null
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(null);//captured piece represents the rook

        //move the king to it's ending position
        board.getSquareAt(endingCoordinate).setPiece(movingPiece);//moving piece represents the king
        movingPiece.setCoordinate(endingCoordinate);


        Coordinate rookEndingCoordinate;
        //if the ending file is less than the fourth, this is queen-side
        if (endingCoordinate.getFile() < Board.FOURTH)
            rookEndingCoordinate = endingCoordinate.add(new Coordinate(1, 0));
        else
            rookEndingCoordinate = endingCoordinate.add(new Coordinate(-1, 0));

        //move the rook to it's ending position
        board.getSquareAt(rookEndingCoordinate).setPiece(capturedPiece);
        capturedPiece.setCoordinate(rookEndingCoordinate);
    }

    /**
     * Used to unMake a castling move on a chessboard
     */
    @Override
    public void unMakeMove() {
        board.getSquareAt(getEndingCoordinate()).setPiece(null);//set the squares of where the king rook landed back to null
        board.getSquareAt(getCapturedPiece().getCoordinate()).setPiece(null);

        //move the king back to its starting square
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());
        movingPiece.setCoordinate(initialCoordinate());

        //move the rook back to its starting square
        if (endingCoordinate.getFile() < Board.FOURTH)
            getCapturedPiece().setCoordinate(new Coordinate(Board.EIGHTH, getCapturedPiece().getCoordinate().getRank()));
        else
            getCapturedPiece().setCoordinate(new Coordinate(Board.FIRST, getCapturedPiece().getCoordinate().getRank()));
        board.getSquareAt(getCapturedPiece().getCoordinate()).setPiece(getCapturedPiece());
    }

    /**
     * Will return the correct string representation for king-side and queen-side castling
     * @return string representing the move
     */
    public String toString()
    {
        if (initialCoordinate.getFile() - endingCoordinate.getFile() < 0)
            return "O-O";
        return "O-O-O";
    }
}
