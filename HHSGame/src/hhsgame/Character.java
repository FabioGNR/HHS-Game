package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

public class Character{
    private static final Image image = ImageReader.getImage("character.jpg");
    
    // The key the player is currently holding, can be null
    private Key key;
    // The Tile the player is currently on, this will never be null
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
        // scale image uniformly to tile size
        float widthF = image.getWidth(null)/TILE_WIDTH;
        float heightF = image.getHeight(null)/TILE_HEIGHT;
        float factor = Math.min(widthF, heightF);
        int width = (int) (image.getWidth(null)*factor), height = (int) (image.getHeight(null)*factor);
        g.drawImage(image, x, y, width, height, null);
    }
}
