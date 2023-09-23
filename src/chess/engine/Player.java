package chess.engine;

final public class Player {
    private final Colour colour;
    public Player(Colour colour)
    {
        this.colour = colour;
    }
    public Colour getColour()
    {
        return colour;
    }
}
