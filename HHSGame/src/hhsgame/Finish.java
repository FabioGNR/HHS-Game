package hhsgame;

public class Finish extends Tile{
    
    public Finish(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return true;
    }
    
}
