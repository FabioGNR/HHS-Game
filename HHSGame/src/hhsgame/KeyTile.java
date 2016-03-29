package hhsgame;

import static hhsgame.Game.*;
import java.awt.Graphics;
import java.awt.Image;

public class KeyTile extends Tile {
    private static final Image image = ImageReader.getImage("keyTile.png");
    private final Key key;
    private final String keyCodeLabel;
    
    public KeyTile(BoardCoordinate pos, Key key) {
        super(pos);
        this.key = key;
        keyCodeLabel = Integer.toString(key.getKeyCode());
    }

    @Override
    public boolean isPassable(Character character) {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        if(image == null)
            return;
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        int labelX = x + (TILE_WIDTH-50)/2;
        int labelY = y + (TILE_HEIGHT-20)/2;
        g.drawString(keyCodeLabel, labelX, labelY);
    }
    
    public Key getKey() {
        return key;
    }

    @Override
    public Tile getReplacement() {
        return new EmptyTile(pos);
    }

    @Override
    public void onCharacterEnter(Character character) {
        character.setKey(key);
    }
}
