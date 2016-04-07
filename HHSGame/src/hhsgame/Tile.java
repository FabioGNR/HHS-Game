package hhsgame;

import java.awt.Graphics;

public abstract class Tile {

    protected final BoardCoordinate pos;

    //create Tile from BoardCoordinate. Subclasses inherit position from Tile
    public Tile(BoardCoordinate pos) {
        this.pos = pos;
    }

    public BoardCoordinate getPos() {
        return pos;
    }
    
    //Abstract methods are required in subclasses and non-abstract methods are optional
    public abstract boolean isPassable(GameCharacter character);

    public Tile getReplacement() {
        return this;
    }

    public void onCharacterEnter(GameCharacter character) {
    }

    public boolean isFinish() {
        return false;
    }
    
    // returns string that can be saved in a level file
    public abstract String saveToString();

    public abstract void paint(Graphics g);
}
