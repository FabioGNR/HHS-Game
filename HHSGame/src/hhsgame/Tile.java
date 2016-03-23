package hhsgame;

public abstract class Tile extends BoardComponent{
    
    public Tile(BoardCoordinate pos) {
        super(pos);
    }
    
    public abstract boolean isPassable();
    
}
