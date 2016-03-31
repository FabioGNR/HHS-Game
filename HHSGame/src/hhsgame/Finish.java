package hhsgame;

import static hhsgame.Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class Finish extends Tile{
    private static final Image image = ImageReader.getImage("finish.png");
    
    public Finish(BoardCoordinate pos) {
        super(pos);
    }
    
    @Override
    public boolean isPassable(GameCharacter character) {
        return true;
    }

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
}
