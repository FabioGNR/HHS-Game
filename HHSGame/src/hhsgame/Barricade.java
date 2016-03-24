package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

public class Barricade extends Tile{
    private static final Image image = ImageReader.getImage("barricade.png");
    private final int keyCode;
    private final String keyCodeLabel;
    
    public Barricade(BoardCoordinate pos, int code) {
        super(pos);
        keyCode = code;
        keyCodeLabel = Integer.toString(code);
    }
    
    @Override
    public boolean isPassable() {
        return false;
    }

    public int getKeyCode() {
        return keyCode;
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
}
