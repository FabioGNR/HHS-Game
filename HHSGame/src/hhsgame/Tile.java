package hhsgame;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Tile {

    protected BoardCoordinate pos;

    public Tile(BoardCoordinate pos) {
        this.pos = pos;
    }

    public void setPos(BoardCoordinate pos) {
        this.pos = pos;
    }

    public BoardCoordinate getPos() {
        return pos;
    }

    public abstract boolean isPassable(Character character);
    public abstract Tile getReplacement();
    public abstract void onCharacterEnter(Character character);

    public abstract void paint(Graphics g);

}
