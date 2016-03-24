package hhsgame;

import java.awt.Graphics;

public class EmptyTile extends Tile{
    
    public EmptyTile(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
