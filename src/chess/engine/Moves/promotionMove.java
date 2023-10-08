package chess.engine.Moves;

import chess.engine.Board.Board;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.Piece;
import chess.engine.Pieces.Queen;

import java.util.List;

//dummy class for now, will simulate promotion by promoting to a queen
//will implement different piece promotion later
public class promotionMove extends Move
{
    public promotionMove(Board board, Piece movingPiece, Piece capturedPiece, Coordinate initialCoordinate, Coordinate endingCoordinate) {
        super(board, movingPiece, capturedPiece, initialCoordinate, endingCoordinate);
    }

    @Override
    public void makeMove()
    {
    }

    @Override
    public void unMakeMove()
    {

    }

}
