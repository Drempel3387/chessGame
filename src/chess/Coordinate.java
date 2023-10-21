package chess;
public class Coordinate {
    //row and column values which relate to a chessboard
    private final int rank;
    private final int file;

    //minimum and maximum rank and files values for a chess board (8x8 collection of tiles)
    public static int MIN_COORDINATE = 0;
    public static int MAX_COORDINATE = 7;

    public Coordinate(final int file, final int rank)
    {
        this.rank = rank;
        this.file = file;
    }//parameterized constructor

    public Coordinate(final Coordinate coordinate)
    {
        this.rank = coordinate.getRank();
        this.file = coordinate.getFile();
    }//copy constructor

    //getters
    public int getRank() {return rank;}
    public int getFile() {return file;}
    public boolean isValid()//check to see if a coordinate is within the bounds of a chessboard
    {
        if (rank < MIN_COORDINATE || rank > MAX_COORDINATE)//if rank not within bounds
            return false;
        return (file >= MIN_COORDINATE && file <= MAX_COORDINATE);//return true if rank and file both within bounds
        //false if file not within bounds
    }
    public boolean areEqual(final Coordinate coordinate)//check whether two coordinates have the same rank and file
    {
       return (rank == coordinate.getRank() && file == coordinate.getFile());//if both are equal (true), otherwise false
    }

    public Coordinate add(final Coordinate coordinate)//add two coordinates
    {
        return (new Coordinate(this.file + coordinate.getFile(), this.rank + coordinate.getRank()));
    }
    public Coordinate multiply(final int number)
    {
        return new Coordinate(this.file*number, this.rank*number);
    }
    public double distance(final Coordinate secondCoordinate) {
        double deltaX, deltaY;
        deltaX = this.getFile() - secondCoordinate.getFile();
        deltaY = this.getRank() - secondCoordinate.getRank();
        return (Math.sqrt(Math.pow(deltaX, 2)+ Math.pow(deltaY, 2)));
    }

    //use for conversion to algebraic notation
    public final static int STARTING_FILE = 97;
    @Override
    public String toString()//will return the coordinate in algebraic notation. Ex. a4, b6, h8, etc
    {
        return ((char)(STARTING_FILE + (char)file) + (Integer.toString(MAX_COORDINATE - rank + 1)));
    }
    public String fileToString()
    {
        return (Character.toString((char)(STARTING_FILE + (char)file)));
    }
}
