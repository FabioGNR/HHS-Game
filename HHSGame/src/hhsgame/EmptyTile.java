package hhsgame;

import static hhsgame.Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class EmptyTile extends Tile{
    private static final Image image = ImageReader.getImage("grass.png");
    
    public EmptyTile(BoardCoordinate pos) {
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
    public Tile getReplacement() {
        // empty tiles can't be replaced
        return this;
    }

    @Override
    public void onCharacterEnter(GameCharacter character) {
        // no action required
    }
}
