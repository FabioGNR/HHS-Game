package hhsgame;

public class BoardCoordinate extends Game{
    private final int x;
    private final int y;
    
    public BoardCoordinate(int x, int y) {
        if(x < 0) {
            this.x = 0;
        } else if(x >= COLS) {
            this.x = COLS-1;
        } else {
            this.x = x;
        }
        
        if(y < 0) {
            this.y = 0;
        } else if(y >= ROWS) {
            this.y = ROWS-1;
        } else {
            this.y = y;
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
