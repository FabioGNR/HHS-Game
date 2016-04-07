package hhsgame;

public class TileBit {
    
    private char type;
    private int keyCode = -1;
    private boolean start = false;
    
    public TileBit(String bit) {
        //Strings is changed to array of char
        char[] parts = bit.toCharArray();
        //if there's at least one character, read its type, otherwise fill with emptyTile 'E'
        if(parts.length > 0) {
            type = parts[0];
            //if third element in char array is an int parse to int and store in keyCode
            if(isInt(parts[2])) {
                keyCode = Integer.parseInt("" + parts[2]);
            }//set start to true if parts[2] == 'C'
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
    
    //try parsing a char to int, return true if it's an int else catch exception and return false
    private boolean isInt(char s) {
        try {
            Integer.parseInt("" + s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
