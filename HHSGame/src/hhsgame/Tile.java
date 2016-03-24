package hhsgame;

import java.awt.Graphics;

public abstract class Tile extends BoardComponent{
    
    public Tile(BoardCoordinate pos) {
        super(pos);
    }
    
    public abstract boolean isPassable();
    public abstract void paint(Graphics g);
    
}
