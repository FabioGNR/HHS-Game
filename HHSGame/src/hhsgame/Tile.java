package hhsgame;

import java.awt.Graphics;

public abstract class Tile {

    protected final BoardCoordinate pos;

    public Tile(BoardCoordinate pos) {
        this.pos = pos;
    }

    public BoardCoordinate getPos() {
        return pos;
    }

    public abstract boolean isPassable(GameCharacter character);
    public Tile getReplacement() {
        return this;
    }
    public void onCharacterEnter(GameCharacter character) {
    }
    public boolean isFinish()
    {
        return false;
    }

    public abstract String saveToString();
    public abstract void paint(Graphics g);
}
