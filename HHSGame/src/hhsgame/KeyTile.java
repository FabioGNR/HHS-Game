package hhsgame;

import java.awt.Graphics;

public class KeyTile extends Tile {

    private final Key key;
    
    public KeyTile(BoardCoordinate pos, Key key) {
        super(pos);
        this.key = key;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Key getKey() {
        return key;
    }
    
}
