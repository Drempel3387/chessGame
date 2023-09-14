public class chessTile {
    private int coordinate;//location of the chessTile
    private Piece piece;//piece at the tile
    private boolean isOccupied;//tile occupation status
    public chessTile(int coordinate, boolean isOccupied, Piece piece)
    {
        this.coordinate = coordinate;
        this.isOccupied = isOccupied;
        this.piece = piece;
    }//construct a chessTile

    //getters
    public boolean isOccupied() {return isOccupied;}
    public Piece getPiece() {return piece;}
    public int getCoordinate() {return coordinate;}

    //setters
    public void setCoordinate(int coordinate) {this.coordinate = coordinate;}
    public void setPiece(Piece piece) {this.piece = piece;}
    public void setIsOccupied(boolean isOccupied) {this.isOccupied = isOccupied;}
}
