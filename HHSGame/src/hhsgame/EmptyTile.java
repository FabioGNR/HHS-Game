package hhsgame;

public class EmptyTile extends Tile{
    
    private Key key;
    
    public EmptyTile(BoardCoordinate pos) {
        super(pos);
    }
    
    public EmptyTile(BoardCoordinate pos, Key key) {
        super(pos);
        this.key = key;
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public boolean hasKey() {
        return this.key != null;
    }
    
    public boolean isPassable() {
        return true;
    }
    
}
