package hhsgame;

import java.awt.Graphics;

public class Wall extends Tile{
    public Wall(BoardCoordinate pos) {
        super(pos);
    }
    
    public boolean isPassable() {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
