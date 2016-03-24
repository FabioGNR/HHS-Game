package hhsgame;

public abstract class BoardComponent{
    
    protected BoardCoordinate pos;

    public BoardComponent(BoardCoordinate pos) {
        this.pos = pos;
    }
    
    public void setPos(BoardCoordinate pos) {
        this.pos = pos;
    }

    public BoardCoordinate getPos() {
        return pos;
    }
}
