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
    public void makeMove() {
        if (isCapture())
            getEndingPiece().setIsAlive(false);//the captured piece is now "dead"
        board.getSquareAt(initialCoordinate()).setPiece(null);//square is now empty, null to signify
        movingPiece.setIsAlive(false);//The capturing pawn is now "dead" also given that it is turning into a queen

        board.getSquareAt(endingCoordinate).setPiece(promotedPiece);//add the queen to that square
        List<Piece> pieces = movingPiece.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces();//get the list of white or black pieces based on the moving piece
        pieces.add(promotedPiece);//add queen to the list of pieces
    }

    @Override
    public void unMakeMove() {
        List<Piece> pieces = movingPiece.getColour() == Colour.WHITE? board.getWhitePieces(): board.getBlackPieces();
        pieces.remove(promotedPiece);//remove the queen from the list of active pieces
        if (isCapture())
            getEndingPiece().setIsAlive(true);//set status of captured piece back to alive

        board.getSquareAt(getEndingCoordinate()).setPiece(getEndingPiece());//place the captured piece back on the board
        board.getSquareAt(initialCoordinate()).setPiece(getMovingPiece());//place the moving piece back to its original square
        getMovingPiece().setCoordinate(initialCoordinate());//reset the pawns coordinate
        getMovingPiece().setIsAlive(true);//set the piece back to alive
    }

}
