package hhsgame;

import static hhsgame.Game.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class KeyTile extends Tile {
    private static final Image image = ImageReader.getImage("keyTile.png");
    private final Key key;
    
    public KeyTile(BoardCoordinate pos, Key key) {
        super(pos);
        this.key = key;
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
        g.setColor(Color.WHITE);
        g.setFont(LABEL_FONT);
        int labelX = x + (TILE_WIDTH-50)/2;
        int labelY = y + (TILE_HEIGHT-20)/2;
        g.drawString(key.getKeyLabel(), labelX, labelY);
        g.setColor(Color.BLACK);
    }
    
    public Key getKey() {
        return key;
    }

    @Override
    public Tile getReplacement() {
        return new EmptyTile(pos);
    }

    @Override
    public void onCharacterEnter(GameCharacter character) {
        character.setKey(key);
    }

    public static Image getImage() {
        return image;
    }
}
