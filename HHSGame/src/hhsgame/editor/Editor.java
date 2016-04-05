package hhsgame.editor;

import hhsgame.*;
import static hhsgame.Game.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComponent;

public class Editor extends JComponent{
    
    private String levelName;
    private Map<BoardCoordinate, Tile> levelLayout = new TreeMap<>();
    private GameCharacter character;
    private BoardCoordinate selected = null;
    private TileType currTileType = TileType.Empty;
    private int keyCode = 0;
    
    public Editor(int width, int height) {
        reset(); // fills level with empty tiles
        this.setSize(width, height);
        this.addMouseListener(new SelectListener());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // paint all tiles
        for (Tile tile : levelLayout.values()) {
            tile.paint(g);
        }
        if(selected != null) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(5));
            g.setColor(Color.RED);
            g.drawRect(selected.getScreenX(),selected.getScreenY(), TILE_WIDTH, TILE_HEIGHT);
            g.setColor(Color.BLACK);
        }
    }
    
    public void setTileType(TileType type, int keyCode) {
        currTileType = type;
        this.keyCode = keyCode;
    }
    
    public void reset() {
        for(int y = 0; y < ROWS; y++) {
            for(int x = 0; x < COLS; x++) {
                BoardCoordinate currPos = new BoardCoordinate(x, y);
                levelLayout.put(currPos, new EmptyTile(currPos));
            }
        }
    }
    
    public void save(String filePath) throws IOException {
        String[][] levelString = new String[ROWS][COLS];
        for(BoardCoordinate pos : levelLayout.keySet()) {
            Tile currTile = levelLayout.get(pos);
            if(currTile instanceof KeyTile) {
                levelString[pos.getY()][pos.getX()] = "K "+((KeyTile) currTile).getKey().getKeyCode();
            } else if(currTile instanceof Barricade) {
                levelString[pos.getY()][pos.getX()] = "B "+((Barricade) currTile).getKeyCode();
            } else if(currTile instanceof Wall) {
                levelString[pos.getY()][pos.getX()] = "W  ";
            } else if(currTile instanceof Finish) {
                levelString[pos.getY()][pos.getX()] = "F  ";
            } else {
                levelString[pos.getY()][pos.getX()] = "E  ";
            }
        }
        LevelWriter.writeLevel(filePath, levelString);
    }
    
    public class SelectListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int BoardX = e.getX()/TILE_WIDTH;
            int BoardY = e.getY()/TILE_HEIGHT;
            
            if(BoardX < COLS && BoardY < ROWS) {
                selected = new BoardCoordinate(BoardX, BoardY);
            }
            
            Tile currTile;
            if(selected != null) {
                currTile = currTileType.createInstance(selected, keyCode);
                levelLayout.put(selected, currTile);
            }
            repaint();
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
