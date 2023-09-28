package chess.engine.Pieces;

import chess.engine.Board.Board;
import chess.engine.Moves.Move;
import chess.engine.Colour;
import chess.engine.Coordinate;
import chess.engine.Pieces.PieceMoveType.steppingPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends steppingPiece {
    private boolean hasKingMoved;
    private final Coordinate[] CASTLE_CHECK_MOVES = {
            new Coordinate(1, 0), new Coordinate(-1, 0)
    };
    private final Coordinate[] POSSIBLE_MOVES = {
            new Coordinate(1, 1), new Coordinate(1, -1),
            new Coordinate(-1, 1), new Coordinate(-1, -1),
            new Coordinate(1, 0), new Coordinate(0, 1),
            new Coordinate(-1, 0), new Coordinate(0, -1)
    };//possible moves for each diagonal, and straight direction
    public King(Colour colour, Coordinate coordinate) {
        super(colour, coordinate);
        hasKingMoved = false;
    }

    @Override
    public List<Move> getLegalMoves(Board board)//extension of base logic for stepping piece, must account for checks, castling, etc
    {
        List<Move> allLegalMoves = getPseudoLegalMoves(board, POSSIBLE_MOVES);
        List<Move> illegalMoves = new ArrayList<>();

        for (Move move: allLegalMoves)
        {
            if (isKingChecked(board))//if a king move would place the king into check, add to illegalMove list
                illegalMoves.add(move);
        }

        for (Move move: illegalMoves)
        {
            allLegalMoves.remove(move);
        }
        getCastleMoves(board, allLegalMoves);

        return allLegalMoves;
    }

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }

    private void getCastleMoves(Board board, List<Move> allLegalMoves)
    {
        if (hasKingMoved())//no reason to look for castle moves if the king has already moved
            return;

        Coordinate currentSquareCoord;
        for (Coordinate coordinate: CASTLE_CHECK_MOVES)
        {
            currentSquareCoord = this.coordinate.add(coordinate);
            while (currentSquareCoord.isValid())
            {
                if (board.getSquareAt(currentSquareCoord).isOccupied()) {
                    if (board.getSquareAt(currentSquareCoord).getPiece() instanceof Rook)
                        allLegalMoves.add(new Move(board, this, currentSquareCoord.add(coordinate.multiply(-1))));
                    /*the king will not be moving to the square the rook is on, instead it will be the square
                    before that one, so must step back one square before adding the move
                    */
                    else
                        break;
                    }
                else
                    if (isKingChecked(board))
                        break;
                currentSquareCoord = currentSquareCoord.add(coordinate);
            }

        }
    }

    public boolean isKingChecked(Board board)
    {
        List<Piece> pieces = (this.colour == Colour.WHITE? board.getBlackPieces(): board.getWhitePieces());
        for (Piece piece: pieces) {
            if (piece.canAttackSquare(board, this.coordinate))
                return true;
        }
        return false;
    }

    public boolean hasKingMoved()
    {
        return hasKingMoved;
    }

    public void setHasKingMoved(boolean hasKingMoved)
    {
        this.hasKingMoved = hasKingMoved;
    }

    @Override
    public String toString() {
        return "K";
    }

}
