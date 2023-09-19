package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Board.Moves.attackMove;
import chess.engine.Board.Moves.Move;
import chess.engine.Board.Moves.NormalMove;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;


public class Bishop extends Piece {
    public Bishop(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }
    public static final Coordinate[] POSSIBLEMOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1)
    };//possible moves for each diagonal (right, up) (right, down) (left up) (left down)
    @Override
    public List<Move> getLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        Coordinate possibleCoordinate;//candidate move

        for (Coordinate possibleMove: POSSIBLEMOVES)//for each possible diagonal
        {
            possibleCoordinate = this.coordinate.add(possibleMove);//move one square on the current diagonal
            while(possibleCoordinate.isValid())//if the coordinate is within the board
            {
                if (board.getTileAt(possibleCoordinate).getPiece() == null) //if no piece at the tile, this is a possible move
                {
                    legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                }
                else {//if there is a piece
                    if (this.colour != board.getTileAt(possibleCoordinate).getPiece().getColour())
                    {
                        legalMoves.add(new attackMove(board, this, board.getTileAt(possibleCoordinate).getPiece(), possibleCoordinate));
                    }//if not own colour, add the move, and don't look further. Bishop only has scope until it sees another piece (cannot move through other pieces)
                    break;
                }
                possibleCoordinate = possibleCoordinate.add(possibleMove);//move one square on the current diagonal
            }
        }
        return legalMoves;
    }//get a list of all legal moves for a bishop


    @Override
    public void print() {
            System.out.printf("%2c", 'B');
    }


}
