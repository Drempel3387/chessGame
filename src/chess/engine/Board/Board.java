package chess.engine.Board;
import chess.engine.Board.chessTile.*;

public class Board {
    private chessTile[][] tiles = new chessTile[Coordinate.MAXCOORDINATE][Coordinate.MAXCOORDINATE];

    public Board()
    {
        initSquares();
        initBlackPieces();
        initWhitePieces();
    }

    private void initSquares()
    {
        for (int rank = Coordinate.MINCOORDINATE; rank <= Coordinate.MAXCOORDINATE; rank++)
        {
            for (int file = Coordinate.MINCOORDINATE; file <= Coordinate.MAXCOORDINATE; file++)
            {
                tiles[file][rank] = new chessTile(new Coordinate(file, rank), null);
            }
        }
    }
    private void initBlackPieces()
    {

    }

    private void initWhitePieces()
    {

    }
}
