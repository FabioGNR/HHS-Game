package hhsgame;

// $TODO change extending Game to static import of variables

import java.awt.Graphics;
import java.util.Map;
import javax.swing.JComponent;

public class GameBoard extends JComponent{
    
    private boolean paused = false;
    
    private Map<BoardCoordinate, Tile> levelLayout;
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
    
    @Override
    public void paintComponent(Graphics g)
    {
        
    }
}
