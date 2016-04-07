package hhsgame;

public class TileBit {
    
    private char type;
    private int keyCode = -1;
    private boolean start = false;
    
    public TileBit(String bit) {
        char[] parts = bit.toCharArray();
        if(parts.length > 0) {
            type = parts[0];
            if(isInt(parts[2])) {
                keyCode = Integer.parseInt("" + parts[2]);
            }
            start = (parts[2] == 'C');
        } else {
            type = 'E';
        }
    }

    public char getType() {
        return type;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean getStart() {
        return start;
    }
    
    private boolean isInt(char s) {
        try {
            Integer.parseInt("" + s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
