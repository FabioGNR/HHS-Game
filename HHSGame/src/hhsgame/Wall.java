package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

public class Wall extends Tile{
    private static final Image image = ImageReader.getImage("wall.png");
    
    public Wall(BoardCoordinate pos) {
        super(pos);
    }
    
    @Override
    public boolean isPassable(GameCharacter character) {
        return false;
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
    public Tile getReplacement() {
        // Walls can't be replaced
        return this;
    }

    @Override
    public void onCharacterEnter(GameCharacter character) {
        // no action required
    }
}
