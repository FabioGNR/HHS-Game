package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;
import java.awt.Color;

public class Barricade extends Tile{
    private static final Image image = ImageReader.getImage("barricade.png");
    private final int barrKeyCode;
    private final String keyCodeLabel;
    
    public Barricade(BoardCoordinate pos, int code) {
        super(pos);
        barrKeyCode = code;
        keyCodeLabel = Integer.toString(code);
    }
    
    @Override
    public boolean isPassable(GameCharacter character) {
        // return true when the character holds the correct key
        return character.hasKey() && character.getKey().getKeyCode() == barrKeyCode;
    }

    public int getKeyCode() {
        return barrKeyCode;
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
        g.setColor(Color.WHITE);
        g.setFont(LABEL_FONT);
        g.drawString(keyCodeLabel, labelX, labelY);
        g.setColor(Color.BLACK);
    }

    @Override
    public Tile getReplacement() {
        return new EmptyTile(pos);
    }

    public static Image getImage() {
        return image;
    }

    @Override
    public String saveToString() {
        return "B "+keyCodeLabel;
    }
}
