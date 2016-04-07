package hhsgame;

public class Key{
    
    private final int keyCode;
    private final String keyLabel;
    //each key has a keyCode and a label showing what key
    public Key(int keyCode) {
        this.keyCode = keyCode;
        keyLabel = Integer.toString(keyCode);
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getKeyLabel() {
        return keyLabel;
    }
    
}
