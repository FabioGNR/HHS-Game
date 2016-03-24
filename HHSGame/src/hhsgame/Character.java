package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

public class Character{
    private static final Image image = ImageReader.getImage("character.png");
    
    private Key key;
    private Tile currentTile;
    
    public Character(Tile startTile) {
        currentTile = startTile;
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public boolean hasKey() {
        return this.key != null;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
    
    public void paint(Graphics g)
    {
        if(image == null)
            return;
        int x = currentTile.getPos().getScreenX();
        int y = currentTile.getPos().getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
}
