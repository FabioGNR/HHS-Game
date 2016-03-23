package hhsgame;

public class Key extends BoardComponent{
    
    private final int keyCode;
    
    public Key(BoardCoordinate pos, int keyCode) {
        super(pos);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
    
}
