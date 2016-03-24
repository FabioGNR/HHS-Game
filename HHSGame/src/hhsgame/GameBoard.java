package hhsgame;

// $TODO change extending Game to static import of variables

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        levelLayout = reader.getLevelTiles(level);
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
    
    private class ControlListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            Tile nextTile;
            if(e.getKeyCode() == e.VK_LEFT) {
                nextTile = levelLayout.get(character.getCurrentTile().getPos().getLeft());
            } else if(e.getKeyCode() == e.VK_RIGHT) {
                nextTile = levelLayout.get(character.getCurrentTile().getPos().getRight());
            } else if(e.getKeyCode() == e.VK_UP) {
                nextTile = levelLayout.get(character.getCurrentTile().getPos().getTop());
            } else if(e.getKeyCode() == e.VK_DOWN) {
                nextTile = levelLayout.get(character.getCurrentTile().getPos().getBottom());
            } else {
                return;
            }
            if(nextTile.isPassable()) {
                character.setCurrentTile(nextTile);
            }
            if(nextTile instanceof KeyTile) {
                KeyTile nextKeyTile = (KeyTile) nextTile;
                character.setKey(nextKeyTile.getKey());
                levelLayout.put(nextKeyTile.getPos(), new EmptyTile(nextKeyTile.getPos()));
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    
    }
}
