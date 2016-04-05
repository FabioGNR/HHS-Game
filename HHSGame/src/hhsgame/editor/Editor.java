package hhsgame.editor;

import hhsgame.*;
import static hhsgame.Game.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComponent;

public class Editor extends JComponent{
    
    private String levelName;
    private Map<BoardCoordinate, Tile> levelLayout = new TreeMap<BoardCoordinate, Tile>();
    private GameCharacter character;
    private BoardCoordinate selected = null;
    
    public Editor() {
        for(int y = 0; y < ROWS; y++) {
            for(int x = 0; x < COLS; x++) {
                BoardCoordinate currPos = new BoardCoordinate(x, y);
                levelLayout.put(currPos, new EmptyTile(currPos));
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // paint all tiles
        for (Tile tile : levelLayout.values()) {
            tile.paint(g);
        }
        // paint player on top
        character.paint(g);
        if(selected != null) {
            g.setColor(Color.RED);
            g.drawRect(selected.getX(), selected.getY(), TILE_WIDTH, TILE_HEIGHT);
            g.setColor(Color.BLACK);
        }
    }
    
    public class SelectListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int BoardX = e.getX()/TILE_WIDTH;
            int BoardY = e.getY()/TILE_HEIGHT;
            
            if(BoardX < COLS && BoardY < ROWS) {
                selected = new BoardCoordinate(BoardX, BoardY);
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
