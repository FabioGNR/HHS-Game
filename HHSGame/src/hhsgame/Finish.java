package hhsgame;

import static hhsgame.Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class Finish extends Tile{
    private static final Image image = ImageReader.getImage("finish.png");
    
    //inherit position from Tile class
    public Finish(BoardCoordinate pos) {
        super(pos);
    }
    
    //return value is true, GameCharacter may pass
    @Override
    public boolean isPassable(GameCharacter character) {
        return true;
    }
    // draws the tile if image was loaded
    @Override
    public void paint(Graphics g) {
        if(image == null)
            return;
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    
    @Override
    public boolean isFinish()
    {
        return true;
    }
    
    public static Image getImage() {
        return image;
    }

    // returns string that can be saved in a level file
    @Override
    public String saveToString() {
        return "F  ";
    }
}
