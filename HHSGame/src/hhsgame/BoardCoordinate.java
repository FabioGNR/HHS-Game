package hhsgame;
import static hhsgame.Game.*;

public class BoardCoordinate{
    private final int x;
    private final int y;
    
    public BoardCoordinate(int x, int y) {
        if(x < 0) { // If X is outside the lower bound, keep it on the lower bound
            this.x = 0;
        } else if(x >= COLS) { // If X is outside the higher bound, keep it on the higher bound
            this.x = COLS-1;
        } else {
            this.x = x;
        }
        
        if(y < 0) { // If Y is outside the lower bound, keep it on the lower bound
            this.y = 0;
        } else if(y >= ROWS) { // If Y is outside the higher bound, keep it on the higher bound
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
    
    // By making new Coordinates with changed values, we can do relative moving.
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
