package hhsgame;

public class Barricade extends Tile{
    
    public Barricade(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return false;
    }
}
