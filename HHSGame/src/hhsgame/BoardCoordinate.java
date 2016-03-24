package hhsgame;
import static hhsgame.Game.*;

public class BoardCoordinate{
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
    
    public BoardCoordinate getLeft() {
        return new BoardCoordinate(x-1, y);
    }
    public BoardCoordinate getRight(){
        return new BoardCoordinate(x+1, y);    
    }
    public BoardCoordinate getTop(){
        return new BoardCoordinate(x, y-1);
    }
    public BoardCoordinate getBottom(){
        return new BoardCoordinate(x,y+1);
    }
    
        
    public int getScreenX() {
        return TILE_WIDTH*x;
    }
    public int getScreenY() {
        return TILE_HEIGHT*y;
    }
}
