package hhsgame;

// $TODO change extending Game to static import of variables

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import static hhsgame.Game.*;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class GameBoard extends JComponent{
    
    private boolean paused = false;
    private boolean finished = false;
    private boolean levelLoaded = false;
    
    private Map<BoardCoordinate, Tile> levelLayout;
    private int currentLevel;
    private Character character;
    private PopUpBox winBox = null;
    
    public GameBoard(int width, int height) {
        setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
        this.addKeyListener(new ControlListener());
    }
    
    public void togglePause() {
        paused = !paused;//?
        grabFocus();
    }
    
    public void loadLevel(LevelReader reader, int levelID) {
        Level level = reader.getLevels().get(levelID);
        levelLayout = level.buildLevel();
        currentLevel = levelID;
        character = new Character(levelLayout.get(level.start));
        finished = false;
        paused = false;
        winBox = null;
        levelLoaded = true;
        repaint();
        grabFocus();
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
        //  
        if(winBox != null) winBox.paint(g);
        
    }
    
    private class ControlListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if(paused || finished) return;
            MoveDirection dir;
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir = MoveDirection.Left;
                System.out.println("left pressed");
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir = MoveDirection.Right;
                System.out.println("right pressed");
            } else if(e.getKeyCode() == KeyEvent.VK_UP) {
                dir = MoveDirection.Up;
                System.out.println("top pressed");
            } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                dir = MoveDirection.Down;
                System.out.println("bottom pressed");
            } else {
                return;
            }
            moveCharacter(dir);
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    
    }
    
    private void finishGame() {
        togglePause();
        winBox = new PopUpBox("Congratulations!");
    }
    
    private void moveCharacter(MoveDirection dir)
    {
        BoardCoordinate currentPos = character.getCurrentTile().getPos();
        BoardCoordinate nextPos;
        if(dir == MoveDirection.Left) {
            nextPos = currentPos.getLeft();
        }
        else if(dir == MoveDirection.Right) {
            nextPos = currentPos.getRight();
        }
        else if(dir == MoveDirection.Up) {
            nextPos = currentPos.getTop();
        }
        else if(dir == MoveDirection.Down) {
            nextPos = currentPos.getBottom();
        }
        else {
            return;
        }
        Tile nextTile = levelLayout.get(nextPos);
        if(nextTile.isPassable(character)) {
            nextTile.onCharacterEnter(character);
            Tile replacement = nextTile.getReplacement();
            levelLayout.put(nextPos, replacement);
            character.setCurrentTile(replacement);
            if(replacement instanceof Finish) {
                finishGame();
            }
        }
        repaint();
    }
}
