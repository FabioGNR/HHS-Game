package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;
import java.awt.Color;

public class GameCharacter{
    private static final Image image = ImageReader.getImage("character.png");
    
    // The key the player is currently holding, can be null
    private Key key;
    // The Tile the player is currently on, this will never be null
    private Tile currentTile;
    
    public GameCharacter(Tile startTile) {
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

    public static Image getImage() {
        return image;
    }
    
    public void paint(Graphics g)
    {
        if(image == null)
            return;
        int x = currentTile.getPos().getScreenX();
        int y = currentTile.getPos().getScreenY();
        // scale image uniformly to tile size
        float widthF = (float)(TILE_WIDTH-10)/(float)image.getWidth(null);
        float heightF = (float)(TILE_HEIGHT-10)/(float)image.getHeight(null);
        float factor = Math.min(widthF, heightF);
        int width = (int) (image.getWidth(null)*factor), height = (int) (image.getHeight(null)*factor);
        g.drawImage(image, x, y+5, width, height, null);
        if(hasKey()) {
            g.setColor(Color.WHITE);
            g.setFont(LABEL_FONT);
            int labelX = x + TILE_WIDTH-25;
            int labelY = y + (TILE_HEIGHT-20)/2;
            g.drawString(key.getKeyLabel(), labelX, labelY);
            g.setColor(Color.BLACK);
        }
            
    }
}
