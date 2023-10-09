package chess.Pieces;

import chess.Board.Board;
import chess.Moves.Move;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.castleMove;
import chess.Pieces.PieceMoveType.steppingPiece;

import java.util.List;


public class King extends steppingPiece {
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
    }

    @Override
    public List<Move> getLegalMoves(Board board)//extension of base logic for stepping piece, must account for checks, castling, etc
    {
        List<Move> legalMoves = getPseudoLegalMoves(board, POSSIBLE_MOVES);
        getCastleMoves(board, legalMoves);

        return legalMoves;
    }

    @Override
    public boolean canAttackSquare(Board board, Coordinate squarePosition) {
        return steppingPieceCanAttackSquare(board, squarePosition, POSSIBLE_MOVES);
    }

    private void getCastleMoves(Board board, List<Move> allLegalMoves)
    {
        if (getHasMoved())//no reason to look for castle moves if the king has already moved
            return;
        Coordinate currentSquareCoord, startingCoordinate = this.coordinate;
        for (Coordinate coordinate: CASTLE_CHECK_MOVES)
        {
            this.coordinate = startingCoordinate;
            currentSquareCoord = this.coordinate.add(coordinate);
            while (currentSquareCoord.isValid())
            {
                this.coordinate = currentSquareCoord;
                if (board.getSquareAt(currentSquareCoord).isOccupied()) {
                    Piece piece = board.getSquareAt(currentSquareCoord).getPiece();
                    if (piece instanceof Rook && !piece.getHasMoved())
                    {
                        if (coordinate.areEqual(new Coordinate(1, 0)))
                            allLegalMoves.add(new castleMove(board, this, piece,startingCoordinate,currentSquareCoord.add(coordinate.multiply(-1))));
                        else
                            allLegalMoves.add(new castleMove(board, this, piece,startingCoordinate,currentSquareCoord.add(coordinate.multiply(-2))));
                    }
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
        this.coordinate = startingCoordinate;
    }

    public boolean isKingChecked(Board board)
    {
        List<Piece> pieces = (this.colour == Colour.WHITE? board.getBlackPieces(): board.getWhitePieces());
        for (Piece piece: pieces) {
            if (!piece.isAlive)
                continue;
            if (piece.canAttackSquare(board, this.coordinate))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "K";
    }

}
