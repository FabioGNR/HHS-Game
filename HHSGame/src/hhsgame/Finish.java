package hhsgame;

import java.awt.Graphics;

public class Finish extends Tile{
    
    public Finish(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return true;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
