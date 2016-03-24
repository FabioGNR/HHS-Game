/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import java.awt.Graphics;
import java.awt.Image;
import static hhsgame.Game.*;

/**
 *
 * @author Fabio
 */
public class KeyTile extends Tile {
    private static final Image image = ImageReader.getImage("keyTile.png");

    public KeyTile(BoardCoordinate pos) {
        super(pos);
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        if(image == null)
            return;
        int x = pos.getScreenX();
        int y = pos.getScreenY();
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
}
