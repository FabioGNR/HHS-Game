package hhsgame;

public class GameBoard extends Game{
    
    private boolean paused = false;
    
    private Tile[][] levelLayout;
    private int currentLevel;
    
    public void togglePause() {
        paused = !paused;
    }
    
    public void loadLevel(int level) {
        
    }
    
    public void reset() {
        loadLevel(currentLevel);
    }
    
    
}
