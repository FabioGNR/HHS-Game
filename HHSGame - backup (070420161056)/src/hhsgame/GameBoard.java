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

    public GameBoard(int width, int height) {
        this.setSize(new Dimension(width, height));
        this.addKeyListener(new ControlListener());
    }

    public void togglePause() {
        paused = !paused;
        grabFocus();
    }

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
        //  
        if (winBox != null) {
            winBox.paint(g);
        }

    }

    private class ControlListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) { // $TO-DO switch?
            if (paused || finished) {
                return;
            }
            MoveDirection dir;
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
            moveCharacter(dir);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    protected void finishGame() {
        finished = true;
        winBox = new PopUpBox("Congratulations!");
    }

    protected void moveCharacter(MoveDirection dir) {
        if (dir == null) {
            return;
        }
        BoardCoordinate currentPos = character.getCurrentTile().getPos();
        BoardCoordinate nextPos = dir.getCoordinate(currentPos);
        Tile nextTile = levelLayout.get(nextPos);
        if (nextTile.isPassable(character)) {
            nextTile.onCharacterEnter(character);
            Tile replacement = nextTile.getReplacement();
            levelLayout.put(nextPos, replacement);
            character.setCurrentTile(replacement);
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

}
