package hhsgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import static hhsgame.Game.*;
import java.awt.Dimension;
import javax.swing.JComponent;

public class GameBoard extends JComponent {

    private boolean paused = false;
    private boolean finished = false;
    private boolean levelLoaded = false;

    private Map<BoardCoordinate, Tile> levelLayout;
    private Level currentLevel;
    private GameCharacter character;
    private PopUpBox winBox = null;
    
    //
    public GameBoard(int width, int height) {
        this.setSize(new Dimension(width, height));
        this.addKeyListener(new ControlListener());
    }
    //paused is set from false to true
    public void togglePause() {
        paused = !paused;
        grabFocus();
    }
    //levelLayout from Level class is stored in this.levelLayout and level is stored as currentLevel
    //make character and put it in the startposition of levelLayout
    //finished and pause are set to false, winBox to null, levelLoaded to true
    //then repaint the level and grabFocus
    public void loadLevel(Level level) {
        levelLayout = level.getLevelLayout();
        currentLevel = level;
        character = new GameCharacter(levelLayout.get(level.getStart()));
        finished = false;
        paused = false;
        winBox = null;
        levelLoaded = true;
        repaint();
        grabFocus();
    }
    //load currentLevel
    public void reset() {
        loadLevel(currentLevel);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!levelLoaded) {
            return;
        }
        // paint all tiles
        for (Tile tile : levelLayout.values()) {
            tile.paint(g);
        }
        // paint player on top
        character.paint(g);
        //  paint the winBox if not null
        if (winBox != null) {
            winBox.paint(g);
        }

    }

    private class ControlListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (paused || finished) {
                return;
            }
            MoveDirection dir;
            //connect keyBoard to GameBoard
            //if the e.getKeyCode is equals to one of the KeyEvent value 
            //make it 'move' in one of the 4 directions
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                dir = MoveDirection.Left;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dir = MoveDirection.Right;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                dir = MoveDirection.Up;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dir = MoveDirection.Down;
            } else {
                return;
            }
            moveCharacter(dir);//move the character
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
    //sets finished to true and show winBox
    protected void finishGame() {
        finished = true;
        winBox = new PopUpBox("Congratulations!");
    }
    
    protected void moveCharacter(MoveDirection dir) {
        if (dir == null) {return;}
        //get the currentPosition of GameCharacter, remembers what the next step and Tile is
        BoardCoordinate currentPos, nextPos;
        currentPos = character.getCurrentTile().getPos();
        nextPos = dir.getCoordinate(currentPos);
        Tile nextTile = levelLayout.get(nextPos);
        //check if the GameCharacter can enter or pass the nextTile
        //replace another Tile on character enter
        if (nextTile.isPassable(character)) {
            nextTile.onCharacterEnter(character);
            Tile replacement = nextTile.getReplacement();
            levelLayout.put(nextPos, replacement);
            character.setCurrentTile(replacement);
            //if Tile is Finish, end the game
            if (replacement.isFinish()) {
                finishGame();
            }
        }
        repaint();
    }

    //for unit testing

    public GameCharacter getCharacter() {
        return character;
    }
    
    public Map<BoardCoordinate, Tile> getLevelLayout() {
        return levelLayout;
    }

}
