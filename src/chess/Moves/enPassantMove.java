package chess.Moves;

import chess.Board.Board;
import chess.Pieces.Piece;
import chess.Coordinate;
/**
 * @author Devon R.
 *
 * Specialization of the Move class. Represents an enPassant chessmove. An enPassantMove requires that an enemy pawn
 * has moved 2 squares from its starting position and lands beside a differently coloured pawn. This differently
 * coloured pawn now has the ability to capture this pawn that just moved two squares(only for it's next move)
 */
public class enPassantMove extends Move {
    /**
     * Create an enPassantMove
     * @param board board which the move is played
     * @param movingPiece piece that is moving
     * @param capturedPiece captured piece(if any)
     * @param initialCoordinate initial coordinate of the movingPiece
     * @param endingCoordinate ending coordinate of the moving piece
     */
    public enPassantMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }
    /**
     * Used to make an enPassantMove on a chessboard
     */
    @Override
    public void makeMove() {
        //empty the squares of the pawn being captured and the capturing pawn
        capturedPiece.setIsAlive(false);
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(null);
        board.getSquareAt(initialCoordinate).setPiece(null);

        //move the pawn to correct en passant square
        board.getSquareAt(endingCoordinate).setPiece(movingPiece);
        movingPiece.setCoordinate(endingCoordinate);
    }
    /**
     * Used to unMake an enPassantMove on a chessboard
     */
    @Override
    public void unMakeMove() {
        //place capturing pawn back on it's starting square
        board.getSquareAt(movingPiece.getCoordinate()).setPiece(null);
        board.getSquareAt(initialCoordinate).setPiece(movingPiece);
        movingPiece.setCoordinate(initialCoordinate);

        //place captured pawn back on the board
        board.getSquareAt(capturedPiece.getCoordinate()).setPiece(capturedPiece);
        capturedPiece.setIsAlive(true);
    }
}
