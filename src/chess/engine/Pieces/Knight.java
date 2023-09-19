package chess.engine.Pieces;

import chess.engine.Board.Moves.attackMove;
import chess.engine.Board.Moves.Move;
import chess.engine.Board.Board;
import chess.engine.Board.Moves.NormalMove;
import chess.engine.Colour;
import chess.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;
public class Knight extends Piece {

    public static final Coordinate[] POSSIBLEMOVES = {
            new Coordinate(1,2),new Coordinate(-1, 2) , new Coordinate(1,-2),
            new Coordinate(-1,-2), new Coordinate(2, 1), new Coordinate(2, -1),
            new Coordinate(-2, 1), new Coordinate(-2, -1)
    };//possible knight jump coordinates
    public Knight(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        Coordinate possibleCoordinate;//candidate move
        List<Move> legalMoves = new ArrayList<>();//list of all legal moves

        for (Coordinate possibleMove : POSSIBLEMOVES) {
            possibleCoordinate = this.coordinate.add(possibleMove);//see if the current coordinate + a move is valid
            if (possibleCoordinate.isValid()) {
                if (board.getTileAt(possibleCoordinate).getPiece() == null)//if tile not occupied
                {
                    legalMoves.add(new NormalMove(board, this, possibleCoordinate));
                }
                else//if occupied
                {
                    Piece destinationPiece = board.getTileAt(possibleCoordinate).getPiece();
                    if (destinationPiece.getColour() != this.colour)//if the colour of the piece at the destination is not the current pieces colour
                        legalMoves.add(new attackMove(board, this, destinationPiece, possibleCoordinate));
                }
            }
        }
        return legalMoves;
    }


    @Override
    public void print() {
        System.out.printf("%2s", "Kn");
    }
}
