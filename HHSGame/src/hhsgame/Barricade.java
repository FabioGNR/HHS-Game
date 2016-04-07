package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;
import java.awt.Color;

public class Barricade extends Tile{
    private static final Image image = ImageReader.getImage("barricade.png");
    private final int barrKeyCode;
    private final String keyCodeLabel;
    
    //whenever an instance of barricade is made, it requires a position in
    //BoardCoordinate and a code
    public Barricade(BoardCoordinate pos, int code) {
        super(pos);
        barrKeyCode = code;
        //barrKeyCode is converted to string
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
        //whenever image is null, return because we can't draw without the image
        if(image == null)
            return;
        //position screenX and screenY from BoardCoordinate assigned to x and y
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        //image is drawn on the specific position, with a specific size
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
        //setlocation of label on the barricade
        int labelX = x + (TILE_WIDTH-50)/2;
        int labelY = y + (TILE_HEIGHT-20)/2;
        g.setColor(Color.WHITE);
        g.setFont(LABEL_FONT);
        g.drawString(keyCodeLabel, labelX, labelY);
    }

    //override the getReplacement method from Tile class and replace with EmptyTile
    @Override
    public Tile getReplacement() {
        return new EmptyTile(pos);
    }
    public static Image getImage() {
        return image;
    }
    
    // returns string that can be saved in a level file
    @Override
    public String saveToString() {
        return "B "+keyCodeLabel;
    }
}
