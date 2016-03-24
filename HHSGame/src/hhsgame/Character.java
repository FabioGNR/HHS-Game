package hhsgame;

public class Character extends BoardComponent{
    
    private Key key;
    
    public Character(BoardCoordinate pos) {
        super(pos);
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public boolean hasKey() {
        return this.key != null;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    
}
