package hhsgame;

public class Player extends BoardComponent{
    
    private Key key;
    
    public Player(BoardCoordinate pos) {
        super(pos);
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public boolean hasKey() {
        return this.key == null;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    
}
