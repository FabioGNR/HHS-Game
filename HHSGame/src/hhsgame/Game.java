package hhsgame;

public class Game {
    protected static int ROWS = 10;
    protected static int COLS = 10;
    protected static int TILE_HEIGHT = 50;
    protected static int TILE_WIDTH = 50;
    
    protected static int getScreenX(BoardCoordinate coord) {
        return TILE_WIDTH*coord.getX();
    }
    protected static int getScreenY(BoardCoordinate coord) {
        return TILE_HEIGHT*coord.getY();
    }
    
    protected static int getRightBound() {
        return TILE_WIDTH*COLS;
    }
    protected static int getBottomBound() {
        return TILE_HEIGHT*ROWS;
    }
    
    public static void main(String[] args) {
        
    }
    
}
