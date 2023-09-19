package chess.engine;
public enum Colour
{
    WHITE {
        public int getDirection() {return -1;}
    },
    BLACK {
        public int getDirection() {return 1;}
    };
    public abstract int getDirection();
}
