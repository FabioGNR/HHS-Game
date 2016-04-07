package hhsgame;

import static hhsgame.Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class EmptyTile extends Tile{
    private static final Image image = ImageReader.getImage("grass.png");
    
    //inherit position from Tile class
    public EmptyTile(BoardCoordinate pos) {
        super(pos);
    }
    //returns true, Gamecharacter may pass 
    @Override
    public boolean isPassable(GameCharacter character) {
        return true;
    }
    //return if image is null therwise draw image
    @Override
    public void paint(Graphics g) {
        if(image == null)
            return;
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    //return image
    public static Image getImage() {
        return image;
    }
    
    @Override
    public String saveToString() {
        return "E  ";
    }
}
