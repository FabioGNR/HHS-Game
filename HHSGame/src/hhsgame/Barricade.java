package hhsgame;

import java.awt.Graphics;

public class Barricade extends Tile{
    
    public Barricade(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return false;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
