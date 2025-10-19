package chess.Pieces;

import chess.Board.Board;
import chess.Game.Game;
import chess.Game.moveList;
import chess.Moves.Move;
import chess.Colour;
import chess.Coordinate;
import chess.Moves.enPassantMove;
import chess.Moves.normalMove;
import chess.Moves.promotionMove;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Devon R.
 *
 * This is an abstract generic representation of a chess piece. It will contain attributes that every chess piece needs
 * and functions that every chess piece will use. It will keep track of coordinate of the piece, colour of the piece
 * ,and living status(dead or alive). It will define abstract methods for getting legal moves, square attacking ability
 * and representing a piece as a string. Additionally, each will need to be able to remove illegal moves and this has
 * been defined.
 */
public abstract class Piece {
    /**
     * Whether the piece has been captured
     */
    private boolean isAlive;
    /**
     * The colour that the piece belongs to
     */
    private final Colour colour;//Colour of the piece, BLACK or WHITE
    /**
     * The coordinate on the chessboard that the piece currently occupies
     */
    private Coordinate coordinate;//Coordinate on the chessboard of the piece

    /**
     * creates a new piece
     * @param colour colour of the piece
     * @param coordinate starting coordinate of the piece
     */
    public Piece(final Colour colour, final Coordinate coordinate) {
        this.coordinate =  coordinate;
        this.colour = colour;
        this.isAlive = true;
    }
    //getters

    /**
     * returns the coordinate that the piece currently occupies
     * @return coordinate
     */
    public Coordinate getCoordinate() {return coordinate;}

    /**
     * returns the "dead" or "alive" status of the piece
     * @return isAlive
     */
    public boolean getIsAlive() {return isAlive;}

    /**
     * Returns the colour or "alliance" of the piece
     * @return colour
     */
    public Colour getColour() {return colour;}
    //setters

    /**
     * will set the living status of a piece
     * @param isAlive living status
     */
    public void setIsAlive(final boolean isAlive) {this.isAlive = isAlive;}
    /**
     * will update the coordinate of a piece
     * @param coordinate current cooordinate
     */
    public void setCoordinate(final Coordinate coordinate) {this.coordinate = coordinate;}

    /**
     * Filters out invalid moves from the given list based on the current board position.
     * This method checks each move's impact on the current player's king, removing moves
     * that would leave the king in a checked position.
     *
     * @param moves list of moves that may be legal(not inspected for pins yet)
     * @param board the board which the moves belong to
     * @param currentColour colour of the current player
     */
    protected void removeInvalidMoves(final List<Move> moves, final Board board, final Colour currentColour) {
        King king = currentColour == Colour.WHITE? board.getWhiteKing(): board.getBlackKing();
        List<Move> illegalMoves = new ArrayList<>();
        for (Move move: moves) {
            move.makeMove();//make each current possible move
            if (king.isKingChecked(board))//if this move places the king into check, the piece is pinned
            {
                illegalMoves.add(move);
            }
            move.unMakeMove();//return back to original position
        }
        for (Move move: illegalMoves) {
            moves.remove(move);
        }//remove any illegal moves
    }//remove moves which place the king into check

    /**
     * this returns a list of legal moves for a given piece in a specific chess game.
     * @param game game which the piece belongs to
     * @return List<Move> list of legal moves based on the current position in the game.
     */
    public abstract List<Move> getLegalMoves(final Game game);//a list of all legal moves for a piece

    /**
     * this function checks to see if a piece is attacking a current square. If a piece can move to a square this means
     * that it is "attacking" that square.
     *
     * @param board board that the piece is on
     * @param squarePosition square that needs to be checked for attacking ability
     * @return boolean signifying attacking ability for a specified square
     */
    public abstract boolean canAttackSquare(final Board board, final Coordinate squarePosition);

    /**
     * this will return a string representation of a piece. Will be a single capitalized letter. King "K"
     * Rook "R", Pawn "P", etc...
     * @return String representing the piece
     */
    @Override
    public abstract String toString();//print the piece to the screen, used to play console chess
}

