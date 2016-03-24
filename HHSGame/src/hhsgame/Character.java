package hhsgame;

import java.awt.Graphics;

public class Character{
    
    private Key key;
    private Tile currentTile;
    
    public Character(Tile startTile) {
        currentTile = startTile;
    }
    
    public Key getKey() {
        return this.key;
    }
    
    public boolean hasKey() {
        return this.key != null;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
    
    public void paint(Graphics g)
    {
        
    }
}
