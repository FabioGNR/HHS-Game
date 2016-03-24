/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import java.awt.Graphics;

/**
 *
 * @author Fabio
 */
public class KeyTile extends Tile {

    public KeyTile(BoardCoordinate pos) {
        super(pos);
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
