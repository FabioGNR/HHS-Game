package hhsgame;

// $TODO change extending Game to static import of variables
public class GameBoard extends Game{
    
    private boolean paused = false;
    
    private Tile[][] levelLayout;
    private int currentLevel;
    
    public GameBoard() {
        setFocusable(true);
    }
    
    public void togglePause() {
        paused = !paused;
    }
    
    public void loadLevel(int level) {
        
    }
    
    public void reset() {
        loadLevel(currentLevel);
    }
    
    
}
