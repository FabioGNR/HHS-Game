package hhsgame;

public class Wall extends Tile{
    public Wall(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return false;
    }
}
