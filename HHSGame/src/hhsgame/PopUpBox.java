package hhsgame;

import java.awt.Graphics;
import static hhsgame.Game.*;
import java.awt.Color;

public class PopUpBox {
    
    private String text;
    
    public PopUpBox(String text) {
        this.text = text;
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH*(COLS-2), TILE_HEIGHT*(ROWS-2));
        g.setColor(Color.GRAY);
        g.drawString(text, TILE_WIDTH, TILE_HEIGHT);
    }
    
}
