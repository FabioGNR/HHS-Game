package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

public class Wall extends Tile{
    private static final Image image = ImageReader.getImage("wall.png");
    
    //inherit position from superclass Tile
    public Wall(BoardCoordinate pos) {
        super(pos);
    }
    
    //GameCharacter may not pass
    @Override
    public boolean isPassable(GameCharacter character) {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        //draw image if image didn't fail to load
        if(image == null)
            return;
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public static Image getImage() {
        return image;
    }

    // returns string that can be saved in a level file
    @Override
    public String saveToString() {
        return "W  ";
    }
}
