package hhsgame;

import java.awt.Graphics;

public abstract class Tile {

    protected final BoardCoordinate pos;

    //position Tile from BoardCoordinate. Subclasses inherit position from Tile
    public Tile(BoardCoordinate pos) {
        this.pos = pos;
    }

    //return position
    public BoardCoordinate getPos() {
        return pos;
    }
    
    //Abstract methods are automatically inherited by subclasses and none abstracts methods are optional
    public abstract boolean isPassable(GameCharacter character);

    public Tile getReplacement() {
        return this;
    }

    public void onCharacterEnter(GameCharacter character) {
    }

    public boolean isFinish() {
        return false;
    }

    public abstract String saveToString();

    public abstract void paint(Graphics g);
}
