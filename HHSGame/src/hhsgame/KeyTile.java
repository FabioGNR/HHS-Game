package hhsgame;

import static hhsgame.Game.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class KeyTile extends Tile {
    private static final Image image = ImageReader.getImage("keyTile.png");
    private final Key key;
    
    //KeyTile inherit position from Tile class and has a Key
    public KeyTile(BoardCoordinate pos, Key key) {
        super(pos);
        this.key = key;
    }
    
    //GameCharacter can pass
    @Override
    public boolean isPassable(GameCharacter character) {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        if(image == null)
            return;  
        //draw key image on a Tile
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        
        //draw a keyLabel on it
        g.setColor(Color.WHITE);
        g.setFont(LABEL_FONT);
        int labelX = x + (TILE_WIDTH-50)/2;
        int labelY = y + (TILE_HEIGHT-20)/2;
        g.drawString(key.getKeyLabel(), labelX, labelY);
    }
    
    public Key getKey() {
        return key;
    }
    
    //KeyTile gets replaced by a EmptyTile
    @Override
    public Tile getReplacement() {
        return new EmptyTile(pos);
    }
    
    //give the charater the key that's on this tile when the character enters it
    @Override
    public void onCharacterEnter(GameCharacter character) {
        character.setKey(key);
    }

    public static Image getImage() {
        return image;
    }

    // returns string that can be saved in a level file
    @Override
    public String saveToString() {
        return "K " + key.getKeyLabel();
    }
}
