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
    
    //GameCharacter starts on a Tile
    public GameCharacter(Tile startTile) {
        currentTile = startTile;
    }
    
    public Key getKey() {
        return this.key;
    }
    //return true if key is not null, GameCharacter has the key
    public boolean hasKey() {
        return this.key != null;
    }
    //used by KeyTile to set key to GameCharacter
    public void setKey(Key key) {
        this.key = key;
    }
    //return the tile (position) the GameCharacter is standing on currently
    public Tile getCurrentTile() {
        return currentTile;
    }
    //sets the GameCharacter to a certain tile as his currentTile
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public static Image getImage() {
        return image;
    }
    
    public void paint(Graphics g)
    {//return if image is null
        if(image == null)
            return;
        //get currentTile position on the BoardCoordinate
        int x = currentTile.getPos().getScreenX();
        int y = currentTile.getPos().getScreenY();
        
        // scale image uniformly to tile size
        float widthF = (float)(TILE_WIDTH-10)/(float)image.getWidth(null);
        float heightF = (float)(TILE_HEIGHT-10)/(float)image.getHeight(null);
        float factor = Math.min(widthF, heightF);
        
        int width = (int) (image.getWidth(null)*factor), height = (int) (image.getHeight(null)*factor);
        g.drawImage(image, x, y+5, width, height, null);
        
        //draw keyLabel whenever GameCharacter hasKey
        if(hasKey()) {
            g.setColor(Color.WHITE);
            g.setFont(LABEL_FONT);
            int labelX = x + TILE_WIDTH-25;
            int labelY = y + (TILE_HEIGHT-20)/2;
            g.drawString(key.getKeyLabel(), labelX, labelY);
//            g.setColor(Color.BLACK);
        }
            
    }
}
