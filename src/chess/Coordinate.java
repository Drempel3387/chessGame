package chess;
/**
 * @author Devon R.
 *
 * This is a representation of a singular chess square's position within a board. This position is defined by the
 * rank and file that the square occupies. The class maintains the rank and file of the coordinate. It is respnsible for
 * validation of correct coordinates, addition, multiplication, and string representations.
 */
public class Coordinate {
    /**
     * rank which the coordinate lies on
     */
    private final int rank;
    /**
     * file which the coordinate lies on
     */
    private final int file;
    /**
     * Minimum possible coordinate within a chessboard. This corresponds to the 8X8 array of tile (starting index is 0)
     */
    public static int MIN_COORDINATE = 0;
    /**
     * Maximum possible coordinate within chessboard. This corresponds to the 8X8 array of tile (final index is 7)
     */
    public static int MAX_COORDINATE = 7;

    /**
     * creates a coordinate with a specific rank and file
     * @param file File of the coordinate
     * @param rank Rank of the coordinate
     */
    public Coordinate(final int file, final int rank)  {
        this.rank = rank;
        this.file = file;
    }

    /**
     * Creates a coordinate with the same rank and file values of another coordinate
     * @param coordinate Coordinate whose values are to be copied
     */
    public Coordinate(final Coordinate coordinate) {
        this.rank = coordinate.getRank();
        this.file = coordinate.getFile();
    }
    //getters

    /**
     * returns the rank that the coordinate corresponds to
     * @return rank
     */
    public int getRank() { return rank; }

    /**
     * returns the file which the coordinate corresponds to
     * @return file
     */
    public int getFile() { return file; }

    /**
     * Returns whether a coordinate corresponds to a coordinate within a chessboard.
     * @return boolean
     */
    public boolean isValid() {
        if (rank < MIN_COORDINATE || rank > MAX_COORDINATE)
            return false;
        return (file >= MIN_COORDINATE && file <= MAX_COORDINATE);
    }

    /**
     * returns a boolean indicating if two coordinates have the same rank and file values
     * @param coordinate The coordinate that will be added to this.
     * @return boolean
     */
    public boolean areEqual(final Coordinate coordinate) { //check whether two coordinates have the same rank and file
       return (rank == coordinate.getRank() && file == coordinate.getFile());//if both are equal (true), otherwise false
    }

    /**
     * Will return the result of addition between this coordinate and another coordinate
     * @param coordinate The coordinate will be added to this
     * @return Coordinate
     */
    public Coordinate add(final Coordinate coordinate) { //add two coordinates
        return (new Coordinate(this.file + coordinate.getFile(), this.rank + coordinate.getRank()));
    }

    /**
     * Will calculate and return the result of multiplication of a coordinate with an integer
     * @param number An integer to be used for multiplication
     * @return Resultant Coordinate of multiplication
     */
    public Coordinate multiply(final int number) {
        return new Coordinate(this.file*number, this.rank*number);
    }

    /**
     * Calculates the distance between two coordinates
     * @param secondCoordinate A coordinate whose distance from this needs to be determined
     * @return double indicating distance between this and another coordinate
     */
    public double distance(final Coordinate secondCoordinate) {
        double deltaX, deltaY;
        deltaX = this.getFile() - secondCoordinate.getFile();
        deltaY = this.getRank() - secondCoordinate.getRank();
        return (Math.sqrt(Math.pow(deltaX, 2)+ Math.pow(deltaY, 2)));
    }

    /**
     * 97 in ASCII is 'a'. This is the starting file on a chessboard and will be used to convert between integer
     * character values for a file.
     */
    public final static int STARTING_FILE = 97;

    /**
     * Will convert a coordinate to a string
     * @return String representation of a coordinate
     */
    @Override
    public String toString() {
        return ((char)(STARTING_FILE + (char)file) + (Integer.toString(MAX_COORDINATE - rank + 1)));
    }

    /**
     * converts and returns a file to string notation.
     * @return String representation of a file
     */
    public String fileToString() {
        return (Character.toString((char)(STARTING_FILE + (char)file)));
    }
}
