package hhsgame;

// $TODO change extending Game to static import of variables

import java.awt.Graphics;
import java.util.Map;
import javax.swing.JComponent;

public class GameBoard extends JComponent{
    
    private boolean paused = false;
    private boolean levelLoaded = false;
    
    private Map<BoardCoordinate, Tile> levelLayout;
    private int currentLevel;
    private Character character;
    
    public GameBoard() {
        setFocusable(true);
    }
    
    public void togglePause() {
        paused = !paused;
    }
    
    public void loadLevel(LevelReader reader, int level) {
        
        levelLoaded = true;
    }
    
    public void reset(LevelReader reader) {
        loadLevel(reader, currentLevel);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if(!levelLoaded) return;
        // paint all tiles
        for(Tile tile : levelLayout.values())
        {
            tile.paint(g);
        }
        // paint player on top
        character.paint(g);
    }
}
