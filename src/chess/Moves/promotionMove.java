package chess.Moves;

import chess.Board.Board;
import chess.Colour;
import chess.Pieces.Piece;
import chess.Coordinate;
import chess.Pieces.Queen;

import java.util.List;

//dummy class for now, will simulate promotion by promoting to a queen
//will implement different piece promotion later
public class promotionMove extends Move
{
    final Piece promotedPiece;
    public promotionMove(Board board, Piece movingPiece, Piece capturedPiece, Piece promotedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
        this.promotedPiece = promotedPiece;
    }

    @Override
    public void makeMove()
    {
        if (isCapture())
        {
            getEndingPiece().setIsAlive(false);
        }
        board.getSquareAt(initialCoordinate()).setPiece(null);//square is now empty, null to signify
        movingPiece.setIsAlive(false);

        board.getSquareAt(endingCoordinate).setPiece(promotedPiece);
        List<Piece> pieces = movingPiece.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces();
        pieces.add(promotedPiece);
    }

    @Override
    public void unMakeMove()
    {
        List<Piece> pieces = movingPiece.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces();
        pieces.remove(promotedPiece);//remove the queen from the list of active pieces
        if (isCapture())
            getEndingPiece().setIsAlive(true);//set status back to alive

        board.getSquareAt(getEndingCoordinate()).setPiece(getEndingPiece());//place the captured piece back on the board
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());
        getMovingPiece().setCoordinate(initialCoordinate());
        getMovingPiece().setIsAlive(true);
    }

}
