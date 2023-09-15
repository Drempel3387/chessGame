package chess.engine.chessTile;
public class Coordinate {
    //row and column values which relate to a chessboard
    private int rank;
    private int file;

    //minimum and maximum rank and files values for a chess board (8x8 collection of tiles)
    public final static int MINCOORDINATE = 1;
    public final static int MAXCOORDINATE = 8;

    public Coordinate(int file, int rank)
    {
        this.rank = rank;
        this.file = file;
    }
    //getters
    public int getRank() {return rank;}
    public int getFile() {return file;}

    //setters
    public void setRank(int rank) {this.rank = rank;}
    public void setFile(int file) {this.file = file;}

    public boolean isValid()//check to see if a coordinate is within the bounds of the chessboard
    {
        if (rank < MINCOORDINATE || rank > MAXCOORDINATE)//if rank not within bounds
            return false;
        return (file >= MINCOORDINATE && file <= MAXCOORDINATE);//return true if rank and file both within bounds
        //false if file not within bounds
    }

    public boolean areEqual(Coordinate coordinate)//check whether two coordinates have the same rank and file
    {
       return (rank == coordinate.getRank() && file == coordinate.getFile());//if both are equal (true), otherwise false
    }

    //use for conversion to algebraic notation, 96 is the character directly before 'a'
    private final static int STARTINGFILE = 96;
    @Override
    public String toString()//will return the coordinate in algebraic notation. Ex. a4, b6, h8, etc
    {
        String chessNotation = "";
        chessNotation += (char)(STARTINGFILE + (char)file) + (Integer.toString(rank));
        return chessNotation;
    }
}
