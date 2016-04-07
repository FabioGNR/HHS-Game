package hhsgame;

import java.awt.Graphics;
import static hhsgame.Game.*;
import java.awt.Color;
import java.awt.Font;

public class PopUpBox {
    
    private final String text;
    private final static Font font = new Font("Calibri", Font.BOLD, 68);
    
    public PopUpBox(String text) {
        this.text = text;
    }
    
    //paint the popUpBox
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(TILE_WIDTH, TILE_HEIGHT*3, TILE_WIDTH*(COLS-2), TILE_HEIGHT*(ROWS-6));
        g.setColor(Color.BLACK);
        g.drawRect(TILE_WIDTH, TILE_HEIGHT*3, TILE_WIDTH*(COLS-2), TILE_HEIGHT*(ROWS-6));
        g.setColor(Color.GRAY);
        g.setFont(font);
        g.drawString(text, TILE_WIDTH*((COLS-2)/3), TILE_HEIGHT*((ROWS-2)/2));
    }
    
}
